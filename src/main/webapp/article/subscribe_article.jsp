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
    <script src="${pageContext.request.contextPath}/login/assets/js/jquery-2.2.1.min.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            appkey:"BS-083f780ef46f45d7b09b2c45f0057e7f"
        });
        alert(goEasy);
        // alert(appkey);
        goEasy.subscribe({
            channel:"test_wpl",
            onMessage:function (message) {

                alert("您有来自："+message.channel+"的新消息："+message.content);
            }
        })

    </script>
</head>
<body>
HELLO WORLD
</body>


</html>
