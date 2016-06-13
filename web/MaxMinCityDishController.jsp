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



<%!
    private PreparedStatement createNestedAggregationPreparedStatement(Connection conn, String aggregation, String city)
        throws SQLException {

        String query = "select " + aggregation + "(price), restaurant_id from dish "+
                " where restaurant_id in (select id from restaurant where address like '" + city + "') "+
                "group by restaurant_id having avg(price) < (select avg(price) from dish);";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        return preparedStatement;
}
%>
<%!
    private List<Dish> getDishList(String aggregation, String city) {
        List<Dish> resultList = new ArrayList<>();
        try (
                Connection conn = happymeal.connection.ConnectionUtility.getConnection();
                PreparedStatement preparedStatement = createNestedAggregationPreparedStatement(conn, aggregation, city);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Dish dish = new Dish();

                String dishName = resultSet.getString("dname");
                dish.setName(dishName);

                double price = resultSet.getDouble("price");
                dish.setPrice(price);

                resultList.add(dish);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultList;
    }
%>
<%
    String aggregation = request.getParameter("aggregation2"); // values: "max"/"min"
    String city = request.getParameter("city"); //selected city
    List<Dish> resultList = getDishList(aggregation, city);


    JSONArray jsonArray = new JSONArray(); // {}
    for (Dish dish : resultList) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dishName", dish.getName());
        jsonObject.put("price", dish.getPrice());
        jsonArray.put(jsonObject);
    }

    out.print(jsonArray.toString());

    %>