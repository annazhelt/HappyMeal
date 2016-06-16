package web;

import happymeal.db.DishTagDAO;
import happymeal.db.RestaurantDAO;
import happymeal.entity.DishTag;
import happymeal.entity.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anna on 2016-06-15.
 */
@WebServlet(name = "DishTagServlet", urlPatterns = "/dishTag")
public class DishTagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DishTagDAO dtdao = new DishTagDAO();
        DishTag dt = null;
        String htmlFormName = request.getParameter("htmlFormName");
        String msg = "";

        if(htmlFormName != null && htmlFormName.equals("add")){
            String dishString = request.getParameter("resAndDish");
            String[] rd = dishString.split(",");
            int rid = Integer.parseInt(rd[0]);
            String dname = rd[1];
            String tname = request.getParameter("tag");
            dt = new DishTag(rid, dname, tname);
            dtdao.create(dt);
            msg = "Successfully added tag to dish";
        }

        request.setAttribute("message", msg);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
