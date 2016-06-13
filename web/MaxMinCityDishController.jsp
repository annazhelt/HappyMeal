<%@ page import="happymeal.db.DishDB" %>
<%@ page import="happymeal.entity.Dish" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="happymeal.db.NestedAggregationDB" %>
<%@ page import="happymeal.entity.NestedAggregationObject" %>


<%
    String aggregation = request.getParameter("aggregation"); // values: "max"/"min"
    String city = request.getParameter("city"); //selected city
    List<NestedAggregationObject> resultList = NestedAggregationDB.getDishList(aggregation,city);


    JSONArray jsonArray = new JSONArray(); // {}
    for (NestedAggregationObject object : resultList) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dishName", object.getDishName());
        jsonObject.put("price", object.getPrice());
        jsonObject.put("restaurant", object.getRestaurantName());
        jsonArray.put(jsonObject);
    }

    out.print(jsonArray.toString());

%>

