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
<h1>Login</h1>
    <%
   String message = (String) request.getAttribute("message");
   if (message != null) {
      out.println("<p>" + message + "</p>");
   }
%>

<form method="post" action="login">
    <div>
        Username: <input type="text" name="username" size="36" />

    </div>
    <div>
        Password: <input type="password" name="password" size="36" />

    </div>
    <div>
        <input type="submit" value="Login" />
    </div>

</form>
</html>
</body>
</html>
