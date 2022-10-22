<!DOCTYPE HTML>

<html>
    <head>
        <title>Leaders page</title>
    </head>
    <body>
        <h2>Welcome to Leaders Page</h2>
        <hr />
        <form:form action="${pageContext.request.contextPath}/" method="POST">
                <button type="submit">Back to Home</button>
        </form:form>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <button type="submit">Logout</button>
        </form:form>
    </body>
</html>