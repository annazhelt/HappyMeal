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

        int rid = Integer.parseInt(request.getParameter("rid"));
        String dname = request.getParameter("dname");
        double price = Double.parseDouble(request.getParameter("price"));

        Dish dish = new Dish(rid, dname, price);
        DishDAO ddao = new DishDAO();

        ddao.create(dish);

        request.setAttribute("message","Successfully added dish to restaurant! Enjoy!");
        request.getRequestDispatcher("/admin.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
