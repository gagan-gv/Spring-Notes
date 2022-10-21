<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>

<html>
    <head>
        <title>Security Home Page</title>
    </head>
    <body>
        <h2>Welcome to Home Page</h2>
        <hr />

        <p>
            Username: <security:authentication property="principal.username" />
        </p>

        <p>
              Roles: <security:authentication property="principal.authorities" />
        </p>

        <hr />

        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <button type="submit">Logout</button>
        </form:form>
    </body>
</html>