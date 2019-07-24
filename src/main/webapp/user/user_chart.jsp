<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/17
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div id="userChart" style="width: 700px;height: 500px"></div>
<script>
    var userChart = echarts.init(document.getElementById('userChart'),'dark');
    option = {
        title:
            {
                text:'持明法洲APP用户地区分布图',
                subtext:'统计图',
                left:'center'
            },
        tooltip:{trigger:'item'},
        legend:{
            orient:'vertical',
            left:'left',
            data:['男','女']
        },
        visualMap:{
            min:0,
            max:10,
            left:'left',
            text:['高','低'],
            calculable:true
        },
        toolbox:{
            show:true,
            orient:'vertical',
            left:'right',
            top:'center',
            feature:{
                mark:{show:true},
                dataView:{show:true,readOnly:false},
                restore:{show:true},
                saveAsImage:{show:true}
            }
        }
    };
    userChart.setOption(option);
    //指定数据
    $.ajax({
        url:'${pageContext.request.contextPath}/user/showUserChart',
        type:'get',
        datatype:'json',
        success:function (response) {
            userChart.setOption({
                series:[
                    {
                        name:'男',
                        type:'map',
                        mapType:'china',
                        roam:false,
                        label:{
                            normal:{show:false},
                            emphasis:{show:true}
                        },
                        data:response.male
                    },
                    {
                        name:'女',
                        type:'map',
                        mapType:'china',
                        label:{
                            normal:{show:false},
                            emphasis:{show:true}
                        },
                        data:response.female
                    }
                ]
            })
        }
    })
</script>
