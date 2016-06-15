package web;

import happymeal.connection.ConnectionUtility;
import happymeal.db.RestaurantDAO;
import happymeal.entity.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * Created by anna on 2016-06-14.
 */
@WebServlet(name = "NewRestaurantServlet", urlPatterns = "/newRestaurant")
public class NewRestaurantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RestaurantDAO rdao = new RestaurantDAO();
        Restaurant r = null;
        String htmlFormName = request.getParameter("htmlFormName");

        if (htmlFormName.equals("delete")){
            int id = Integer.parseInt(request.getParameter("rid"));
            r = new Restaurant(id);
            rdao.delete(r);
            request.setAttribute("message", "Successfully deleted your restaurant! Enjoy!");
            request.getRequestDispatcher("/admin.jsp").forward(request, response);

        } else {

            String rname = request.getParameter("name");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");

            HttpSession session = request.getSession(false);
            String admin = session.getAttribute("username").toString();
            r = new Restaurant(rname, admin, tel, address);
            rdao.create(r);


            request.setAttribute("message", "Successfully added your restaurant! Enjoy!");
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    Connection conn = null;
//        PreparedStatement stmt = null;
//        try{
//
//            conn = ConnectionUtility.getConnection();
//            String sql;
//            sql = "insert into Restaurant (rname, admin_name, phone, address) values (?, ?, ?, ?)";
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, rname);
//            stmt.setString(2, admin);
//            stmt.setString(3,tel);
//            stmt.setString(4,address);
//            System.out.println(stmt);
//            stmt.executeUpdate();
//
//            //STEP 6: Clean-up environment
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }catch(Exception e){
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
}
