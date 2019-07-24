<%--
  Created by IntelliJ IDEA.
  User: L
  Date: 2019/7/9
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!DOCTYPE html>
<!--lang的作用： 给浏览器翻译指定原始的语言，给屏幕阅读软件指定阅读的语言-->
<html lang="zh-CN">
<head>
    <!--编码格式-->
    <meta charset="utf-8">
    <!--跨浏览器的兼容性-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--移动设备优先-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>持明法洲</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/login/assets/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/login/assets/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/login/assets/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/src/jquery.jqGrid.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
    <%--ECHART--%>
    <script src="${pageContext.request.contextPath}/statics/echarts.min.js"></script>
    <%--引入echarts的地图js文件--%>
    <script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js"></script>
    <style type="text/css">
        .ui-th-div{
            height: 50px;
            text-align: center;
            vertical-align: text-top;
        }
    </style>
</head>
<body>
<!--导航-->
<div class="navbar navbar-inverse">
    <div class="container-fluid">
        <a href="" class="navbar-brand navbar-left"><span>持明法洲后台管理系统</span></a>
            <a href="${pageContext.request.contextPath}/admin/logout"><span
                    class="navbar-brand navbar-right">安全退出</span></a>
            <span class="navbar-brand navbar-right">欢迎${admin.adminNickname}&emsp;</span>
    </div>

</div>

<div class="container-fluid">

    <div class="row">
        <div class="col-md-3">
            <div class="panel-group" id="pg">
                <%--轮播图开始--%>
                <div class="panel panel-default" id="deptPanel">
                    <div class="panel panel-heading">
                        <a href="#pd1" class="panel-title  text-center" data-toggle="collapse" data-parent="#pg"><h4>轮播图管理</h4></a>
                    </div>
                    <div class="panel-collapse collapse in " id="pd1">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item text-center"><a href="javascript:$('#RightLayout').load('carousel/carousel_show.jsp')" data-toggle="modal">所有轮播图</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <%--专辑开始--%>
                <div class="panel panel-default" id="empPanel">
                    <div class="panel panel-heading">
                        <a href="#pd2" class="panel-title text-center" data-toggle="collapse" data-parent="#pg"><h4>专辑管理</h4></a>
                    </div>
                    <div class="panel-collapse collapse" id="pd2">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item text-center"><a href="javascript:$('#RightLayout').load('album/album_show.jsp')" id="">查询所有专辑</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <%--文章管理开始--%>
                <div class="panel panel-default" id="empPanel">
                    <div class="panel panel-heading">
                        <a href="#pd3" class="panel-title text-center" data-toggle="collapse" data-parent="#pg"><h4>文章管理</h4></a>
                    </div>
                    <div class="panel-collapse collapse" id="pd3">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item text-center"><a href="javascript:$('#RightLayout').load('article/article_show.jsp')">查询所有文章</a></li>
                                <li class="list-group-item text-center"><a href="javascript:$('#RightLayout').load('article/subscribe_article.jsp')">已订阅文章</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <%--用户管理开始--%>
                <div class="panel panel-default" id="empPanel">
                    <div class="panel panel-heading">
                        <a href="#pd4" class="panel-title text-center" data-toggle="collapse" data-parent="#pg"><h4>用户管理</h4></a>
                    </div>
                    <div class="panel-collapse collapse" id="pd4">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item text-center"><a href="javascript:$('#RightLayout').load('user/user_show.jsp')">查询所有用户</a></li>
                                <li class="list-group-item text-center"><a href="${pageContext.request.contextPath}/user/exportUserFile">导出用户信息表</a></li>
                                <li class="list-group-item text-center"><a href="javascript:$('#RightLayout').load('user/user_chart.jsp')">查看图表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%----%>
        <div class="col-md-9" id="RightLayout">
            <%--巨幕开始--%>
            <div class="jumbotron">
                <h3>欢迎来到持明法洲后台管理系统！</h3>
            </div>
            <%--图片开始--%>
                <div>
                    <img src="${pageContext.request.contextPath}/login/assets/img/backgrounds/62.jpg" alt="" style="width:100%">
                </div>
    </div>

    </div>
</div>
<div class="panel-footer" align="center">
    <p >持明法洲后台管理系统@WPL 2019.7</p>
</div>

</body>
</html>
