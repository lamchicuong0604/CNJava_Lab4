package tdtu.edu.vn;

import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet2
 */
@WebServlet("/ImageServlet2")
public class ImageServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; filename=image2.jpg");
	        
	        // Load the image file from resources
	     InputStream inputStream = getServletContext().getResourceAsStream("/images/image2.jpg");
	        
	        // Copy the image data to the response output stream
	     OutputStream outputStream = response.getOutputStream();
	     byte[] buffer = new byte[1024];
	     int bytesRead;
	     while ((bytesRead = inputStream.read(buffer)) != -1) {
	       outputStream.write(buffer, 0, bytesRead);
	        	}
	        
	      inputStream.close();
	      outputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
    public String getServletName() {
        return "image2";
    }

}
