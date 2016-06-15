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

        if (htmlFormName != null && htmlFormName.equals("delete")){
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

}
