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
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Manage All Restaurants!</title>
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
</head>
<body>
<%
    RestaurantDAO rdao = new RestaurantDAO();
    DishDAO ddao = new DishDAO();
    List<Restaurant> restaurants = rdao.findAllWithAdmin(session.getAttribute("username").toString());
%>

<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<p>" + message + "</p>");
    }
%>

    <div>
        <h1>My Restaurants and Dishes</h1>
        <div id="accordion">
        <%
            for (Restaurant r: restaurants) {
                //out.println("<div class='panel panel-default'>");
                out.println("<div class='accordion-section'>");
                String rname = r.getRname();
                rname = rname.replaceAll("\\s","");
                out.println("<a class='accordion-section-title' href='#"+rname+"'>"+r.getRname()+"</a>");

                out.println("<div id='"+rname+"' class='accordion-section-content'>");

                List<Dish> dishesByRes = ddao.findAllWithRID(r.getId());
                for (Dish d : dishesByRes) {
                    out.println("<p>" + d.getName() + " " + d.getPrice()+ "</p>");
                }
                out.println("</div></div>");
            }
        %>
        </div>
    </div>
    <br>
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
<br>
<form method="post" action="/newDish">
    <h1>Add Dishes To Your Restaurants!</h1>
    <div>
    Choose Your Restaurant: <select name="rid">
    <%
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

<form action="/logout" method="get">
    <input type="submit" class="btn btn-danger" value="Logout" />
</form>
    </body>

<!--
<div>
    <h3>My Dishes</h3>
    <%
        List<Dish> dishes = ddao.findAllWithAdmin(session.getAttribute("username").toString());
        for (Dish d: dishes) {
            out.println("<p>" +d.getName() + " " + d.getRestaurantID() + "</p>");
        }
    %>
</div>
-->

<script>

        function close_accordion_section(currentAttrValue, elem) {
            $(elem).removeClass('active');
            $(currentAttrValue).slideUp(300);
            $(currentAttrValue).removeClass('open');
        }

        $('.accordion-section-title').click(function(e) {
                    // Grab current anchor value
                    var currentAttrValue = $(this).attr('href');

                    if ($(e.target).is('.active')) {
                        var elem= this;
                        close_accordion_section(currentAttrValue, elem);
                    } else {
                        //close_accordion_section(currentAttrValue);
                        // Add active class to section title
                        $(this).addClass('active');
                        // Open up the hidden content panel
                        $(currentAttrValue).slideDown(300)
                        $(currentAttrValue).addClass('open');
                    }

                    e.preventDefault()
                }
        );

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
