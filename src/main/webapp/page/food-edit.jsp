<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin user Examples</title>
    <meta name="description" content="这是一个 user 页面">
    <meta name="keywords" content="user">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>欢迎您,${sessionScope.mer.mName}</strong>
        <small>商家后台系统</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span
                    class="am-badge am-badge-warning">5</span></a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="${pageContext.request.contextPath}/page/merHome?mId=${sessionScope.mer.mId}"><span
                        class="am-icon-home"></span> 首页</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>
                        页面模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li><a href="${pageContext.request.contextPath}/page/merInfo" class="am-cf"><span
                                class="am-icon-check"></span> 店面资料<span
                                class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
                        <li><a href="#"><span class="am-icon-puzzle-piece"></span> 菜品编辑</a></li>
                        <li><a href="admin-gallery.html"><span class="am-icon-th"></span> 订单查看<span
                                class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
                        <li><a href="admin-log.html"><span class="am-icon-calendar"></span> 用户维护</a></li>
                        <%--<li><a href="admin-404.html"><span class="am-icon-bug"></span> 404</a></li>--%>
                    </ul>
                </li>
                <li><a href="admin-table.html"><span class="am-icon-table"></span> 任务</a></li>
                <li><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 日志</a></li>
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
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">编辑菜品</strong> /
                    <small>Edit Food</small>
                </div>
            </div>

            <hr/>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">
                            <div class="am-g">
                                <div class="am-u-md-4">
                                    <img id="food-pic" class="am-img-circle am-img-thumbnail"
                                         src="../${sessionScope.tbFood.fUrl}" alt=""/>
                                </div>
                                <div class="am-u-md-8">
                                    <p>请使用本地上传图片。 </p>
                                    <form class="am-form" method="post" enctype="multipart/form-data"
                                          action="${pageContext.request.contextPath}/food/editFoodPic?fId=${sessionScope.tbFood.fId}">
                                        <div class="am-form-group">
                                            <input type="file" id="user-pic" name="pictureFile"
                                                   onchange="showPic(this)">
                                            <p class="am-form-help">请选择要上传的文件...</p>
                                            <button type="submit" class="am-btn am-btn-primary am-btn-xs">保存</button>
                                        </div>
                                    </form>

                                    <script>
                                        function showPic(obj) {
                                            var newPreview = document.getElementById('food-pic');
                                            if (obj) {
                                                // ie 浏览器兼容
                                                if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                                                    obj.select();
                                                    newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);";
                                                    var path = document.selection.createRange().text;
                                                    var flag = judgeImgSuffix(path);
                                                    if (flag) {
                                                        newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = document.selection.createRange().text;
                                                    } else {
                                                        alert("要求图片格式为png,jpg,jpeg,bmp");
                                                    }
                                                    return;
                                                } else {
                                                    if (obj.files) {
                                                        //alert(obj.files.item(0).name);
                                                        if (judgeImgSuffix(obj.files.item(0).name)) {
                                                            newPreview.src = window.URL.createObjectURL(obj.files.item(0));
                                                            return;
                                                        } else {
                                                            alert("要求图片格式为png,jpg,jpeg,bmp");
                                                        }
                                                    }

                                                    return;
                                                }
                                            }

                                            function judgeImgSuffix(path) {
                                                var index = path.lastIndexOf('.');
                                                var suffix = "";
                                                if (index > 0) {
                                                    suffix = path.substring(index + 1);
                                                }
                                                if ("png" == suffix || "jpg" == suffix || "jpeg" == suffix || "bmp" == suffix || "PNG" == suffix || "JPG" == suffix || "JPEG" == suffix || "BMP" == suffix) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            }
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
                    <form class="am-form am-form-horizontal" method="post"
                          action="${pageContext.request.contextPath}/food/editFoodInfo?merId=${sessionScope.tbFood.mer.mId}">
                        <div class="am-form-group">
                            <label for="food-id" class="am-u-sm-3 am-form-label"><span style="color:red">*</span>编号 / ID</label>
                            <div class="am-u-sm-9">
                                <input type="text" id="food-id" name="fId" value="${sessionScope.tbFood.fId}"
                                       readonly="readonly">

                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label"><span style="color:red">*</span>名字 /
                                Name</label>
                            <div class="am-u-sm-9">
                                <input type="text" id="user-name" name="fName" value="${sessionScope.tbFood.fName}">

                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="food-price" class="am-u-sm-3 am-form-label"><span style="color:red">*</span>原价 /
                                Price</label>
                            <div class="am-u-sm-9">
                                <input type="text" id="food-price" name="fPrice" value="${sessionScope.tbFood.fPrice}">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="food-dprice" class="am-u-sm-3 am-form-label">折扣价 / DPrice</label>
                            <div class="am-u-sm-9">
                                <input type="text" id="food-dprice" name="fDPrice"
                                       value="${sessionScope.tbFood.fDPrice}">
                            </div>
                        </div>


                        <div class="am-form-group">
                            <div class="am-u-sm-9 am-u-sm-push-3">
                                <button type="submit" class="am-btn am-btn-primary">保存修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <footer class="admin-content-footer">
            <hr>
            <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
        </footer>

    </div>
    <!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<footer>
    <hr>
    <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>

<script src="assets/js/app.js"></script>
</body>
</html>