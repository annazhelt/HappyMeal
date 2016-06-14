<%@ page import="org.json.JSONObject" %>
<%@ page import="happymeal.entity.OrderDetailsObject" %>
<%@ page import="happymeal.db.UpdateOrderDB" %>


<%
    int id = Integer.parseInt(request.getParameter("order_id"));
    OrderDetailsObject object = UpdateOrderDB.getOrderDetails(id);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("user_name", object.getUser_name());
    jsonObject.put("delivery_address", object.getDelivery_address());
    jsonObject.put("delivery_method", object.getDelivery_method());
    jsonObject.put("delivery_time", object.getDelivery_time());
    jsonObject.put("status", object.getStatus());
    jsonObject.put("rname", object.getRestaurantName());
    jsonObject.put("dname", object.getDname());

    out.print(jsonObject.toString());

%>