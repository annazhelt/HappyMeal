package web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by anna on 2016-06-14.
 */
@WebFilter("/admin.jsp")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean isAdmin = false;
        if (session != null && session.getAttribute("isAdmin") != null)
            isAdmin = session.getAttribute("isAdmin").toString().equals("true");
        if (isAdmin){
            chain.doFilter(req, resp);
        } else {
            request.setAttribute("message","Need admin priveleges to view this page ");
            response.sendRedirect("login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
