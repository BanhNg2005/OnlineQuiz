<%--
  Created by IntelliJ IDEA.
  User: congh
  Date: 2025-04-05
  Time: 3:48 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h2>Register for Online Quiz</h2>
<form action="SignUp" method="GET">
    <label>Username:</label>
    <input type="text" name="username" required><br>

    <label>Password:</label>
    <input type="password" name="password" required><br>

    <label>Email:</label>
    <input type="email" name="email" required><br>

    <input type="submit" value="Sign Up">
</form>

<p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>
