<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/12
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/lang/zh-CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/editor/themes/default/default.css">
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
<%--选项卡--%>
<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">所有文章</a>
        </li>
        <li role="presentation">
            <a href="#addArticle" aria-controls="addArticle" role="tab" data-toggle="tab" onclick="openModal('add')">添加文章</a>
        </li>
    </ul>
</div>
<%--展示所有文章--%>
<table class="table table-bordered table-hover" id="articles"></table>
<div id="pager_article" style="height: 40px;"></div>
<%--添加和修改文章的模态框--%>
<div class="modal fade" tabindex="-1" role="dialog" id="articleMd">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 800px">
            <%--头部--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>编辑文章</h4>
            </div>
            <%--内容--%>
            <div class="modal-body">
                <form class="form-horizontal" id="articleForm">
                    <input type="hidden" id="articleId" name="id">
                    <input type="hidden" id="articleOper" name="oper">
                    <%--输入标题--%>
                    <div class="form-group">
                        <label for="articleTitle" class="col-sm-3 control-label">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" name="articleTitle" id="articleTitle" placeholder="文章标题">
                        </div>
                    </div>
                    <%--输入作者--%>
                    <div class="form-group">
                        <label for="articleAuthor" class="col-sm-3 control-label">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" name="authorName" id="articleAuthor" placeholder="文章作者">
                        </div>
                    </div>
                    <%--输入文章内容--%>
                    <div class="form-group">
                        <%--kindeditor--%>
                        <textarea id="articleContent" name="articleContent" style="width: 750px;height: 800px;"></textarea>
                    </div>
                </form>
            </div>
            <%--脚部--%>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //展示所有文章
    $('#articles').jqGrid({
        styleUI:'Bootstrap',
        url:'${pageContext.request.contextPath}/article/showAllArticles',
        datatype:'json',
        colNames:['编号','文章标题','发布日期','内容','作者','操作'],
        colModel:[{name:'id',align:'center',hidden:true},
            {name:'articleTitle',align:'center'},
            {name:'articlePubDate',align:'center'},
            {name:'articleContent',align:'center'},
            {name:'authorName',align:'center'},
            {name:'aa',formatter:function (value, option, row) {
                    return "<a class='btn btn-warning' onclick=\"openModal('edit','"+row.id+"')\">修改</a>";
                }}],
        page:1,
        rowList:[5,10,20],
        rowNum:5,
        height:300,
        autowidth:true,
        pager:'#pager_article',
        viewrecords:true,
        caption:'文章的详细信息',
        sortname:'id',
        editurl:'${pageContext.request.contextPath}/article/editArticle'
    }).navGrid('#pager_article',{edit:false,add:false,del:true,refersh:true});


    //打开模态框
    function openModal(oper,id){
        KindEditor.html("#articleContent","");
        var article = $('#articles').jqGrid("getRowData",id);
        //给表单设置默认值
        $('#articleId').val(article.id);
        $('#articleTitle').val(article.articleTitle);
        $('#articleAuthor').val(article.authorName);
        KindEditor.html('#articleContent',article.articleContent);
        $('#articleOper').val(oper);
        $('#articleMd').modal("show");
    }


    //初始化kind editor
    KindEditor.create('#articleContent',{
        width:'770px',
        height:'500px',
        allowFileManager:true,
        //图片空间的地址
        fileManagerJson:'${pageContext.request.contextPath}/article/browser',
        //上传图片的地址
        uploadJson:'${pageContext.request.contextPath}/article/upload',
        //上传图片时接受的参数
        filePostName:'articleImage',
        resizeType:1,
        //同步kind editor的值到text area文本框
        afterBlur:function () {
            this.sync();
        }
    });
    //添加文件
    function save() {
        $.ajax({
            url:'${pageContext.request.contextPath}/article/editArticle',
            type:'POST',
            data:$('#articleForm').serialize(),
            dataType:'json',
            success:function () {
                $('#articleMd').modal("hide");
                $('#articles').trigger("reloadGrid");

            }
        })
    }
</script>
