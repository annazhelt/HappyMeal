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
@WebFilter("/*")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            String requestURI = request.getRequestURI();
            if (requestURI.contains("login.jsp") || requestURI.contains("/js/") || requestURI.contains("/css/")) {
                chain.doFilter(request, response);
            } else {
                request.setAttribute("message", "Need to login!");
                response.sendRedirect("login.jsp");
            }
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
