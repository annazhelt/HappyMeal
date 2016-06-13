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
    private PreparedStatement insertIntoPlaceorder(Connection conn, String user_name,
                                      String deliveryAddress, String deliveryTime,
                                      String deliveryMethod)
            throws SQLException {

        String insertQuery = "insert into placeorder"
                + "(user_name, delivery_address, delivery_time, delivery_method, current_status) values"
                + "(?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        preparedStatement.setString(1, user_name);
        preparedStatement.setString(2, deliveryAddress);
        preparedStatement.setString(3, deliveryTime);
        preparedStatement.setString(4, deliveryMethod);
        preparedStatement.setString(5, "preparing");
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
%>

<%!
    private PreparedStatement insertIntoOrderDish(Connection conn, String order_id, String dish_name, String restaurant_id)
            throws SQLException {
        String insertQuery = "insert into orderdish"
                + "(order_id, dish_name, restaurant_id) values"
                + "(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        preparedStatement.setString(1, order_id);
        preparedStatement.setString(2, dish_name);
        preparedStatement.setString(3, restaurant_id);
        preparedStatement.executeUpdate();
        return preparedStatement;
    }
%>

<%!
    public void placeOrder(String user_name,
                            String deliveryAddress, String deliveryTime,
                            String deliveryMethod, String order_id,
                            String dish_name, String restaurant_id) {

        try (
                Connection conn = happymeal.connection.ConnectionUtility.getConnection();
                PreparedStatement ps1 = insertIntoPlaceorder(conn, user_name, deliveryAddress,
                        deliveryTime, deliveryMethod);
                PreparedStatement ps2 = insertIntoOrderDish(conn, order_id, dish_name, restaurant_id);
        ) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
%>
<%

    String[] dish_names = request.getParameterValues("selectedDishNames");
    String user_name = request.getParameter("username");
    String deliveryAddress = request.getParameter("address");
    String deliveryMetod = request.getParameter("method");
    String deliveryTime = request.getParameter("time");
    String order_id = request.getParameter("orderId");
    String restaurant_id = "";


    for (String dn : dish_names){
        placeOrder(user_name, deliveryAddress, deliveryTime, deliveryMetod, order_id, dn, restaurant_id);
    }

%>