<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>菜品编辑</title>
    <meta name="description" content="这是一个 table 页面">
    <meta name="keywords" content="table">
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
                        <li><a href="${pageContext.request.contextPath}/mer/queryOrders.do?mId=${sessionScope.mer.mId}"><span class="am-icon-th"></span> 订单查看<span
                                class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
                        <%--<li><a href="admin-log.html"><span class="am-icon-calendar"></span> 用户维护</a></li>--%>
                        <%--<li><a href="admin-404.html"><span class="am-icon-bug"></span> 404</a></li>--%>
                    </ul>
                </li>
                <%--<li><a href="admin-table.html"><span class="am-icon-table"></span> 任务</a></li>
                <li><a href="admin-form.html"><span class="am-icon-pencil-square-o"></span> 日志</a></li>--%>
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
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">菜品编辑</strong> /
                    <small>Edit</small>
                </div>
            </div>

            <hr>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <button type="button" class="am-btn am-btn-default" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 550, height: 450}"><span class="am-icon-plus"></span> 新增
                            </button>

                            <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除
                            </button>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3">
                    <div class="am-form-group">

                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3">
                    <div class="am-input-group am-input-group-sm">
                        <input type="text" class="am-form-field">
                        <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
                    </div>
                </div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12">
                    <form class="am-form">
                        <table class="am-table am-table-striped am-table-hover table-main" id="foodList">
                            <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox"/></th>
                                <th class="table-id">ID</th>
                                <th class="table-title">菜品名</th>
                                <th class="table-type">类别</th>
                                <th class="table-author am-hide-sm-only">商家</th>
                                <th class="table-type">原价</th>
                                <th class="table-author am-hide-sm-only">折扣价</th>
                                <th class="table-set">图片</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.pageBean.lists}" var="food">
                                <tr>
                                    <td><input type="checkbox"/></td>
                                    <td>${food.fId}</td>
                                    <td><a href="${pageContext.request.contextPath}/food/editFood?fId=${food.fId}">${food.fName}</a></td>
                                    <td>${food.foodType.ftName}</td>
                                    <td class="am-hide-sm-only">${food.mer.mName}</td>
                                    <td>${food.fPrice}</td>
                                    <td class="am-hide-sm-only">${food.fDPrice}</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <img id="food-pic" height="100" width="100" class="am-img-circle am-img-thumbnail" src="../${food.fUrl}" alt=""/>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="am-cf">
                            共 ${sessionScope.pageBean.totalCount} 条记录
                            <div class="am-fr">
                                <ul class="am-pagination">
                                    <li class="am-disabled"><a href="#">«</a></li>
                                    <c:forEach var="i" begin="1" end="${sessionScope.pageBean.totalPage}" step="1">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/food/queryPage.do?pid=${i}&mId=${sessionScope.mer.mId}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="am-disabled"><a href="#">»</a></li>
                                </ul>
                            </div>
                        </div>

                        <%--<button
                                type="button"
                                class="am-btn am-btn-primary"
                                data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 225}">
                            Modal
                        </button>--%>

                        <hr/>
                        <p>注：为保障菜品信息的正确性,建议您使用PC端进行操作!</p>
                    </form>
                </div>
            </div>
        </div>

        <div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
            <div class="am-modal-dialog">
                <div class="am-modal-hd">新增菜品
                    <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                </div>
                <div class="am-modal-bd">
                    <form class="am-form am-form-horizontal" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/food/addFood?mId=${sessionScope.mer.mId}">
                        <div class="am-form-group">
                            <label for="user-name" class="am-u-sm-3 am-form-label"><span style="color:red">*</span>菜品名</label>
                            <div class="am-u-sm-8">
                                <input type="text" id="user-name" name="fName" placeholder="输入菜品名 / Name">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="food-type" class="am-u-sm-3 am-form-label"><span style="color:red">*</span>种类</label>
                            <div class="am-u-sm-8">
                                <select id="food-type" name="ftId" data-am-selected="{btnSize: 'sm'}">
                                    <option value="0">所有类别</option>
                                    <c:forEach items="${sessionScope.types}" var="type">
                                        <option value="${type.ftId}">${type.ftName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="food-price" class="am-u-sm-3 am-form-label"><span style="color:red">*</span>原价</label>
                            <div class="am-u-sm-8">
                                <input type="text" id="food-price" name="fPrice" placeholder="输入原价 / Price">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label for="food-dprice" class="am-u-sm-3 am-form-label">折扣</label>
                            <div class="am-u-sm-8">
                                <input type="text" id="food-dprice" name="fDPrice" placeholder="输入折扣价 / DPrice">
                            </div>
                        </div>

                        <div class="am-form-group">
                            <img id="food-picture" width="120px" height="120px" class="am-img-circle am-img-thumbnail am-u-sm-4" src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/200/h/200/q/80" alt=""/>
                            <div class="am-u-sm-8" style="margin-top: 50px;">
                                <input type="file" name="pictureFile" id="user-pic" onchange="showPic(this)">
                                <p class="am-form-help">请选择要上传的文件...</p>
                                <button type="submit" onclick="addFood(this.form)" class="am-btn am-btn-primary am-btn-xs">提交</button>
                            </div>
                        </div>
                    </form>

                    <script>

                        function showPic(obj) {
                            var newPreview = document.getElementById('food-picture');
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
                                }  else {
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
                                if ("png"==suffix || "jpg"==suffix || "jpeg"==suffix || "bmp"==suffix || "PNG"==suffix || "JPG"==suffix || "JPEG"==suffix || "BMP"==suffix) {
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

        <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
            <div class="am-modal-dialog">
                <div class="am-modal-hd">警告</div>
                <div class="am-modal-bd">
                    你，确定要删除这条记录吗？
                </div>
                <div class="am-modal-footer">
                    <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                    <span class="am-modal-btn" data-am-modal-confirm>确定</span>
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

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<footer>
    <hr>
    <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
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
