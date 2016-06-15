<%@ page import="org.json.JSONObject" %>
<%@ page import="happymeal.entity.OrderDetailsObject" %>
<%@ page import="happymeal.db.UpdateOrderDB" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.JSONArray" %>


<%
    String id = request.getParameter("id");
    int oid = Integer.parseInt(id);
    String method = request.getParameter("method");
    String address = request.getParameter("address");
    String time = request.getParameter("time");

    int n = UpdateOrderDB.updateOrder(oid, method, time, address);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("number_of_rows_updated", Integer.toString(n));

    out.print(jsonObject.toString());

%>