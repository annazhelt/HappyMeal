<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 2016-06-14
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="happymeal.db.RestaurantDAO" %>
<%@ page import="happymeal.entity.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page import="happymeal.db.DishDAO" %>
<%@ page import="happymeal.entity.Dish" %>

<html>
<head>
    <title>Manage All Restaurants!</title>
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
</head>
<body>
<div class="alert">
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<p>" + message + "</p>");
    }
%>
    </div>
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

<form method="post" action="/newDish">
    <h1>Add Dishes To Your Restaurants!</h1>
    <div>
    Choose Your Restaurant: <select name="rid">
    <%
        RestaurantDAO rdao = new RestaurantDAO();
        List<Restaurant> restaurants = rdao.findAllWithAdmin(session.getAttribute("username").toString());
        for (Restaurant r: restaurants){
            out.println("<option value=" + r.getId()+ ">"+r.getRname()+"</option>");
        }
    %>
    </select>
    </div>
    <div>
    Dish Name:<input type="text" name="dname" size="36" maxLength="100"/>
    </div>
    <div>
        Dish Price:<input type="text" name="price" size="36"/>
    </div>
    <input type="submit" value="Submit!" />
</form>

<!--
<div>
    <h3>My Dishes</h3>
    <% DishDAO ddao = new DishDAO();
        List<Dish> dishes = ddao.findAllWithAdmin(session.getAttribute("username").toString());
        for (Dish d: dishes) {
            out.println("<p>" +d.getName() + " " + d.getRestaurantID() + "</p>");
        }
    %>
</div>
-->

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
