<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 2016-06-13
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
</head>
<body>
<body>
<div class="container">
    <h1 class="page-header">HappyMeal Login</h1>
    <%
   String message = (String) request.getAttribute("message");
   if (message != null) {
      out.println("<p class='alert'>" + message + "</p>");
   }
%>
    <div class="col-xs-12 col-md-4 ">
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

    <fieldset class="form-group">
        <input type="submit" class='btn btn-primary' value="Login" />
    </fieldset>

</form>
        </div>
    </div>
</html>
</body>
</html>
