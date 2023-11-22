package Servlets;

import Beans.Product;
import Utils.DB;
import Utils.FolderUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name="AddProduct", value = "/addproduct")
@MultipartConfig
public class AddProduct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        FolderUtils.createImagesFolder(getServletContext());
        Part filePart = request.getPart("image");
        String imagePath = saveImage(filePart, getServletContext());

        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setImagePath(imagePath);

        if(addToDatabase(product)){
            response.sendRedirect(request.getContextPath() + "/pages/confirmation.jsp");
        }else{
            PrintWriter out = response.getWriter();
            out.println("znaci... problem");
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/pages/addproduct.jsp");
        FolderUtils.createImagesFolder(getServletContext());
    }

    private String saveImage(Part filePart, ServletContext servletContext) {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String imagePath = FolderUtils.getImagesFolderPath(servletContext) + File.separator + fileName;
        try {
            filePart.write(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagePath;
    }
    private boolean addToDatabase(Product product){
        try (Connection con = DB.getInstance().getConnection()){
            if(con != null){
                String query = "INSERT INTO products (name, description, imagePath) VALUES (?, ?, ?)";
                try(PreparedStatement preparedStatement = con.prepareStatement(query)){
                    preparedStatement.setString(1, product.getName());
                    preparedStatement.setString(2, product.getDescription());
                    preparedStatement.setString(3, product.getImagePath());

                    int rowsAffected = preparedStatement.executeUpdate();
                            return rowsAffected > 0;
                }
            }else{
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
