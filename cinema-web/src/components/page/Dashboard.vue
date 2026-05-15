<!--挡泥板组件，首页-->
<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../../assets/img/admin.png" class="user-avator" alt/>
                        <div class="user-info-cont">
                            <div class="user-info-name">{{managerData.name}}</div>
                            <div>{{managerData.role}}</div>
                        </div>
                    </div>
                    <!--登录时间地点使用axios从服务器请求到-->
                    <div class="user-info-list">
                        管理员ID
                        <span>{{managerData.managerId}}</span>
                    </div>
                    <div class="user-info-list">
                        已工作时间
                        <span>{{workTime}}</span>
                    </div>
                </el-card>

                <!--percontage需要必须要保证数据存在再获取，不然刚获取时，数据还未计算完成，会在后台报错-->
                <el-card shadow="hover" style="height:252px;">
                    <div slot="header" class="clearfix">
                        <span>交易金额比例</span>
                    </div>
                    消费
                    <el-progress v-if="getDealRadio" :percentage="getDealRadio" color="#42b983"></el-progress>
                    充值
                    <el-progress v-if="rechargeRadio" :percentage="rechargeRadio" color="#f1e05a"></el-progress>
                    积分兑换
                    <el-progress v-if="integralRadio" :percentage="integralRadio"></el-progress>
                </el-card>
            </el-col>
            <!--用户访问量，系统消息，数量等也从服务端拿-->
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{statisticData.memberNum}}</div>
                                    <div>已注册会员</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-lx-vipcard grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{statisticData.cardNum}}</div>
                                    <div>已办理会员卡</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-lx-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{statisticData.movieNum}}</div>
                                    <div>已售电影票</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-card shadow="hover" style="height:403px;">
                    <div slot="header" class="clearfix">
                        <span>电影票</span>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="handleInsertMovie">添加</el-button>
                    </div>
                    <el-table
                            :data="movieData"
                            border
                            class="table"
                            ref="multipleTable"
                            header-cell-class-name="table-header"
                            @selection-change="handleSelectionChange"
                    >
                        <el-table-column type="selection" width="55" align="center"></el-table-column>
                        <el-table-column prop="movieId" label="ID" width="55" align="center"></el-table-column>
                        <el-table-column prop="name" label="电影名"></el-table-column>
                        <el-table-column prop="price" label="价格" width="55"></el-table-column>
                        <el-table-column prop="time" label="时间"></el-table-column>
                        <el-table-column prop="integral" label="积分" width="55"></el-table-column>
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope">
                                <el-button
                                        type="text"
                                        icon="el-icon-edit"
                                        @click="handleEdit(scope.$index, scope.row)"
                                >编辑
                                </el-button>
                                <!--点击时传递当前行索引和数据给函数，并在函数中赋值给form等数据，
                                并将编辑框显示-->
                                <el-button
                                        type="text"
                                        icon="el-icon-delete"
                                        class="red"
                                        @click="handleDelete(scope.$index, scope.row)"
                                >删除
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
                    <!-- 编辑弹出框，拿到刚刚赋值到form的数据 -->
                    <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
                        <el-form ref="form" :model="form" label-width="70px">
                            <el-form-item label="电影名">
                                <el-input v-model="form.name"></el-input>
                            </el-form-item>
                            <el-form-item label="价格">
                                <el-input v-model="form.price"></el-input>
                            </el-form-item>
                            <el-form-item label="积分">
                                <el-input v-model="form.integral"></el-input>
                            </el-form-item>
                        </el-form>
                        <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
                    </el-dialog>
                    <!--添加电影票对话框-->
                    <el-dialog title="添加" :visible.sync="insertVisible" width="30%">
                        <el-form ref="form" :model="insertForm" label-width="70px">
                            <el-form-item label="电影名">
                                <el-input v-model="insertForm.name"></el-input>
                            </el-form-item>
                            <el-form-item label="价格">
                                <el-input v-model="insertForm.price"></el-input>
                            </el-form-item>
                            <el-form-item label="积分">
                                <el-input v-model="insertForm.integral"></el-input>
                            </el-form-item>
                        </el-form>
                        <span slot="footer" class="dialog-footer">
                <el-button @click="insertVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveMovie">确 定</el-button>
            </span>
                    </el-dialog>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import bus from '../common/bus';
    import {movieData} from '../../api/index';
    import {managerData} from '../../api/index';
    import {dealData} from '../../api/index';
    import {statisticData} from '../../api/index';
    import {delMovie} from '../../api/index';
    import {modifyMovie} from '../../api/index';
    import {insertMovie} from '../../api/index';
    import {requestDealData} from '../../api/index';

    export default {
        name: 'dashboard',
        data() {
            return {
                query: {
                    pageIndex: 1,
                    pageSize: 4,
                    movieId: '',
                    managerId: localStorage.getItem('managerId')
                },
                movieData: [],
                movieAllData:[],
                managerData: {},
                dealData: {},
                statisticData: {},
                pageTotal: 0,
                form: {},
                insertForm:{},
                editVisible: false,
                insertVisible:false,
                total: 0,
                multipleSelection: [],
                defaultManagerId:'admin',
                timer: "",//定义一个定时器的变量
                workTime: "", // 获取当前时间
            };
        },
        created() {
            const _this = this;//声明一个变量指向Vue实例this，保证作用域一致
            // this.changeDate();
            this.getMovieData();
            this.getManagerData();
            this.getStatisticData();
            this.getDealData();
            bus.$on('member-change', () => {
                this.getStatisticData();
            });
            bus.$on('card-change', () => {
                this.getStatisticData();
            });
            bus.$on('integral-change', () => {
                this.getDealData();
            });
            bus.$on('recharge-change', () => {
                this.getDealData();
            });
            bus.$on('movie-change', () => {
                this.getStatisticData();
                this.getDealData();
            });
            _this.timer = setInterval(function(){
                _this.workTime = //修改数据date
                    _this.getHour()+new Date(new Date()- new Date(_this.managerData.time)).getMinutes()+":"+_this.getSecond();
            },1000);
        },
        beforeMount() {

        },
        beforeDestroy() {
            if (this.timer) {
                clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
            }
        },
        //computed用来监控自己定义的变量，该变量不在data里面声明，
        // 直接在computed里面定义，然后就可以在页面上进行双向数据绑定展示出结果或者用作其他处理；
        //而watch主要用于监控vue实例的变化，它监控的变量当然必须在data里面声明才可以
        computed: {
            totalNumber: function () {
                return this.dealData.consume + this.dealData.recharge + this.dealData.integral;
            },
            getDealRadio: function () {
                return Math.round(this.dealData.consume / this.totalNumber * 100);
            },
            rechargeRadio: function () {
                return  Math.round(this.dealData.recharge / this.totalNumber * 100);
            },
            integralRadio: function () {
                return  Math.round(this.dealData.integral / this.totalNumber * 100);
            }
        },

        methods: {
/*            changeDate() {
                const now = new Date().getTime();
                /!*                this.data.forEach((item, index) => {
                    const date = new Date(now - (6 - index) * 86400000);
                    item.name = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
                });*!/
            },*/
            getSecond(){
                let second = new Date(new Date()- new Date(this.managerData.time)).getSeconds();
                if(second<10){
                    return "0"+second;
                }else{
                    return second;
                }

            },
            getHour(){
                let hour = new Date(new Date()- new Date(this.managerData.time)).getHours()-8;
                if(hour>0){
                    return hour+":";
                }else{
                    return "";
                }
            },
            // 删除操作
            handleDelete(index, row) {
                // 二次确认删除
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        delMovie(row).then(res=>{
                            if(res.status==200){
                                //弹出过渡消失的成功！消息提示
                                this.$message.success('删除成功');
                                /*splice(index,len,[item])它也可以用来替换/删除/添加数组内某一个或者几个值（该方法会改变原始数组）
                                  index:数组开始下标
                                  len: 替换/删除的长度
                                  item:替换的值，删除操作的话 item为空*/
                                //this.movieData.splice(index, 1);
                                this.getMovieData();
                            }else{
                                this.$message.error("删除失败，服务器出现故障")
                            }
                        })

                    })
                    .catch(() => {
                    });//异常捕捉
            },
            // 编辑操作，
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                modifyMovie(this.form).then(res=>{
                    if(res.status==200){
                        this.editVisible = false;
                        this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                        //this.$set(this.movieData, this.idx, this.form);
                        this.getMovieData();
                    }else{
                        this.$message.error("修改失败，服务端出现错误");
                    }
                });
            },
            // 分页导航
            handlePageChange(val) {
                let i;
                this.$set(this.query, 'pageIndex', val);
                //在这里判断，并且清空并插入到数组
                this.movieData = [];
                let start = (this.query.pageIndex-1)*this.query.pageSize;
                let end = this.query.pageSize*this.query.pageIndex;
                if(this.pageTotal<end){
                    end = this.pageTotal;
                }
                for(i=start;i<end;i++){

                    this.movieData.push(this.movieAllData[i]);
                }
            },
            /*为了将所有movie的数据传递给card组件使用，直接从服务器数据库中查所有movie数据，
            * 然后在接收函数位置手动对接收数据进行分页显示*/
            getMovieData() {
                let _query ={
                    "pageIndex":this.query.pageIndex,
                    "pageSize":0
                }
                movieData(_query).then(res => {
                    this.movieData=[];
                    this.movieAllData = res.data.list;
                    this.pageTotal = res.data.pageTotal || 20;
                    let i;
                    let start = (this.query.pageIndex-1)*this.query.pageSize;
                    let end = this.query.pageSize*this.query.pageIndex;
                    if(this.pageTotal<end){
                        end = this.pageTotal;
                    }
                    for(i=start;i<end;i++){

                        this.movieData.push(this.movieAllData[i]);
                    }
                    localStorage.setItem("movieData",JSON.stringify(this.movieAllData));
                    bus.$emit('movieData-change');
                })
            },
            getManagerData() {
                managerData(this.query).then(res => {
                    this.managerData = res.data;
                    //得到当前已工作时间
                    this.workTime = //修改数据date
                        this.getHour()+new Date(new Date()- new Date(this.managerData.time)).getMinutes()+":"+this.getSecond();


                })
            },
            getDealData() {
                dealData().then(res => {
                    this.dealData = res;
                })
            },
            getStatisticData() {
                statisticData().then(res => {
                    this.statisticData = res.data;
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
            handleInsertMovie(){
                this.insertForm={};
                this.insertVisible=true;
            },
            saveMovie(){
                insertMovie(this.insertForm).then(res=>{
                    if(res.status==200) {
                        this.insertVisible = false;
                        this.$message.success(`添加成功`);
                        this.getMovieData();
                    }else{
                        this.insertVisible = false;
                        this.$message.success(`添加失败，服务端出现问题`);
                    }
                })
            },
            getDealData(){
                requestDealData().then(res=>{
                    if(res.status==200) {
                        this.dealData = res.data;
                    }
                })
            }
        }
    };
</script>


<style scoped>

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

</style>
