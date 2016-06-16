package web;

import happymeal.db.DishDAO;
import happymeal.entity.Dish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by anna on 2016-06-14.
 */
@WebServlet(name = "NewDishServlet", urlPatterns = "/newDish")
public class NewDishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DishDAO ddao = new DishDAO();

        String htmlForm = request.getParameter("htmlFormName");
        if(htmlForm != null && htmlForm.equals("delete")){
            String dishString = request.getParameter("resAndDish");
            String[] rd = dishString.split(",");
            int rid = Integer.parseInt(rd[0]);
            String dname = rd[1];
            Dish d = new Dish(rid, dname);
            ddao.delete(d);
            request.setAttribute("message", "Successfully deleted dish from restaurant! Enjoy!");
            request.getRequestDispatcher("/admin.jsp").forward(request, response);

        }else {

            int rid = Integer.parseInt(request.getParameter("rid"));
            String dname = request.getParameter("dname");
            double price = Double.parseDouble(request.getParameter("price"));

            Dish dish = new Dish(rid, dname, price);

            ddao.create(dish);

            request.setAttribute("message", "Successfully added dish to restaurant! Enjoy!");
            response.sendRedirect("/admin.jsp");
            //request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
