<template>
    <div class="tags" v-if="showTags">
        <ul>
            <li class="tags-li" v-for="(item,index) in tagsList" :class="{'active': isActive(item.path)}" :key="index">
                <!--Vue根组件已经注册了路由，所以可以直接使用路由组件标签，用户点击此title的标签可以跳转-->
                <router-link :to="item.path" class="tags-li-title">
                    {{item.title}}
                </router-link>
                <!--关闭按钮，点击后触发关闭标签函数-->
                <span class="tags-li-icon" @click="closeTags(index)">
                    <i class="el-icon-close"></i>
                </span>
            </li>
        </ul>
        <!--最右侧的标签选项按钮区域-->
        <div class="tags-close-box">
            <!--多个el-dropdown下拉 共用一个 @command 事件  @command="handleTags" 只要将命令作为command属性传入即可-->
            <el-dropdown @command="handleTags">
                <!--最上层显示按钮，点击按钮显示下拉菜单-->
                <el-button size="mini" type="primary">
                    标签选项<i class="el-icon-arrow-down el-icon--right"></i>
                </el-button>
                <!--这里也使用插槽分发内容，在父组件中显示-->
                <el-dropdown-menu size="small" slot="dropdown">
                    <el-dropdown-item command="other">关闭其他</el-dropdown-item>
                    <el-dropdown-item command="all">关闭所有</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
    import bus from './bus';
    export default {
        data() {
            return {
                tagsList: []
            }
        },
        methods: {
            //如果是完整地址，返回true，设置class为active
            isActive(path) {
                return path === this.$route.fullPath;
            },
            // 关闭单个标签
            closeTags(index) {
                const delItem = this.tagsList.splice(index, 1)[0];
                const item = this.tagsList[index] ? this.tagsList[index] : this.tagsList[index - 1];
                if (item) {
                    delItem.path === this.$route.fullPath && this.$router.push(item.path);
                }else{
                    this.$router.push('/');
                }
            },
            // 关闭全部标签
            closeAll(){
                this.tagsList = [];
                this.$router.push('/');
            },
            // 关闭其他标签
            closeOther(){
                const curItem = this.tagsList.filter(item => {
                    return item.path === this.$route.fullPath;
                })
                this.tagsList = curItem;
            },
            // 设置标签
            /*some() 方法用于检测数组中的元素是否满足指定条件（函数提供）。
              some() 方法会依次执行数组的每个元素：
              如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测。
              如果没有满足条件的元素，则返回false。
              注意： some() 不会对空数组进行检测。
              注意： some() 不会改变原始数组。
            */
            setTags(route){
                const isExist = this.tagsList.some(item => {
                    return item.path === route.fullPath;
                })
                //shift() 方法用于把数组的第一个元素从其中删除，并返回第一个元素的值。
                //push() 方法可向数组的末尾添加一个或多个元素，并返回新的长度。
                //逻辑：如果数组中的item地址没有和路由的完整地址一致一致的，且长度大于8，删除第一个，然后添加这个路由地址作为标签

                if(!isExist){
                    if(this.tagsList.length >= 8){
                        this.tagsList.shift();
                    }
                    this.tagsList.push({
                        title: route.meta.title,
                        path: route.fullPath,
                        name: route.matched[1].components.default.name
                    })
                }
                bus.$emit('tags', this.tagsList);
            },
            handleTags(command){
                command === 'other' ? this.closeOther() : this.closeAll();
            }
        },
        computed: {
            //在组件模板的根标签用if判断，绑定此函数，如果返回true表示有标签，显示标签栏，否则不显示
            showTags() {
                return this.tagsList.length > 0;
            }
        },
        watch:{
            //监视route，表示当前激活的路由的状态信息，
            // 包含了当前 URL 解析得到的信息，还有 URL 匹配到的 route records（路由记录）。
            //逻辑：当路由信息改变时，设置标签
            $route(newValue, oldValue){
                this.setTags(newValue);
            }
        },
        created(){
            //只要创建了实例，就默认设置一个默认的标签（首页）
            this.setTags(this.$route);
            // 监听关闭当前页面的标签页
            bus.$on('close_current_tags', () => {
                for (let i = 0, len = this.tagsList.length; i < len; i++) {
                    const item = this.tagsList[i];
                    if(item.path === this.$route.fullPath){
                        if(i < len - 1){
                            this.$router.push(this.tagsList[i+1].path);
                        }else if(i > 0){
                            this.$router.push(this.tagsList[i-1].path);
                        }else{
                            this.$router.push('/');
                        }
                        this.tagsList.splice(i, 1);
                        break;
                    }
                }
            })
        }
    }

</script>


<style>
    .tags {
        position: relative;
        height: 30px;
        overflow: hidden;
        background: #fff;
        padding-right: 120px;
        box-shadow: 0 5px 10px #ddd;
    }

    .tags ul {
        box-sizing: border-box;
        width: 100%;
        height: 100%;
    }

    .tags-li {
        float: left;
        margin: 3px 5px 2px 3px;
        border-radius: 3px;
        font-size: 12px;
        overflow: hidden;
        cursor: pointer;
        height: 23px;
        line-height: 23px;
        border: 1px solid #e9eaec;
        background: #fff;
        padding: 0 5px 0 12px;
        vertical-align: middle;
        color: #666;
        -webkit-transition: all .3s ease-in;
        -moz-transition: all .3s ease-in;
        transition: all .3s ease-in;
    }

    .tags-li:not(.active):hover {
        background: #f8f8f8;
    }

    .tags-li.active {
        color: #fff;
    }

    .tags-li-title {
        float: left;
        max-width: 80px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        margin-right: 5px;
        color: #666;
    }

    .tags-li.active .tags-li-title {
        color: #fff;
    }

    .tags-close-box {
        position: absolute;
        right: 0;
        top: 0;
        box-sizing: border-box;
        padding-top: 1px;
        text-align: center;
        width: 110px;
        height: 30px;
        background: #fff;
        box-shadow: -3px 0 15px 3px rgba(0, 0, 0, .1);
        z-index: 10;
    }

</style>
