<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<html>
<head>
    <script src="statics/echarts.min.js"></script>
    <script src="statics/boot/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<div id="main" style="height: 500px;width: 700px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'),'light');

    // 指定图表的配置项和数据
    var option = {
        //color: ['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
