package web;

import happymeal.connection.ConnectionUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by anna on 2016-06-13.
 */
@WebServlet(name = "DivisionServlet")
public class DivisionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = "";
        Connection conn = null;
        Statement stmt = null;
        try{

            String name = request.getParameter("resName");
            //text += name;
            conn = ConnectionUtility.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "select rname from Restaurant r where not exists " +
                    "(select tname from tag t where not exists " +
                    "(select tag_name from dishtag dt where dt.restaurant_id = r.id and t.tname = dt.tag_name))";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String rname = rs.getString("rname");
                text+= "<p>"+rname+"</p>";
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        PrintWriter pw = response.getWriter();
        pw.write(text);
    }
}
