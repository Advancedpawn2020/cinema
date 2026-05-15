<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">ZRK电影院会员管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.managerId" placeholder="managerId">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        type="password"
                        placeholder="password"
                        v-model="param.password"
                        @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <p class="login-tips">Tips : 默认账号为admin，密码为123123。</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {managerLogin} from "../../api";
    export default {
    data: function() {
        return {
            param: {
                managerId: '',
                password: '',
            },
            rules: {
                managerId: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
            },
        };
    },
    methods: {
        submitForm() {

                /*this.$refs.formName.validate()登录验证函数，formName是form的ref属性,使用此方法前检查prop一定必须要写在<el-form-item>上面*/
                this.$refs.login.validate(valid => {
                    if (valid) {
                        managerLogin(this.param).then(res => {
                            if(res.status==200) {
                                this.$message.success('登录成功');
                                //当提交之后，将当前输入的用户名存储到localStorage中
                                localStorage.setItem('managerId', this.param.managerId);
                                /*router.push(location)
                          想要导航到不同的 URL，则使用 router.push 方法。
                          这个方法会向 history 栈添加一个新的记录，所以，当用户点击浏览器后退按钮时，则回到之前的 URL。
                          当你点击 <router-link> 时，这个方法会在内部调用，所以说，点击 等同于调用 router.push(…)。*/
                                this.$router.push('/');
                            }else{
                                this.$message.error('账号或者密码错误，请重新输入！');
                                return false;
                            }
                        }
                    );

                    } else {
                        this.$message.error('请输入账号和密码');
                        console.log('error submit!!');
                        return false;
                    }

                });
        },
    },
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../../assets/img/login-bg.jpg);
    background-size: 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #000000;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #000000;
}
</style>