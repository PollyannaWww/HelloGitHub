<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/17
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/editor/lang/zh-CN.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/editor/themes/default/default.css">
    <script src="${pageContext.request.contextPath}/login/assets/js/jquery-2.2.1.min.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        KindEditor.ready(function (K) {
            window.editor = K.create('#articleContent',{
                width:'700px',
                height:'500px'
            })
        });
        var goEasy = new GoEasy({
            appkey:"BS-083f780ef46f45d7b09b2c45f0057e7f"
        });
        goEasy.subscribe({
            channel:"test_wpl",
            onMessage:function (message) {
               KindEditor.html("#articleContent",message.content)
                //alert("您有来自："+message.channel+"的新消息："+message.content);
            }
        })

    </script>
</head>
<body>
    <%--kindeditor--%>
    <textarea id="articleContent" name="articleContent" style="width: 750px;height: 800px;"></textarea>
</body>


</html>
