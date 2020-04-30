<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head></head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
<style>
    body{
        margin-top:20px;
        margin:0 auto;
    }
    .carousel-inner .item img{
        width:100%;
        height:300px;
    }
    .container .row div{
        /* position:relative;
        float:left; */
    }

    font {
        color: #3164af;
        font-size: 18px;
        font-weight: normal;
        padding: 0 10px;
    }
</style>
</head>
<body>

<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
    <div class="row">

        <div class="col-md-2"></div>
        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <font>会员注册</font>USER REGISTER
            <form class="form-horizontal" style="margin-top:5px;" method="post" action="${pageContext.request.contextPath}/register">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
                        <input type="hidden" name="method" value="userRegist">
                    </div>
                    <span id="username_span"></span>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="usercaption" placeholder="请输入姓名" name="name">
                    </div>
                </div>
                <div class="form-group opt">
                    <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineRadio1" value="男" checked="checked"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="inlineRadio2" value="女"> 女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <input type="date" id="date" class="form-control"  name="birthday">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit"  width="100" value="注册" name="submit" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

</body>
<script>
    $(function(){
        //页面加载完毕为id为username文本框绑定失去焦点事件
        $("#username").blur(function(){
            //获取到用户输入的信息
            var val=$(this).val();
            //alert(val);
            var vv=$.trim(val);
            if(vv!=""){
                //发送Ajax
                $.post("${pageContext.request.contextPath}/UserServlet",{"username":vv,"method":"userExists"},function(dt){
                    //alert(dt);
                    if(dt=="11"){
                        $("#username_span").html("用户已经存在");
                        $("#username_span").css("color","red");
                    }else{
                        $("#username_span").html("用户可以注册");
                        $("#username_span").css("color","green");
                    }

                });
            }
        });
    });
</script>
</html>



