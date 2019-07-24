<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/12
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .panel-body{
        padding: 0px;
    }
    .ui-pg-input{
        float: left;
    }
    .form-control{
        height: 20px;
    }
    .ui-jqgrid-caption{
        background-color: #a4ceeb;
        color: #564800;
        text-shadow: 1px 0px 1px black;
    }
    .ui-jqgrid-titlebar{
        background-color: #a4ceeb;
        color: #564800;
        text-shadow: 1px 0px 1px black;
    }
</style>
<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#users" aria-controls="home" role="tab" data-toggle="tab">所有用户</a>
        </li>
        <li role="presentation">
            <a href="javascript:$('#').load('user/user_chart.jsp')" aria-controls="showChart" role="tab" data-toggle="tab">查看图表</a>
        </li>
    </ul>
</div>
<table class="table table-bordered table-hover" id="users" ></table>
<div id="pager_user" style="height: 40px"></div>
<script type="text/javascript">
    $('#users').jqGrid({
        styleUI:'Bootstrap',
        url:'${pageContext.request.contextPath}/user/showAllUsers',
        datatype:'json',
        colNames:['编号','手机号','用户名','用户头像','真实姓名','性别','省份','城市','状态','个人简介','注册时间','上师'],
        colModel:[{name:'id',align:'center',hidden:true},
            {name:'userMobile',align:'center'},
            {name:'userName',align:'center'},
            {name:'userPhoto',align:'center',formatter:function (value, option, row) {
                    return '<img src="${pageContext.request.contextPath}/user/images/'+row.userPhoto+'" style="height:50px"/>';
                }},
            {name:'userRealname',align:'center'},
            {name:'userSex',align:'center'},
            {name:'userProvince',align:'center'},
            {name:'userCity',align:'center'},
            {name:'userStatus',align:'center'},
            {name:'userDescription',align:'center'},
            {name:'userRegistDate',align:'center'},
            {name:'masterId',align:'center'}],
        pager:'#pager_user',
        page:1,
        rowList:[5,10,20],
        rowNum:5,
        sortname:'id',
        height:400,
        autowidth:true,
        viewrecords:true,
        caption:'用户的详细信息'
    }).navGrid('#pager_user',{add:false,edit:false,del:false,refersh:true})
</script>
