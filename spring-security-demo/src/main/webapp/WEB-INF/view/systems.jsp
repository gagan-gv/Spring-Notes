<!DOCTYPE HTML>

<html>
    <head>
        <title>Systems page</title>
    </head>
    <body>
        <h2>Welcome to Systems Page</h2>
        <hr />
        <form:form action="${pageContext.request.contextPath}/" method="POST">
                <button type="submit">Back to Home</button>
        </form:form>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <button type="submit">Logout</button>
        </form:form>
    </body>
</html>