<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<div align="right">
    <c:forEach var="msq" items="${users}">
        <h3>${msq}</h3>
    </c:forEach>
</div>
<h1>Add new User</h1>
<form method="post">
    Name: <input type="text" name="name">
    <br>
    <br>
    Last Name: <input type="text" name="lastName">
    <br>
    <br>
    Email: <input type="email" name="email">
    <br>
    <br>
    Age: <input type="number" name="age">
    <br>
    <br>
    <input type="submit" name="Add" />
</form>
<br>
<br>
<br>
<h1>Delete User</h1>
<form method="post" action="${pageContext.request.contextPath}/users/delete">
    Name: <input type="text" name="name">
    <br>
    <br>
    <input type="submit" name="Delete" />
</form>
<br>
<br>
<br>
<h1>Change User:</h1>
<br>
<form method="post" action="${pageContext.request.contextPath}/users/change">
    Name: <input type="text" name="name">
    <br>
    <br>
    Last Name: <input type="text" name="lastName">
    <br>
    <br>
    Email: <input type="email" name="email">
    <br>
    <br>
    Age: <input type="number" name="age">
    <br>
    <br>
    <input type="submit" name="Change" />
</form>

</body>
</html>
