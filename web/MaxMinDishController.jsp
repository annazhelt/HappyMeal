<%@ page import="happymeal.db.DishDB" %>
<%@ page import="happymeal.entity.Dish" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.List" %>

<%
    List
%>


<%
    String aggregation = request.getParameter("aggregation"); // values: "max"/"min"
    DishDB dishDB = new DishDB();
    List<Dish> resultList = dishDB.getDishListByAggregation(aggregation);
    JSONArray jsonArray = new JSONArray(); // {}
    for (Dish dish : resultList) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dishName", dish.getName());
        jsonObject.put("price", dish.getPrice());
        jsonArray.put(jsonObject);
    }
    out.print(jsonArray.toString());
%>