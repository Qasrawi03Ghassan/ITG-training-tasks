<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div style="position: absolute; top: 35%; left:50%; transform: translate(-50%,-50%);width: 300px; margin: 100px auto; text-align: center; border: 1px solid #ccc; padding: 20px; border-radius: 25px;">
        <h2>Login</h2>

        <c:if test="${param.error != null}">
            <p style="color:red;">Invalid username or password</p>
        </c:if>

        <c:if test="${param.logout != null}">
            <p style="color:green;">You have been logged out successfully.</p>
        </c:if>

        <form action="<c:url value='/perform-login'/>" method="post">
            <div style="margin-bottom: 10px;">
                <label for="username">Username:</label><br/>
                <input type="text" id="username" name="username" required style="width: 100%; padding: 5px;"/>
            </div>

            <div style="margin-bottom: 10px;">
                <label for="password">Password:</label><br/>
                <input type="password" id="password" name="password" required style="width: 100%; padding: 5px;"/>
            </div>
            <button type="submit" style="width: 100%; padding: 8px; background-color: #007bff; color: white; border: none; border-radius: 3px;">
                Login
            </button>
        </form>
    </div>
</body>
</html>
