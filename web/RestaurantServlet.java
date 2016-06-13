package web;

import happymeal.connection.ConnectionUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by anna on 2016-06-10.
 */
@WebServlet(name = "RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

//"<p>" + resNames[i] +
//        "<button type='button' id = '"
//        + resNames[i] + "' onclick='showComment()'> Show Comments</button> " +
//        "<button id = '"+resNames[i]+"a' onclick='addComment()'>Add Comment </button> <div id = '"+
//resNames[i]+"c'></div></div></p>"

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String text = "";

        Connection conn = null;
        Statement stmt = null;
        try{
            conn = ConnectionUtility.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Restaurant";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){

                String name = rs.getString("rname");
                text += constructTableEntry(name);

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

    private  String constructTableEntry(String name){
        String tableEntry = "";
        tableEntry += "<tr id='"+name+"'> <td>" + name+"</td>" +
                "<td> <button onclick='showComment()'>Show Comments</button> </td> </tr>";
        return tableEntry;
    }
}
