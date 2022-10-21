<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>

<html>
    <head>
        <title>Security Home Page</title>
    </head>
    <body>
        <h2>Welcome to Home Page</h2>
        <hr />

        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <button type="submit">Logout</button>
        </form:form>
    </body>
</html>