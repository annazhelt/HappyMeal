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
<%@ page import="java.util.HashMap" %>

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
    HashMap<Integer, List<Dish>> resDishes= new HashMap();
    for (Restaurant r : restaurants){
        resDishes.put(r.getId(), ddao.findAllWithRID(r.getId()));
    }
%>

<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<p>" + message + "</p>");
    }
%>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">My Restaurants and Dishes</h3>
        </div>
        <div class="panel-body">
            <div id="accordion">
            <%
                for (Restaurant r: restaurants) {
                    //out.println("<div class='panel panel-default'>");
                    out.println("<div class='accordion-section'>");
                    String rname = r.getRname();
                    rname = rname.replaceAll("\\s","");
                    out.println("<a class='accordion-section-title' href='#"+rname+"'>"+r.getRname()+"</a>");

                    out.println("<div id='"+rname+"' class='accordion-section-content'>");

                    for (Dish d : resDishes.get(r.getId())) {
                        out.println("<p>" + d.getName() + " " + d.getPrice()+ "</p>");
                    }
                    out.println("</div></div>");
                }
            %>
            </div>
        </div>
    </div>
        <br>

    <div class="col-xs-12 col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Create a New Restaurant!</h3>
            </div>
            <div class="panel-body">
                <form method="post" action="/newRestaurant">
                    <fieldset class="form-group">
                        <label>Restaurant Name</label>
                        <input type="text" class="form-control" name="name" size="36" maxlength="10" />

                    </fieldset>
                    <fieldset class="form-group">
                        <label>Restaurant Phone</label>
                         <input type="tel" class="form-control" name="tel" size="36" maxlength="10"/>

                    </fieldset>
                    <fieldset class="form-group">
                        <label>Restaurant Address</label>
                        <input type="text" class="form-control" name="address" size="36" maxlength="200"/>

                    </fieldset>
                    <button type="submit" class="btn btn-primary">Submit</button>

                </form>
            </div>
        </div>
    </div>
<div class="col-xs-12 col-md-6 ">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Add Dishes To Your Restaurants!</h3>
        </div>
        <div class="panel-body">
            <form method="post" action="/newDish">
                <fieldset class="form-group">
                    <label>Choose Your Restaurant </label>
                    <select name="rid" class="form-control">
                        <%
                            for (Restaurant r: restaurants){
                                out.println("<option value=" + r.getId()+ ">"+r.getRname()+"</option>");
                            }
                        %>
                    </select>
                </fieldset>
                <fieldset class="form-group">
                    <label>
                        Dish Name
                    </label>
                    <input type="text" name="dname" size="36" maxLength="100" class="form-control"/>
                </fieldset>
                <fieldset class="form-group">
                    <label>
                        Price
                    </label>
                    <input type="text" name="price" size="36" class="form-control"/>
                </fieldset>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
    </div>

        <div class="col-xs-12 col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Delete Your Old Restaurants!</h3>
                </div>
                <div class="panel-body">
                    <form action="/newRestaurant" method="post" name="delete">
                        <input type="hidden" name="htmlFormName" value="delete"/>
                        <fieldset class="form-group">
                            <label>Choose Your Restaurant </label>
                            <select name="rid" class="form-control">
                                <%
                                    for (Restaurant r: restaurants){
                                        out.println("<option value=" + r.getId()+ ">"+r.getRname()+"</option>");
                                    }
                                %>
                            </select>
                        </fieldset>
                        <button type="submit" class="btn btn-primary">Submit</button>

                    </form>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-md-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Delete your  Old Dishes!</h3>
        </div>
        <div class="panel-body">
            <form action="/newDish" method="post" name="delete">
                <input type="hidden" name="htmlFormName" value="delete"/>
                <fieldset class="form-group">
                    <label>Choose the Dish </label>
                    <select name="resAndDish" class="form-control">
                        <%
                            for (Restaurant r: restaurants){
                                for (Dish d: resDishes.get(r.getId())){
                                    out.println("<option value=" + r.getId()+ "," + d.getName()+ ">"+
                                            r.getRname()+ " " + d.getName()+"</option>");
                                }
                            }
                        %>
                    </select>
                    </fieldset>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>

    </div>




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
