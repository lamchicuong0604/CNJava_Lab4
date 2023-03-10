package tdtu.edu.vn;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductService")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Product> products = new ArrayList<Product>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
        products.add(new Product(1, "IP1", 975));
    }
    private Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    
    private boolean removeProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set the response type to json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // check if the request parameter id is used
        String idParam = request.getParameter("id");
        if (idParam != null) {
            // get the product with the specified id
            try {
                int id = Integer.parseInt(idParam);
                Product product = getProductById(id);
                if (product != null) {
                    // return the product as json
                    JSONObject result = new JSONObject();
                    result.put("code", 0);
                    result.put("message", "?????c s???n ph???m th??nh c??ng");
                    result.put("data", product.toJSON());
                    response.getWriter().write(result.toString(4));
                } else {
                    // return an error message if the product is not found
                    JSONObject result = new JSONObject();
                    result.put("code", 2);
                    result.put("message", "Kh??ng t??m th???y s???n ph???m n??o v???i m?? s??? " + id);
                    response.getWriter().write(result.toString(4));
                }
            } catch (NumberFormatException e) {
                // return an error message if the id is not a valid integer
                JSONObject result = new JSONObject();
                result.put("code", 1);
                result.put("message", "Id s???n ph???m kh??ng h???p l???");
                response.getWriter().write(result.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            // return all products as json
            JSONArray result = new JSONArray();
            for (Product product : products) {
                result.put(product.toJSON());
            }
            JSONObject responseJson = new JSONObject();
            responseJson.put("code", 0);
            responseJson.put("message", "?????c s???n ph???m th??nh c??ng");
            responseJson.put("data", result);
            response.getWriter().write(responseJson.toString(4));
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// set the response type to json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // read the request body as json
        StringBuilder sbBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        	sbBuilder.append(line);
        }
        
        JSONObject requestBody = new JSONObject(sbBuilder.toString());
        
        try {    
            // check if all required fields are provided
            if (!requestBody.has("id") || !requestBody.has("name") || !requestBody.has("price")) {
                JSONObject result = new JSONObject();
                result.put("code", 3);
                result.put("message", "Th??ng tin s???n ph???m kh??ng ?????y ?????");
                response.getWriter().write(result.toString());
                return;
            }
            
            // check if the id is a valid integer
            int id;
            try {
                id = requestBody.getInt("id");
            } catch (JSONException e) {
                JSONObject result = new JSONObject();
                result.put("code", 2);
                result.put("message", "Id s???n ph???m kh??ng h???p l???");
                response.getWriter().write(result.toString());
                return;
            }
            
            // check if the id already exists
            if (getProductById(id) != null) {
            	JSONObject result = new JSONObject();
                result.put("code", 4);
                result.put("message", "S???n ph???m ???? t???n t???i");
                response.getWriter().write(result.toString());
                return;
            }
            
            // check if the price is a valid float
            float price;
            try {
                price = (float) requestBody.getDouble("price");
            } catch (JSONException e) {
                JSONObject result = new JSONObject();
                result.put("code", 5);
                result.put("message", "Gi?? s???n ph???m kh??ng h???p l???");
                response.getWriter().write(result.toString());
                return;
            }
            
            // create a new product and add it to the list
            Product newProduct = new Product(id, requestBody.getString("name"), price);
            products.add(newProduct);
            
            // return a success message with the new product
            JSONObject result = new JSONObject();
            result.put("code", 0);
            result.put("message", "Th??m s???n ph???m th??nh c??ng");
            result.put("data", newProduct.toJSON());
            response.getWriter().write(result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// set the response type to json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // read the request body as json
        StringBuilder sbBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        	sbBuilder.append(line);
        }
        
        JSONObject requestBody = new JSONObject(sbBuilder.toString());
        try {
            
            // check if all required fields are provided
            if (!requestBody.has("id") || !requestBody.has("name") || !requestBody.has("price")) {
                JSONObject result = new JSONObject();
                result.put("code", 3);
                result.put("message", "Th??ng tin s???n ph???m kh??ng ?????y ?????");
                response.getWriter().write(result.toString());
                return;
            }
            
            // check if the id is a valid integer
            int id;
            try {
                id = requestBody.getInt("id");
            } catch (JSONException e) {
                JSONObject result = new JSONObject();
                result.put("code", 2);
                result.put("message", "Id s???n ph???m kh??ng h???p l???");
                response.getWriter().write(result.toString());
                return;
            }
            
            // get the product with the specified id
            Product product = getProductById(id);
            if (product == null) {
                // return an error message if the product is not found
                JSONObject result = new JSONObject();
                result.put("code", 1);
                result.put("message", "S???n ph???m kh??ng t???n t???i");
                response.getWriter().write(result.toString());
                return;
            }
            
            // update the product information
            product.setName(requestBody.getString("name"));
            product.setPrice((float) requestBody.getDouble("price"));
            
            // return a success message with the updated product
            JSONObject result = new JSONObject();
            result.put("code", 0);
            result.put("message", "C???p nh???t s???n ph???m th??nh c??ng");
            result.put("data", product.toJSON());
            response.getWriter().write(result.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// set the response type to json
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    // check if the request parameter id is used
	    String idParam = request.getParameter("id");
	    if (idParam != null) {
	        // delete the product with the specified id
	        try {
	            int id = Integer.parseInt(idParam);
	            boolean success = removeProductById(id);
	            if (success) {
	                // return a success message
	                JSONObject result = new JSONObject();
	                result.put("code", 0);
	                result.put("message", "X??a s???n ph???m th??nh c??ng");
	                response.getWriter().write(result.toString());
	            } else {
	                // return an error message if the product is not found
	                JSONObject result = new JSONObject();
	                result.put("code", 1);
	                result.put("message", "S???n ph???m kh??ng t???n t???i");
	                response.getWriter().write(result.toString());
	            }
	        } catch (NumberFormatException e) {
	            // return an error message if the id is not a valid integer
	            JSONObject result = new JSONObject();
	            result.put("code", 2);
	            result.put("message", "Id s???n ph???m kh??ng h???p l???");
	            response.getWriter().write(result.toString());
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    } else {
	        // return an error message if no id is provided
	        JSONObject result = new JSONObject();
	        result.put("code", 4);
	        result.put("message", "Kh??ng c?? th??ng tin s???n ph???m ????? x??a");
	        response.getWriter().write(result.toString());
	    }
	}
}

