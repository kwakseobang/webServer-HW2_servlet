<%--
  Created by IntelliJ IDEA.
  User: kwakseobang
  Date: 11/6/24
  Time: 12:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/signup/error" method="post">
  <h2>username이 존재합니다. 다른 username으로 회원가입해주세요</h2>
  <button type="submit" name="action" value="login">Login Page</button>
  <button type="submit" name="action" value="signup">SignUp Page</button>
</form>
</body>
</html>
