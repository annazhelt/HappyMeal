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
    public boolean deleteRestaurant(String restaurantName) {
        try {
            Connection conn = happymeal.connection.ConnectionUtility.getConnection();
            String selectionQuery = "select id from Restaurant where rname = ?";
            PreparedStatement selectionPS = conn.prepareStatement(selectionQuery);
            selectionPS.setString(1, restaurantName);
            ResultSet selectionRS = selectionPS.executeQuery();
            if (!selectionRS.last()) {
                return false;
            }
            String deletionQuery = "delete from Restaurant where rname = ?";
            PreparedStatement ps = conn.prepareStatement(deletionQuery);
            ps.setString(1, restaurantName);
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
   boolean deletionResult = deleteRestaurant(restName);
   if (deletionResult) {
        out.print("Deletion successful");
   } else {
        out.print("Deletion unsuccessful");
   }
%>
   