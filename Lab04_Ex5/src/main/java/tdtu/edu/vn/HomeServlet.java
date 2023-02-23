package tdtu.edu.vn;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String targetPage = null;

        if (page == null) {
            response.getWriter().println("Welcome to our website");
            return;
        } else if (page.equals("about")) {
            targetPage = "/about.jsp";
        } else if (page.equals("contact")) {
            targetPage = "/contact.jsp";
        } else if (page.equals("help")) {
            targetPage = "/help.jsp";
        } else {
            response.getWriter().println("Invalid page request");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

