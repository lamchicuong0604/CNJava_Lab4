package tdtu.edu.vn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HashMap<String, String> accounts;

    public void init() throws ServletException {
        accounts = new HashMap<String, String>();
        accounts.put("user1", "password1");
        accounts.put("user2", "password2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            out.println("<html><body><h2>Name/Password match</h2></body></html>");
        } else {
            out.println("<html><body><h2>Name/Password does not match</h2></body></html>");
        }
    }
}
