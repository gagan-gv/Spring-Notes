<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Login</title>
        <style>
            .failed {
                color: #f00;
            }
        </style>
    </head>
    <body>
        <h3>Sign In</h3>
        <form:form action="${pageContext.requests.contextPath}/processLogin" method="POST">
            <p>Username: <input type="text" name="username" /></p>
            <p>Password: <input type="password" name="password" /></p>
            <br />
            <c:if test="${param.error != null}">
                <i class="failed">Invalid Credentials</i>
                <br />
            </c:if>
            <input type="submit" value="Login" />
        </form:form>
    </body>
</html>