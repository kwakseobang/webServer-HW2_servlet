<%--
  Created by IntelliJ IDEA.
  User: kwakseobang
  Date: 11/6/24
  Time: 12:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
  <input type="text" name="username">
  <input type="password" name="password">
  <button type="submit">Login</button>
</form>

<form action="/signup" method="get">

  <button type="submit">signup</button>
</form>
</body>
</html>
