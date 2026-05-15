<template>
    <div>
        <!--面包屑组件，在el-breadcrumb中使用el-breadcrumb-item标签表示从首页开始的每一级。
        Element 提供了一个separator属性，在el-breadcrumb标签中设置它来决定分隔符，它只能是字符串，默认为斜杠/。
        例如：
        <el-breadcrumb separator="/">
  <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
  <el-breadcrumb-item>活动列表</el-breadcrumb-item>
  <el-breadcrumb-item>活动详情</el-breadcrumb-item>
</el-breadcrumb>
-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 会员列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <!--使用下拉选择菜单来选择搜索条件-->
                <el-select v-model="query.birthdayQuery" clearable placeholder="按生日查询" class="handle-select mr10">
                    <el-option key="0" label="当天过生日" value="1"></el-option>
                    <el-option key="2" label="七天内过生日" value="7"></el-option>
                    <el-option key="3" label="十天内过生日" value="10"></el-option>
                </el-select>
                <el-input v-model="query.name" clearable placeholder="会员名称查询" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">查询</el-button>
            </div>
            <!--绑定memberData变量-->
            <el-table
                    :data="memberData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @selection-change="handleSelectionChange"
            >
                <!--select：当用户手动勾选数据行的CheckBox时，触发的事件，参数：selection，row
                selection-change：当选项发生变化时会触发该事件，参数：selection
                可以定义好这两个方法打印下看看什么时候会触发
                当勾选住一个选项时，两个方法都会触发，当勾选全选时，只有selection-change方法触发，
                两个方法传递的selection参数就是：渲染该行的对象数据-->
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <!--Prop 是你可以在组件上注册的一些自定义 attribute。当一个值传递给一个 prop attribute 的时候
                ，它就变成了那个组件实例的一个属性。
                这里的prop、label、width、align都是会传递给el-table-column组件的属性
                这里的prop属性对应列内容的字段名，会被分配到绑定到表格的data对应的数据-->
                <el-table-column prop="memberId" label="ID" width="180" align="center"></el-table-column>
                <el-table-column prop="name" width="150" label="会员姓名" align="center"></el-table-column>
                <el-table-column prop="sex" label="性别" align="center"></el-table-column>
                <el-table-column prop="birthday" label="生日" align="center">                </el-table-column>
                <el-table-column prop="instance" label="距离生日天数" align="center">
                </el-table-column>
            </el-table>
            <!--底部的分页区域-->
            <div class="pagination">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page="query.pageIndex"
                        :page-size="query.pageSize"
                        :total="pageTotal"
                        @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    import {memberData} from '../../api/index';
    import {registerMember} from '../../api/index';
    import {delMember} from '../../api/index';
    import {modifyMember} from '../../api/index';
    import {loginMember} from "../../api/index";
    import bus from '../common/bus';
    //定义的单文件组件向外导出默认对象
    export default {
        //组件name的作用是，便于调试和便于调用自身
        //当我们使用 keep-alive时可以使用include和exclude指定需要缓存和不需要缓存的组件。指定的依据就是组件的name。
        name: 'basetable',
        data() {
            return {
                query: {
                    name: '',
                    pageIndex: 1,
                    pageSize: 10,
                    birthdayQuery: ''
                },
                rules: {
                    memberId: [{required: true, message: '请输入会员名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                    name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
                    sex: [{required: true, message: '请输入性别', trigger: 'blur'}],
                    birthday: [{required: true, message: '请输入生日', trigger: 'blur'}],
                },
                memberData: [],
                multipleSelection: [],
                delList: [],
                editVisible: false,
                registerVisible: false,
                loginVisible: false,
                pageTotal: 0,
                form: {},
                registerForm: {},
                loginForm: {},
                idx: -1,
                id: -1
            };
        },

        created() {
            this.getMemberData();

            this.openNotify();
        },
        methods: {
            openNotify() {
                this.$notify({
                    title: '提示',
                    message: '本作业所有密码为123123',
                    duration: 0
                });
            },
            // 开发时，获取 easy-mock 的模拟数据
            // 将query数据作为参数传递给fetchData，在then后面的回调函数中操作数据
            getMemberData() {
                memberData(this.query).then(res => {
                    this.memberData = res.data.list;
                    this.pageTotal = res.data.pageTotal || 50;
                    this.memberData.map((m)=>{
                        // 转换日期格式bai
                        var now = Date.parse(new Date());
                        let birthday = Date.parse(new Date(m.birthday.replace(/-/g, '/'))); // "2010/08/01";
                        m.instance = parseInt((now-birthday)/ (1000 * 60 * 60 * 24));//核心：时间戳相减，然后除以天数
                    });
                    this.memberData.sort((a,b)=>a.instance-b.instance);
                });
            },
            handleRegister() {
                this.registerVisible = true;
            },
            saveRegister() {
                registerMember(this.registerForm).then(res => {
                    if (res.status == 200) {
                        this.registerVisible = false;
                        this.$message.success(`注册成功`);
                        this.memberData.push(res.data);
                        //还要更改下面的分页显示数量
                        this.pageTotal++;
                        //发送会员更改事件给中央事件接收器bus
                        bus.$emit('member-change');
                    } else {
                        this.$message.error(`修改第 ${this.idx + 1} 行失败`);
                    }
                    this.registerForm = {};
                })
            },
            handleMember(index, row) {
                this.idx = index;
                this.loginForm = row;
                this.loginForm.password = '';
                this.loginVisible = true;
            },
            loginMember() {
                loginMember(this.loginForm).then(res => {
                    let _this = this;
                    if (res.status == 200) {
                        this.loginVisible = false;
                        //路由跳转到会员卡页面，这里需要将登录会员的账号信息传递过去
                        /*
                        * // 使用path来匹配路由，然后通过query来传递参数
                            this.$router.push({
                                path: '/orderDetail',
                                query: {
                                id: 1
                                }
                            });
                            // 对应路由配置如下：
                               {
                                     path: '/orderDetail',
                                     name: 'orderDetail',
                                     component: orderDetail
                                }
                            // 组件中获取参数的方式：
                            this.$route.query.id
                        * */
                        //如果需要在跳转路由时传参，要使用query而不是params，因为前者是get方式请求，在地址栏中显示参数的，即使刷新也会不丢失数据
                        //如果传递的数据是对象，需要将对象转换为字符串，在接收处转换回来

                        /*
                        提醒一下，当使用路由参数时，例如从 /user/foo 导航到 /user/bar，
                        原来的组件实例会被复用。因为两个路由都渲染同个组件，比起销毁再创建，复用则显得更加高效。不过，这也意味着组件的生命周期钩子不会再被调用。
                        复用组件时，想对路由参数的变化作出响应的话，你可以简单地 watch (监测变化) $route 对象
                        或者不想复用的话，就在父组件的router-view上加个key
                        <router-view :key="$route.fullPath"></router-view>
                        */
                        this.$router.push({
                            name: 'card',
                            query: {
                                data: JSON.stringify(_this.loginForm)
                            }
                        });
                        this.loginForm = {};
                    } else {
                        this.$message.error('账号或者密码错误，请重新输入');
                    }
                })
            },
            // 触发搜索按钮，将显示的页面设为第一页
            handleSearch() {
                //如果在实例创建之后添加新的属性到实例上，它不会触发视图更新。
                // 更新新增属性e，是不会更新视图，但是会改变其值，当更新原有属性d时会更新视图，同时将新增的属性e的值也更新到视图里边
                //解决：Vue 不允许在已经创建的实例上动态添加新的根级响应式属性 (root-level reactive property)。
                // 然而它可以使用 Vue.set(object, key, value) 方法将响应属性添加到嵌套的对象上：
                //还可以使用 vm.$set 实例方法，这也是全局 Vue.set 方法的别名：
                this.$set(this.query, 'pageIndex', 1);
                this.getMemberData();
            },
            // 删除操作
            handleDelete(index, row) {
                // 二次确认删除
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        delMember(row).then(res => {
                            if (res.status == 200) {
                                //弹出过渡消失的成功！消息提示
                                this.$message.success('删除成功');
                                /*splice(index,len,[item])它也可以用来替换/删除/添加数组内某一个或者几个值（该方法会改变原始数组）
                                  index:数组开始下标
                                  len: 替换/删除的长度
                                  item:替换的值，删除操作的话 item为空*/
                                this.memberData.splice(index, 1);
                                //发送会员更改事件给中央事件接收器bus
                                bus.$emit('member-change');
                            } else {
                                this.$message.error("服务器发生问题，删除失败");
                            }
                        })

                    })
                    .catch(() => {
                    });//取消捕捉
            },
            delAllSelection() {
                // 二次确认删除
                this.$confirm('确定要删除所选行吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    const length = this.multipleSelection.length;
                    let str = '';
                    this.delList = this.delList.concat(this.multipleSelection);
                    for (let i = 0; i < length; i++) {
                        str += this.multipleSelection[i].name + ' ';
                        delMember(this.multipleSelection[i]).then(res => {
                            if (res.status == 200) {

                                //发送会员更改事件给中央事件接收器bus
                                bus.$emit('member-change');
                            } else {
                                this.$message.error(`服务器发生问题，删除${this.multipleSelection[i].name}失败`);
                            }
                        })
                    }
                    this.getMemberData();
                    this.$message.error(`删除了${str}`);
                    this.multipleSelection = [];
                }).catch(() => {
                });//取消捕捉

            },
            // 多选操作，//val 为选中数据的集合
            handleSelectionChange(val) {
                this.multipleSelection = val;
                /*
                可以通过以下方式来获取val中的值
                for(var i = 0;i<val.length;i++){
                alert(val[i].name);
                }*/
            },
            // 编辑操作，
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                modifyMember(this.form).then(res => {
                    if (res.status == 200) {
                        this.editVisible = false;
                        this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                        this.$set(this.memberData, this.idx, this.form);
                    } else {
                        this.$message.error(`修改第 ${this.idx + 1} 行失败`);
                    }
                })
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageIndex', val);
                this.getMemberData();
            }
        }
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {

        width: 150px;

    }

    .handle-input {
        width: 150px;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }

    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }
    a{
        text-decoration: none;/*why set text-decoration as none?*/
        position:absolute;
        top: 20%;
        left: 50%;
        transform: translate(-50%, -50%);/*translate the button self*/
        font-size: 24px;
        background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
        background-size: 400%;
        width: 100px;
        height: 50px;
        line-height: 50px;/*this means the height the text self */
        text-align: center;
        color: #fff;
        text-transform: capitalize;
        border-radius: 5px;/*make the profile round*/
        z-index: 1;
    }
    a::before{
        content: "";
        position: absolute;
        left: -5px;
        top: -5px;
        right: -5px;
        bottom: -5px;
        background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
        background-size: 400%;
        border-radius: 50px;
        filter: blur(20px);
        z-index: -1;
    }
    a:hover::before, a:hover{
        animation: sun 8s infinite;
    }
    @keyframes sun{
        100%{
            background-position: -400% 0;
        }
    }
</style>
