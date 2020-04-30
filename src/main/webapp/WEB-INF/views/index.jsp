<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>电影票管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
<!--描述：菜单栏-->
<div class="container-fluid">
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <li><a href="${pageContext.request.contextPath}/registerPage">注册</a></li>
        </ol>
    </div>
</div>
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">会员管理</a>
            </div>
        </div>
    </nav>
</div>
<div>
    <table  class="table">
        <tr>
            <td>会员id</td>
            <td>姓名</td>
            <td>性别</td>
            <td>生日</td>
        </tr>
        <c:forEach items="${users}" var="user">
<tr>
    <td>${user.username}</td>
    <td>${user.name}</td>
    <td>${user.sex}</td>
    <td>${user.birthday}</td>
</tr>
        </c:forEach>
    </table>
</div>
</body>

</html>