package web;

import happymeal.connection.ConnectionUtility;
import happymeal.db.UserDAO;
import happymeal.entity.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by anna on 2016-06-13.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    private RequestDispatcher jsp;

    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        jsp = context.getRequestDispatcher("/login.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        jsp.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        User user = new UserDAO().findByUsername(username);
        if (user == null)
        {
            req.setAttribute("message", "Authentication failed.");
            jsp.forward(req, resp);
            return;
        }

        String password = req.getParameter("password");
        if (password == null || !user.getPassword().equals(password))
        {
            req.setAttribute("message", "Authentication failed.");
            jsp.forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        String uname = user.getUser_name();
        session.setAttribute("username", uname);
        String url = "/index.html";
        resp.sendRedirect(url);
    }
}
