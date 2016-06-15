<%@ page import="org.json.JSONObject" %>
<%@ page import="happymeal.entity.OrderDetailsObject" %>
<%@ page import="happymeal.db.UpdateOrderDB" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.JSONArray" %>


<%
    int id = Integer.parseInt(request.getParameter("id"));
    List<OrderDetailsObject> objectList = UpdateOrderDB.getOrderDetails(id);
    JSONArray jsonArray = new JSONArray();
    for (OrderDetailsObject object : objectList){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", object.getUser_name());
        jsonObject.put("delivery_address", object.getDelivery_address());
        jsonObject.put("delivery_method", object.getDelivery_method());
        jsonObject.put("delivery_time", object.getDelivery_time());
        jsonObject.put("current_status", object.getStatus());
        jsonObject.put("rname", object.getRestaurantName());
        jsonObject.put("dish_name", object.getDname());
        jsonArray.put(jsonObject);
    }


    out.print(jsonArray.toString());

%>