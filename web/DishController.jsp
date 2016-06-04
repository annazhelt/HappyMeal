<%@ page import="happymeal.db.DishDB" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.List" %>
<%

    String columnName = request.getParameter("columnName");
    String priceThresholdString = request.getParameter("priceThreshold");
    double priceThreshold = Double.parseDouble(priceThresholdString);

    DishDB dishDB = new DishDB();
    List<Object> resultList = dishDB.getDishListByPriceThreshold(columnName, priceThreshold);


    JSONArray jsonArray = new JSONArray(); // {}
    for (Object o : resultList) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(columnName, o.toString());

        jsonArray.put(jsonObject);
    }

    out.print(jsonArray.toString());
%>