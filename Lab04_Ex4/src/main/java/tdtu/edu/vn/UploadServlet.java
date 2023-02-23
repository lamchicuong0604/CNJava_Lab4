package tdtu.edu.vn;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "C:\\Users\\nk\\Desktop\\Eclipse\\JAVA TECH - TH\\Lab04_Ex4\\uploads";
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("txt", "doc", "docx", "img", "pdf", "rar", "zip");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form fields
        String fileName = request.getParameter("name");
        Part filePart = request.getPart("document");
        boolean override = "on".equals(request.getParameter("override"));

        // Check if file has valid extension
        String fileExtension = getFileExtension(filePart);
        if (!SUPPORTED_EXTENSIONS.contains(fileExtension)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported file extension");
            return;
        }

        // Check if file with the same name already exists
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File existingFile = new File(uploadDir, fileName + "." + fileExtension);
        if (existingFile.exists() && !override) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File already exists");
            return;
        } 
        // Save file to server
        System.out.println(uploadDir.getAbsolutePath());
        filePart.write(uploadDir + File.separator + fileName + "." + fileExtension);

        // Set response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("File uploaded. Click <a href=\""+ UPLOAD_DIRECTORY + "/" + fileName + "." + fileExtension + "\">here</a> to visit file.");
        out.println("</body></html>");
    }

    private String getFileExtension(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                int dotIndex = fileName.lastIndexOf('.');
                return fileName.substring(dotIndex + 1);
            }
        }
        return "";
    }
}
