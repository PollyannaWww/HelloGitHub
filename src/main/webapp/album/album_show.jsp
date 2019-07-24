<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/11
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/login/assets/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid.css" type="text/css">

<script src="${pageContext.request.contextPath}/login/assets/js/jquery-2.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/login/assets/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/src/jquery.jqGrid.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
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
<table id="albums"></table>
<div id="pager_album" style="height: 40px"></div>
<script>
    $('#albums').jqGrid({
        styleUI:'Bootstrap',
        url:'/cmfz/album/showAllAlbums',
        datatype:'json',
        colNames:['编号','名称','封面图','集数','评分','作者','播音员','简介','发布日期'],
        colModel:[{name:'id',hidden:true},
            {name:'albumName',editable:true,edittype:'text'},
            {name:'albumCover',align:'center',editable:true,edittype:'file',formatter:function (value, option, row) {
                    return '<img src="/cmfz/album/covers/'+row.albumCover+'" style="height:70px">';
                }},
            {name:'albumCount',align:'center'},
            {name:'albumScore',align:'center',editable:true,edittype:'text'},
            {name:'albumAuthor',align:'center',editable:true,edittype:'text'},
            {name:'albumAnnouncer',align:'center',editable:true,edittype:'text'},
            {name:'albumDescription',align:'center',editable:true,edittype:'text'},
            {name:'albumPubDate',align:'center'}],
        autowidth:true,
        height:500,
        rowNum:5,
        sortname:'id',
        rowList:[5,10,20],
        pager:'#pager_album',
        viewrecords:true,
        page:1,
        caption:'专辑的详细信息',
        editurl:'${pageContext.request.contextPath}/album/editAlbum',
        subGrid:true,
        subGridRowExpanded : function(subgrid_id, row_id) {
            var subgrid_table_id = subgrid_id + "_t";
            var pager_id = "p_" + subgrid_table_id;
            $("#" + subgrid_id).html(
                "<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
            jQuery("#" + subgrid_table_id).jqGrid(
                {
                    url: '/cmfz/audio/showAudiosByAlbumId?albumId=' + row_id,
                    datatype: "json",
                    colNames: ['编号', '名称', '文件大小', '时长', '集序','在线播放'],
                    colModel: [
                        {name: "id", hidden:true},
                        {name: "audioName", align: 'center',editable:true,edittype:'text'},
                        {name: "audioSize", align: 'center',formatter:function (value, option, row) {
                                return row.audioSize;
                            }},
                        {name: "audioDuration", align: 'center'},
                        {name: "audioOrder", align: 'center',editable:true,edittype:'text'},
                        {name:"audioLocation",align:'center',width:330,editable:true,edittype:'file',formatter:function (value,options,row) {
                            //console.log(row);
                                return "<audio controls loop>\n"+
                                    "<source src='${pageContext.request.contextPath}/album/audios/"+row.audioLocation+"' type='audio/mp3'>\n"+
                                    "</audio>";
                            }}
                    ],
                    rowNum: 5,
                    pager: pager_id,
                    sortname: 'id',
                    sortorder: "asc",
                    height: '150%',
                    styleUI:'Bootstrap',
                    autowidth:true,
                    editurl:'${pageContext.request.contextPath}/audio/editAudio?albumId='+row_id
                });
            jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                "#" + pager_id, {
                    edit: false,
                    add: true,
                    del: false
                },{
                //修改
                },{
                    //控制添加
                    closeAfterAdd:close,
                    afterSubmit:function (response) {
                        var status = response.responseJSON.status;
                        var id = response.responseJSON.message;
                        if(status){
                            $.ajaxFileUpload({
                                url:'${pageContext.request.contextPath}/audio/uploadAudio',
                                fileElementId:"audioLocation",
                                data:{id:id},
                                type:'POST',
                                success:function () {
                                    $("#albums").trigger("reloadGrid");
                                }
                            })
                        }
                        return "123";
                    }
                },{
                //删除
                });
        }
    }).navGrid('#pager_album',{edit:true,add:true,del:true,search:false,refresh:true},
        {
            //控制修改

        },{
        //控制添加
            closeAfterAdd:close,
            afterSubmit:function (response) {
                var status = response.responseJSON.status;
                var id = response.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:'${pageContext.request.contextPath}/album/upload',
                        fileElementId:"albumCover",
                        data:{id:id},
                        type:'POST',
                        success:function () {
                            $("#albums").trigger("reloadGrid");
                        }
                    })
                }
                return "123";
            }
        },{
        //控制删除
        })
</script>