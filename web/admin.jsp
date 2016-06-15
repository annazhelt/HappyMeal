<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 2016-06-14
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage All Restaurants!</title>
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<p>" + message + "</p>");
    }
%>
<form method="post" action="/newRestaurant">
    <h1>Create a new Restaurant!</h1>
    <div>
        Restaurant Name: <input type="text" name="name" size="36" maxlength="10" />

    </div>
    <div>
         Restaurant Phone: <input type="tel" name="tel" size="36" maxlength="10"/>

    </div>
    <div>
        Restaurant Address: <input type="text" name="address" size="36" maxlength="200"/>

    </div>
    </br>
    <div>
        <input type="submit" value="Submit!" />
    </div>

</form>
</body>

<script>

//    $(function() {
//        $('form').submit(function() {
//            $.ajax({
//                type: 'POST',
//                url: '/newRestaurant',
//                data: { name: $(this).name.value,
//                        tel: $(this).tel.value,
//                        address: $(this).address.value
//                    }
//            });
//            return false;
//        });
//    })
</script>
</html>
