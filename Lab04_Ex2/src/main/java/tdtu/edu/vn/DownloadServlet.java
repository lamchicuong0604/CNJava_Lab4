package tdtu.edu.vn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the filename from the request URL
    	String filename = request.getParameter("file");
        if (filename == null || filename.isBlank()) {
        	response.setContentType("text/html");
        	response.getWriter().write("<h1>File not found</h1>");
            //response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        // Get the path to the file on the server side
        Path file = Paths.get(getServletContext().getRealPath("/images/" + filename));
        if (!Files.exists(file) || Files.isDirectory(file)) {
        	response.setContentType("text/html");
        	response.getWriter().write("<h1>File not found</h1>");
            //response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        // Set the content type and header for the response
        String contentType = getServletContext().getMimeType(filename);
        if (contentType != null && !contentType.equals("text/html")) {
            response.setContentType("text/html");
        } else {
            response.setContentType(contentType != null ? contentType : "application/octet-stream");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        // Copy the file data to the response output stream
        try (InputStream inputStream = Files.newInputStream(file);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
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
        return "download";
    }

}
