<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎使用鑫点后台系统,请先登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="alternate icon" type="image/png" href="${basePath}/page/assets/i/favicon.png">
    <link rel="stylesheet" href="${basePath}/page/assets/css/amazeui.min.css"/>
    <script src="${basePath}/page/assets/js/jquery.min.js"></script>
    <style>
        .header {
            text-align: center;
        }

        .header h1 {
            font-size: 200%;
            color: #333;
            margin-top: 30px;
        }

        .header p {
            font-size: 14px;
        }
    </style>

    <script>


        function login(loginform) { // 传入表单
            if (loginform.user.value === "") {
                alert("请输入账号!");
                loginform.user.focus();
                return false;
            }
            if (loginform.password.value === "") {
                alert("请输入密码!");
                loginform.password.focus();
                return false;
            }

        }
    </script>
</head>
<body>
<div class="header">
    <div class="am-g">
        <h1>鑫点后台系统</h1>

    </div>
    <hr/>
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <h2>登录</h2>
        <hr>
        <form name="loginform" id="loginfrom" method="post" class="am-form" action="${pageContext.request.contextPath}/mer/queryMer.do">
            <label for="user">账号:</label>
            <input type="text" name="mLoginId" id="user" value="">
            <br>
            <label for="password">密码:</label>
            <input type="password" name="mPassword" id="password" value="">
            <br>
            <label for="remember-me">
                <input id="remember-me" type="checkbox">
                记住密码
            </label>
            <br/>
            <div class="am-cf">
                <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl" onsubmit="login(this.form);">
                <input type="submit" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
            </div>
        </form>
        <hr>
        <p>© 2019 AllMobilize, Inc. Licensed under MIT license.</p>
    </div>
</div>
</body>
</html>
