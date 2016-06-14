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
    public boolean deleteDish(String restaurantName, String dishName) {
        try {
            Connection conn = happymeal.connection.ConnectionUtility.getConnection();
            String deletionQuery = "delete from dish where restaurant_id = (select id from restaurant where rname = ?) and dname = ?";
            PreparedStatement ps = conn.prepareStatement(deletionQuery);
            ps.setString(1, restaurantName);
            ps.setString(2, dishName);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
%>
    
<%
   String restName = request.getParameter("restaurantName");
   String dishName = request.getParameter("dishName");
   boolean deletionResult = deleteDish(restName, dishName);
   if (deletionResult) {
        out.print("{\"code\": 1}");
   } else {
        out.print("{\"code\": 2}");
   }
%>
   