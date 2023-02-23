package tdtu.edu.vn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIDEs = request.getParameterValues("favorite_ide[]");
        String toeicScore = request.getParameter("toeic");
        String message = request.getParameter("message");
        
        StringBuilder errorMessage = new StringBuilder();
        
        // Check if required fields are empty
        if (name == null || name.isEmpty()) {
            errorMessage.append("Name is required. ");
        }
        
        if (email == null || email.isEmpty()) {
            errorMessage.append("Email is required. ");
        }
        
        if (country == null || country.equals("Select a country")) {
            errorMessage.append("Please select a country. ");
        }
        
        // Display error message if required fields are empty
        if (errorMessage.length() > 0) {
            request.setAttribute("error", errorMessage.toString());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        // Forward to display.jsp with user information if all required fields are filled in
        else {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("birthday", birthday);
            request.setAttribute("birthtime", birthtime);
            request.setAttribute("gender", gender);
            request.setAttribute("country", country);
            String favoriteIDEsStr = "";
            if (favoriteIDEs != null && favoriteIDEs.length > 0) {
                for (int i = 0; i < favoriteIDEs.length; i++) {
                    favoriteIDEsStr += favoriteIDEs[i];
                    if (i < favoriteIDEs.length - 1) {
                        favoriteIDEsStr += ", ";
                    }
                }
            }
            request.setAttribute("favoriteIDEs", favoriteIDEsStr);

            request.setAttribute("toeicScore", toeicScore);
            request.setAttribute("message", message);
            
            request.getRequestDispatcher("display.jsp").forward(request, response);
        }
    }
}
