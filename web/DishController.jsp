<%@ page import="happymeal.db.DishDB" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.List" %>
<%@ page import="happymeal.entity.Dish" %>
<%

    String columnName = request.getParameter("columnName");
    String priceThresholdString = request.getParameter("priceThreshold");
    double priceThreshold = Double.parseDouble(priceThresholdString);

    DishDB dishDB = new DishDB();
    List<Dish> resultList = dishDB.getDishListByPriceThreshold(columnName, priceThreshold);


    JSONArray jsonArray = new JSONArray(); // {}
    for (Dish dish : resultList) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dishName", dish.getName());
        if (columnName.equals("restaurant")) {
            jsonObject.put(columnName, dish.getRestaurant().getRname());
        } else if (columnName.equals("price")) {
            jsonObject.put(columnName, dish.getPrice());
        }
        jsonArray.put(jsonObject);
    }

    out.print(jsonArray.toString());
%>