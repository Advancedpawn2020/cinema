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
                    <i class="el-icon-lx-cascades"></i> {{name}}的会员卡列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                        type="primary"
                        icon="el-icon-bank-card"
                        class="handle-del mr10"
                        @click="memberVisible = true"
                >会员选择
                </el-button>
                <span v-if="isBirthday" class="birthday">
                      <el-tag
                              type="danger"
                              effect="dark" style="margin-left:10px">
                    今天是用户{{this.name}}的生日
                      </el-tag>

                </span>

                <!--需求:模糊搜索,输入一个关键字弹出所有含有关键字的列表
                    实现:elementui的远程搜索实现,原先的做法是按照官网那样,聚焦时拿到全部数据放到数组,
                    然后输入文字时循环该数组,将匹配的筛选出来,但是有些数据量太大了,几万条,考虑到性能问题,
                    就由后台做模糊搜索,前端只需请求接口即可,后台返回匹配的数据.

-->
                <!--发现这个标签，用官方的实例也会报错，但是对使用无影响，使用@change无法响应，所以加上.native使用原生事件-->
                <el-autocomplete
                        clearable
                        v-model="cardQuery.cardId"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入要查找的会员卡ID"
                        @change.native="handleChange"
                        @select="handleSelect"
                        @clear="handleClear"
                        :trigger-on-focus="false"
                        style="float: right"

                ></el-autocomplete>
                <!--trigger-on-focus 是否在输入框 focus 时显示建议列表-->

            </div>
            <!--绑定memberData变量-->
            <el-table
                    :data="cardData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    :row-class-name="tableRowClassName"

            >
                <!--select：当用户手动勾选数据行的CheckBox时，触发的事件，参数：selection，row
                selection-change：当选项发生变化时会触发该事件，参数：selection
                可以定义好这两个方法打印下看看什么时候会触发
                当勾选住一个选项时，两个方法都会触发，当勾选全选时，只有selection-change方法触发，
                两个方法传递的selection参数就是：渲染该行的对象数据-->
                <!--Prop 是你可以在组件上注册的一些自定义 attribute。当一个值传递给一个 prop attribute 的时候
                ，它就变成了那个组件实例的一个属性。
                这里的prop、label、width、align都是会传递给el-table-column组件的属性
                这里的prop属性对应列内容的字段名，会被分配到绑定到表格的data对应的数据-->
                <el-table-column prop="cardId" label="ID" width="180" align="center"></el-table-column>
                <el-table-column prop="balance" label="余额" align="center">
                    <template slot-scope="scope">
                        ￥{{scope.row.balance}}
                    </template>
                </el-table-column>
                <el-table-column prop="integral" label="积分" align="center"></el-table-column>
                <el-table-column prop="lose" label="状态" align="center">
                    <template slot-scope="scope">
                        <el-tag
                                :type="scope.row.lose===0?'success':'danger'"
                        >{{scope.row.lose===0?'正常':'挂失'}}
                        </el-tag>
                        <!--<el-tag v-if="scope.row.orderStatus == 1">待付款</el-tag>
                        <el-tag v-else-if="scope.row.orderStatus == 2">待发货</el-tag>
                        <el-tag v-else-if="scope.row.orderStatus == 3">已发货</el-tag>
                        <el-tag v-else-if="scope.row.orderStatus == 4">订单关闭</el-tag>
                        <el-tag v-else-if="scope.row.orderStatus == 5">订单完成</el-tag>
                        -->
                    </template>
                </el-table-column>
                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">

                        <el-button
                                type="primary"
                                v-if="scope.row.balance>0"
                                @click="handleConsume(scope.$index, scope.row)"
                                :disabled="scope.row.lose!==0"
                        >消费
                        </el-button>
                    </template>
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

        <!--消费，为了给选择器选择时传递多个值，用@change来改变-->
        <el-dialog title="消费" :visible.sync="consumeVisible" width="35%">
            <el-form :model="form" :label-position="labelPosition" label-width="80px" :rules="rules">
                <el-form-item label="卡号">
                    {{form.cardId}}
                </el-form-item>
                <el-form-item label="电影票">
                    <el-select v-model="form.movieId" placeholder="请选择电影"
                               @change="pickMoviePriceAndIntegral" label-width="80px">
                        <!--使用数组中不会变化的那一项作为key值,对应到项目中,即每条数据都有一个唯一的id,来标识这条数据的唯一性;
                        使用v-for更新已渲染的元素列表时,默认用就地复用策略;列表数据修改的时候,
                        他会根据key值去判断某个值是否修改,如果修改,则重新渲染这一项,否则复用之前的元素;-->
                        <el-option
                                v-for="item in movieData"
                                :key="item.movieId"
                                :label="item.name"
                                :value="item.movieId">
                            <span style="float: left">{{ item.name }}</span>
                            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.price }}</span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="价格">
                    {{form.price}}
                </el-form-item>
                <el-form-item label="获得积分">
                    {{form.integral}}
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="concelConsume">取 消</el-button>
                <el-button type="primary" @click="saveConsume">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="会员选择" :visible.sync="memberVisible">
            <el-table
                    :data="memberData"
                    border
                    highlight-current-row
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @current-change="handleCurrentChange"
            >
                <el-table-column prop="memberId" label="ID" width="180" align="center"></el-table-column>
                <el-table-column prop="name" width="150" label="会员姓名" align="center"></el-table-column>
                <el-table-column prop="sex" label="性别" align="center"></el-table-column>
                <el-table-column prop="birthday" label="生日" align="center"></el-table-column>
            </el-table>
            <!--底部的分页区域-->
            <div class="pagination">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page="memberQuery.pageIndex"
                        :page-size="memberQuery.pageSize"
                        :total="memberQuery.pageTotal"
                        @current-change="handlememberQueryPageChange"
                ></el-pagination>
            </div>

                       <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="memberVisible = false" :disabled="accessCard" >确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    import {reissueCard} from "../../api/index";
    import {cancelCard} from "../../api/index";
    import {loseCard} from "../../api/index";
    import {rechargeCard} from "../../api/index";
    import {consumeCard} from "../../api/index";
    import {getCardData} from "../../api/index";
    import {registerCard} from '../../api/index';
    import {movieData} from "../../api/index";
    import {queryRecord} from "../../api/index";
    import {exchangeIntegral} from "../../api/index";
    import {getCardIdByFuzzyQuery} from "../../api/index";
    import {getCardByCardId} from "../../api/index";
    import {memberData} from '../../api/index';
    import bus from '../common/bus';

    //定义的单文件组件向外导出默认对象
    export default {
        //组件name的作用是，便于调试和便于调用自身
        //当我们使用 keep-alive时可以使用include和exclude指定需要缓存和不需要缓存的组件。指定的依据就是组件的name。
        name: 'basetable',
        data() {
            return {
                query: {
                    memberId: '',
                    name: '',
                    pageIndex: 1,
                    pageSize: 10,
                    birthdayQuery: ''
                },
                memberQuery:{
                    memberId: '',
                    name: '',
                    pageIndex: 1,
                    pageSize: 5,
                    birthdayQuery: ''
                },
                accessCard:true,
                memberData: [],
                memberVisible:false,
                loginForm:{},
                cardQuery: {
                    cardId: ''
                },
                rules: {
                    memberId: [{required: true, message: '请输入会员名', trigger: 'blur'}],
                },
                integralExchange: [
                    {
                        key: '0',
                        label: `布娃娃---3000`,
                        disable: false,

                    },
                    {
                        key: '1',
                        label: `玩具汽车---900`,
                        disable: false,

                    },
                    {
                        key: '2',
                        label: `变形金刚---5000`,
                        disable: false,

                    },
                    {
                        key: '3',
                        label: `可乐---500`,
                        disable: false,

                    },
                    {
                        key: '4',
                        label: `航模---6666`,
                        disable: false,

                    }
                ],
                cardData: [],
                recordData: [],
                movieData: [],
                multipleSelection: [],
                delList: [],
                rechargeVisible: false,
                registerVisible: false,
                recordVisible: false,
                consumeVisible: false,

                integralExchangeVisible: false,
                pageTotal: 0,
                pickIntegral: [],
                present: '',
                form: {},
                idx: -1,
                id: -1,
                name,
                isBirthday: false,
                beyondLimit: false,
                memberBirthday: '',
                labelPosition: 'right',
            };
        },
        /* 日期格式化组件的使用：
    （一）npm安装
    npm install moment --save
    （二）在main.js上引入，挂载在vue上
      import moment from 'moment'
    Vue.prototype.$moment = moment
    （三）在html中使用
    <div class="title">{{$moment(key).format('YYYY-MM-DD')}}</div>*/
        created() {
            //将接收字符串转换为数据才能取出数据
            let obj = eval('(' + this.$route.query.data + ')');
            if (obj === undefined) {
                this.memberVisible = true;
                this.getMemberData();
            }else {
                this.name = obj.name;
                this.memberBirthday = obj.birthday;
                this.query.memberId = obj.memberId;
                this.judgeBirthday();
                this.getCardData();
                this.getMovieData();
            }

            //当电影信息在首页修改后，重新获取电影信息
            bus.$on('movieData-change', () => {
                this.getMovieData();
            });
        },

        computed: {
            integralTotal: {
                get() {//回调函数 当需要读取当前属性值时执行，根据相关数据计算并返回当前属性的值
                    return this.cardData.reduce((total, currentValue) => {
                        return total + currentValue.integral;
                    }, 0);
                },
                set(val) {//监视当前属性值的变化，当属性值发生变化时执行，更新相关的属性数据
                    //val就是integralTotal的最新属性值
                    return val;
                }
            },
            //返回穿梭框所选定的礼物所消耗的积分，并判断如果大于拥有积分，则锁定确认键，并弹出错误提示
            //顺便得到当前选定的所有礼物名称
            consumeIntegral: {
                get() {
                    let sum = 0;
                    this.present = '';
                    for (let i = 0; i < this.pickIntegral.length; i++) {
                        sum += parseInt(this.integralExchange[this.pickIntegral[i]].label.split('---')[1]);
                        this.present += this.integralExchange[this.pickIntegral[i]].label.split('---')[0] + '、';

                    }
                    this.present = this.present.substring(0, this.present.length - 1);
                    if (sum > this.integralTotal) {
                        this.$message.error("所选积分已超出现有积分，请放回一部分");
                        this.beyondLimit = true;
                    } else {
                        this.beyondLimit = false;
                    }
                    return sum;
                },
            },
        },
        methods: {
            handleCurrentChange(val){
                this.name = val.name;
                this.memberBirthday = val.birthday;
                this.query.memberId = val.memberId;
                this.accessCard=false;
                this.judgeBirthday();
                this.getCardData();
                this.getMovieData();
            },
            getMemberData() {
                memberData(this.memberQuery).then(res => {
                    this.memberData = res.data.list;
                    this.memberQuery.pageTotal = res.data.pageTotal || 0;
                });
            },

            handleChange() {
                if (this.cardQuery.cardId === '') {
                    this.getCardData();
                }
            },
            handleDealSearch() {
                queryRecord(this.query).then(res => {
                    if (res.status == 200) {
                        this.recordData = res.data.list;
                        this.pageTotal = res.data.pageTotal;
                        this.recordVisible = true;
                    } else {
                        this.$message.error("服务器发生故障，请稍后再试");
                    }
                })

            },
            concelConsume() {
                this.consumeVisible = false;
                this.form = {};
            },
            pickMoviePriceAndIntegral(e) {
                this.movieData.map((item, i) => {
                    if (item.movieId == e) {
                        this.form.price = item.price;
                        this.form.integral = item.integral;
                        this.form.movieName = item.name;
                    }
                });
            },
            //获取电影信息
            getMovieData() {
                this.movieData = eval('(' + localStorage.getItem("movieData") + ')');
            },
            //补卡
            handleReissue(index, row) {
                reissueCard(row).then(res => {
                    if (res.status == 200) {
                        //这里不返回数据，只是重新查询
                        this.$message.success(`数据转移成功，新卡号为${res.data}`);
                        this.getCardData();
                    } else {
                        this.$message.error(`补卡失败！`);
                    }
                })
            },
            //解挂
            handleCancel(index, row) {
                cancelCard(row).then(res => {
                    if (res.status == 200) {
                        //这里不返回数据，只是重新查询
                        this.$message.success(`解挂成功`);
                        this.getCardData();
                    } else {
                        this.$message.error(`解挂失败！`);
                    }
                })
            },
            //挂失
            handleLoss(index, row) {
                loseCard(row).then(res => {
                    if (res.status == 200) {
                        //这里不返回数据，只是重新查询
                        this.$message.success(`挂失成功`);
                        this.getCardData();
                    } else {
                        this.$message.error(`挂失失败！`);
                    }
                })
            },
            //充值窗口
            handleRecharge(index, row) {
                //弹出对话框
                this.form = row;
                this.rechargeVisible = true;
            },
            //保存充值数据
            saveRecharge() {
                let cardId = this.form.cardId;
                let value = this.form.value;
                let query = {
                    'cardId': cardId,
                    'value': value
                };
                rechargeCard(query).then(res => {
                    if (res.status == 200) {
                        //这里不返回数据，只是重新查询
                        this.$message.success(`卡号${this.form.cardId}充值 ￥${this.form.value}成功`);
                        this.rechargeVisible = false;
                        this.getCardData();
                        bus.$emit('recharge-change');
                    } else {
                        this.$message.error(`充值失败！`);
                        this.rechargeVisible = false;

                    }
                    this.form = {};
                })
            },
            //消费窗口
            handleConsume(index, row) {
                this.form = {};
                this.form.cardId = row.cardId;
                this.form.balance = row.balance;
                this.consumeVisible = true;
            },
            //保存消费数据
            saveConsume() {
                if (this.form.price > this.form.balance) {
                    this.$message.error(`余额不足，无法购买`);
                } else {
                    let cardId = this.form.cardId;
                    let price = this.form.price;
                    let movieName = this.form.movieName;
                    let integral = this.form.integral;
                    let _query = {
                        'cardId': this.form.cardId,
                        'price': this.form.price,
                        'integral': this.form.integral
                    };
                    //金额，消费类型
                    consumeCard(_query).then(res => {
                        if (res.status == 200) {
                            //这里不返回数据，只是重新查询，这里要提示金额
                            this.$message({
                                message: `卡号         ${cardId}
                            消费      ￥${price}      ，成功购买     ${movieName}     电影票，获得积分      ${integral}`,
                                type: 'success',
                                duration: 5000,
                                center: true
                            });
                            this.getCardData();
                            bus.$emit('movie-change');
                        } else {
                            this.$message.error(`消费失败！`);
                        }
                    });
                    this.consumeVisible = false;
                    this.form = {};
                }
            },
            //判断当前是否生日
            judgeBirthday(index, row) {

                let nowDate = this.$moment(new Date()).format('YYYY-MM-DD');
                if (this.memberBirthday === nowDate) {
                    this.isBirthday = true;
                }
            },
            //根据卡的状态显示状态行的颜色
            tableRowClassName({row, rowIndex}) {
                if (row.lose !== 0) {
                    return 'warning-row';
                }
                return '';
            },
            // 开发时，获取 easy-mock 的模拟数据
            // 将query数据作为参数传递给fetchData，在then后面的回调函数中操作数据
            getCardData() {
                getCardData(this.query).then(res => {
                    this.cardData = res.data.list;
                    this.pageTotal = res.data.pageTotal || 0;
                });
            },
            //注册卡对话框
            handleRegister() {
                this.registerVisible = true;
            },
            //请求注册
            saveRegister() {
                registerCard(this.query).then(res => {
                    if (res.status == 200) {
                        //这里不返回数据，只是重新查询
                        this.$message.success(`注册成功`);
                        this.getCardData();
                        //发送会员卡更改事件给中央事件接收器bus
                        bus.$emit('card-change');
                        this.registerVisible = false;
                    } else {
                        this.$message.error(`注册失败！`);
                    }
                })
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
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageIndex', val);
                this.getCardData();
            },
            handlememberQueryPageChange(val){
                this.$set(this.memberQuery, 'pageIndex', val);
                this.getMemberData();
            },
            //交易记录翻页
            handleRecordPageChange(val) {
                this.$set(this.query, 'pageIndex', val);
                this.handleDealSearch();
            },
            //积分兑换窗口
            handleIntegralExchange() {
                this.integralExchangeVisible = true;
            },
            //保存积分兑换数据
            saveIntegralExchange() {
                let query = {
                    'memberId': this.query.memberId,
                    'integral': this.consumeIntegral
                };
                exchangeIntegral(query).then(res => {
                    if (res.status == 200) {
                        this.$message.success(`积分兑换成功，会员可获得${this.present}`);
                        this.integralExchangeVisible = false;
                        this.getCardData();
                        bus.$emit('integral-change');
                    } else {
                        this.$message.success(`服务端发生错误，积分兑换失败`);
                    }
                    this.pickIntegral = [];
                });
            },
            //会员卡id号搜索(模糊搜索),cb是一个回调函数，用于在输入框下拉框中显示数据
            querySearch(queryString, cb) {
                if (queryString != '') {
                    this.getPlanTypeData(queryString, (data) => {
                        let results = '';
                        if (queryString && !data[0].noId) {  //输入框有值且有匹配数据时
                            results = data.filter(this.createFilter(queryString))
                        } else {   //没有匹配数据时显示自定义的字段
                            results = data
                        }
                        cb(results);
                    });
                }
            },
            createFilter(queryString) {
                return (queryString) => {
                    // return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase())> -1);
                    //后台已做筛选,不需再过滤
                    return (queryString.value)
                };
            },
            //获取cardId数据
            getPlanTypeData(val, fun) {
                let dataArr = [];
                let query = {
                    'memberId': this.query.memberId,
                    'cardId': val
                };
                getCardIdByFuzzyQuery(query).then(res => {    //发送请求
                    if (res.status != 200) return false;
                    let dataList = res.data;

                    if (dataList.length > 0) {
                        dataList.forEach((item, index) => {
                            dataArr.push({
                                'value': item.cardId,
                                'name': item.cardId
                            })
                        });
                    } else {
                        dataArr.push({
                            'value': '无搜索结果',
                            'noId': '无搜索结果',
                        })
                    }
                    fun(dataArr);
                });
            },
            //搜索选中
            handleSelect(item) {
                if (item.name) {
                    //this.$set(this.addForm,'jxdescribe',item.name);
                    //向服务器发出请求，查询到当前cardId的全部信息，在表格中显示
                    getCardByCardId(this.cardQuery).then(res => {
                        if (res.status == 200) {
                            this.cardData = [];
                            this.cardData.push(res.data)
                        }
                    })
                }
            },
            handleClear() {
                this.getCardData();
            }
        }
    };
</script>
<!-- 引入供row-class-name使用的全局样式，因为这个属性是table的父组件使用的-->
<style scoped>
    @import "../../assets/css/common.css";


    .handle-box {
        margin-bottom: 20px;
    }


    .handle-input {
        width: 30%;
        /*display: inline-block;*/
        float: right;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }


    .mr10 {
        text-align: center;
    }


</style>
