<%@ page contentType="text/html; ISO-8859-1" language="java" %>

<html>
<head>
    <title>Login</title>
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
</head>

<body>

<div class="container">
    <h1>HappyMeal Login</h1>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p class='alert'>" + message + "</p>");
        }
    %>
    <div class="col-xs-12 col-md-4">
        <form method="post" action="/login">
            <fieldset class="form-group">
                <label>
                    Username:
                </label>
                <input type="text" name="username" size="36" class="form-control"/>

            </fieldset>

            <fieldset class="form-group">
                <label>
                    Password:
                </label>
                <input type="password" name="password" size="36" class="form-control"/>
            </fieldset>

            <input type="submit" class='btn btn-primary' value="Login" />

        </form>
    </div>
</div>

</body>
</html>
