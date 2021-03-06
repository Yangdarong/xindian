<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>欢迎登录商家后台</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="page/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="page/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="page/assets/css/admin.css">
</head>
<body>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>欢迎您,${sessionScope.mer.mName}</strong> <small>商家后台系统</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 商家用户 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="${pageContext.request.contextPath}/page/merHome?mId=${sessionScope.mer.mId}"><span class="am-icon-home"></span> 首页</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 页面模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li><a href="${pageContext.request.contextPath}/page/merInfo" class="am-cf"><span class="am-icon-check"></span> 店面资料<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
                        <li><a href="${pageContext.request.contextPath}/food/queryFoods.do?mId=${sessionScope.mer.mId}"><span class="am-icon-puzzle-piece"></span> 菜品编辑</a></li>
                        <li><a href="${pageContext.request.contextPath}/mer/queryOrders.do?mId=${sessionScope.mer.mId}"><span class="am-icon-th"></span> 订单查看<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
                        <%--<li><a href="admin-log.html"><span class="am-icon-calendar"></span> 用户维护</a></li>--%>
                        <%--<li><a href="admin-404.html"><span class="am-icon-bug"></span> 404</a></li>--%>
                    </ul>
                </li>
                <%--<li><a href="admin-table.html"><span class="am-icon-table"></span> 任务</a></li>--%>
                <%--<li><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 日志</a></li>--%>
                <li><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li>
            </ul>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark"></span> 公告</p>
                    <p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
                </div>
            </div>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-tag"></span> wiki</p>
                    <p>Welcome to the Amaze UI wiki!</p>
                </div>
            </div>
        </div>
    </div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>常用模块</small></div>
            </div>

            <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
                <li><a href="${pageContext.request.contextPath}/food/queryFoods.do?mId=${sessionScope.mer.mId}" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>菜品编辑<br/></a></li>
                <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>订单查看<br/></a></li>
                <li><a href="javascript:void(0);" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>昨日访问<br/></a></li>
                <li><a href="#" class="am-text-secondary"><span class="am-icon-btn am-icon-user-md"></span><br/>用户维护<br/></a></li>
            </ul>

            <div class="am-g">
                <div class="am-u-sm-12">
                    <table class="am-table am-table-bd am-table-striped admin-content-table">
                        <thead>
                        <tr>
                            <th>ID</th><th>用户名</th><th>订购菜品</th><th>下单时间</th><th>状态</th><th>管理</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.orders}" var="order">
                            <tr><td>${order.oId}</td><td>${order.user.uSignature}</td><td><a href="javascript:void(0);">${order.orderFood.food.fName}</a></td><td>${order.orderUser.ouPayTime}</td>
                                <td>
                                <c:if test="${order.oState == 4}">
                                    <span class="am-badge am-badge-danger">待处理</span>
                                    <%--<span class="am-badge am-badge-success">+20</span>--%>
                                </c:if>
                                <c:if test="${order.oState == 5}">
                                    <span class="am-badge am-badge-warning">处理中</span>
                                    <%--<span class="am-badge am-badge-success">+20</span>--%>
                                </c:if>
                                <c:if test="${order.oState == 6}">
                                    <span class="am-badge am-badge-badge">用户取消</span>
                                    <%--<span class="am-badge am-badge-success">+20</span>--%>
                                </c:if>
                                <c:if test="${order.oState == 7}">
                                    <span class="am-badge am-badge-secondary">送餐中</span>
                                    <%--<span class="am-badge am-badge-success">+20</span>--%>
                                </c:if>
                                <c:if test="${order.oState == 8}">
                                    <span class="am-badge am-badge-success">订单完成</span>
                                    <%--<span class="am-badge am-badge-success">+20</span>--%>
                                </c:if>
                                </td>
                                <td>
                                <c:if test="${order.oState > 3}">
                                    <div class="am-dropdown" data-am-dropdown>
                                        <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                                        <ul class="am-dropdown-content">
                                            <li><a href="${pageContext.request.contextPath}/mer/operatorOrder.do?oId=${order.oId}&oState=${order.oState}">1. 执行</a></li>
                                            <li><a href="${pageContext.request.contextPath}/mer/toEditOrder.do?oId=${order.oId}">2. 编辑</a></li>
                                            <li><a href="${pageContext.request.contextPath}/mer/cancelOrder.do?oId=${order.oId}">3. 取消</a></li>
                                        </ul>
                                    </div>
                                </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="am-g">




                <div class="am-u-md-6">
                    <div class="am-panel am-panel-default">

                    </div>


                </div>
                <div class="am-u-md-6">
                    <div class="am-panel am-panel-default">

                    </div>
                </div>
            </div>
        </div>

        <footer class="admin-content-footer">
            <hr>
            <p class="am-padding-left">© 2019 AllMobilize, Inc. Licensed under MIT license.</p>
        </footer>
    </div>
    <!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="page/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="page/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="page/assets/js/amazeui.min.js"></script>
<script src="page/assets/js/app.js"></script>
</body>
</html>
