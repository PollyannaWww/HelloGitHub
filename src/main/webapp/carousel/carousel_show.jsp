<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/10
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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

<div class="panel panel-primary" id="showCarousel" style="height: auto">
    <div class="panel-heading">
    </div>
    <div class="panel-body">
        <table class="table table-bordered table-hover" id="carousels">
            <%--轮播图表格体--%>
        </table>
    </div>
    <div class="panel-footer" id="pager1">
        <%--分页--%>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#carousels').jqGrid({
            styleUI:'Bootstrap',
            url:'/cmfz/carousel/showAllCarousels',
            datatype:'json',
            colNames:["编号","名称","图片","简介","状态"],
            autowidth:true,
            celledit:true,
            editurl:'/cmfz/carousel/editCarousel',
            cellurl:'/cmfz/carousel/editCarousel',
            colModel:[{name:'id',align:'center',editable:false},
                {name:'carouselName',align:'center',editable:true,edittype:'text',resizable:true},
                {name:'carouselPicture',editable:true,edittype:'file',formatter:function (value,option,row) {
                        return '<img src="/cmfz/carousel/img/'+row.carouselPicture+'"style="height:70px">';
                    }},
                {name:'carouselDescription',align:'center',editable:true,edittype:'textarea',resizable:true},
                {name:'carouselStatus',align:'center',editable:true,edittype:'select',editoptions:{value:'1:正常;0:冻结'},resizable:true}],
            mtype:'POST',
            pager:'#pager1',
            rowList:[5,10,20,40],
            rowNum:5,
            viewrecords:true,
            sortname:'id',
            sortorder:'asc',
            caption:'所有轮播图',
            height:500,
            hidegrid:true,
            page:1,
            rownumbers:true

        }).navGrid('#pager1',{edit:true,add:true,del:true,refresh:true},{
            //控制修改的额外操作
            closeAfterEdit:true,
            beforeShowForm:function (frm) {
                frm.find("#carouselPicture").attr("disabled",true);
            }
        },{
            //控制添加的额外操作
            closeAfterAdd:close,
            afterSubmit:function (response) {
                var status = response.responseJSON.status; //返回map中的status
                var message = response.responseJSON.message;//返回map中的message，保存的是插入的轮播图的id
                //console.log("status==="+status+",message==="+message);
                //console.log(carouselPicture);
                if(status){
                    //文件上传
                    $.ajaxFileUpload({
                        url:'/cmfz/carousel/upload',
                        type:'POST',
                        fileElementId:"carouselPicture",
                        data:{id:message},
                        success:function () {
                            //刷新页面
                            $("#carousels").trigger("reloadGrid");
                            console.log("ok");
                        }
                    })
                }
                return "123";
            }
        },{});
    });

</script>