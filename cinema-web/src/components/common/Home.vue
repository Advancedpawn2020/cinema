<!--这个文件是根地址/默认开启的-->
<template>
    <div class="wrapper">

        <v-head></v-head>
        <v-sidebar></v-sidebar>
        <!--这里的：是v-bind表示强制绑定，将其后的属性绑定到vue的数据控制
        表示 content-collapse 这个 class 存在与否将取决于数据属性collapse-->
        <!--在main.css中，此class的属性：
            .content-collapse {
            left: 65px;
            }
         content-box是主页面的属性，包括背景颜色等-->
        <div class="content-box" :class="{'content-collapse':collapse}">
            <!--这里现实tags的模板-->
            <v-tags></v-tags>
            <div class="content">
                <!--transition 属性设置元素当过渡效果，
                当Vue 在插入、更新或者移除 DOM 时，提供多种不同方式的应用过渡效果。
                对标签内包含的有开启关闭效果的DOM元素有效-->
                <!--同时生效的进入和离开的过渡不能满足所有要求，所以 Vue 提供了过渡模式
                    in-out：新元素先进行过渡，完成之后当前元素过渡离开。
                    out-in：当前元素先进行过渡，完成之后新元素过渡进入。-->
                <transition name="move" mode="out-in">
                    <!--是Vue的内置组件，能在组件切换过程中将状态保留在内存中，防止重复渲染DOM。
                    <keep-alive> 包裹动态组件时，会缓存不活动的组件实例，
                    而不是销毁它们。和 <transition> 相似，<keep-alive> 是一个抽象组件：
                    它自身不会渲染一个 DOM 元素，也不会出现在父组件链中。-->
                    <keep-alive :include="tagsList">
                        <!--在这个标签位置显示路由组件渲染
                        加上key表示不进行组件复用，当路由地址发生改变，就进行刷新-->
                        <router-view :key="$route.fullPath"></router-view>
                    </keep-alive>
                </transition>
                <!--这是一个小三角，回到顶部组件-->
                <el-backtop target=".content"></el-backtop>
            </div>
        </div>
    </div>
</template>

<script>
import vHead from './Header.vue';
import vSidebar from './Sidebar.vue';
import vTags from './Tags.vue';
import bus from './bus';
export default {
    //不使用return包裹的数据会在项目的全局可见，会造成变量污染；
    // 使用return包裹后数据中变量只在当前组件中生效，不会影响其他组件。
    data() {
        return {
            tagsList: [],
            collapse: false
        };
    },
    components: {
        vHead,
        vSidebar,
        vTags
    },
    // <!--表示生命周期中创建vue实例之后的钩子-->
    created() {
        /*vm.$emit( event, arg )
        触发当前实例上的事件
           vm.$on( event, fn );
           监听event事件后运行 fn；
           */
        //在侧边栏组件中发送的事件，当侧边栏被折叠后，主页的content的class也需要随之改变
        bus.$on('collapse-content', msg => {
            this.collapse = msg;
        });

        // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
        //这里将打开的标签加入到内存中
        bus.$on('tags', msg => {
            let arr = [];
            for (let i = 0, len = msg.length; i < len; i++) {
                msg[i].name && arr.push(msg[i].name);
            }
            this.tagsList = arr;
        });
    }
};
</script>
