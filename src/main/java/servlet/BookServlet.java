package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
public class BookServlet extends HttpServlet {
    private Connection conn;

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/realestate", "root", "bhuvana");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String phone = request.getParameter("phone");
        String propertyType = request.getParameter("propertyType");
        String propertyName = request.getParameter("propertyName");
        String email = request.getParameter("email");
        String propertyArea = request.getParameter("propertyArea");
        String amount = request.getParameter("amount");
        String apartmentType = request.getParameter("apartmentType");

        String sql = "INSERT INTO bookings (first_name, phone, property_type, property_name, email, area, amount, apartment_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, phone);
            stmt.setString(3, propertyType);
            stmt.setString(4, propertyName);
            stmt.setString(5, email);
            stmt.setString(6, propertyArea);
            stmt.setString(7, amount);
            stmt.setString(8, apartmentType);
            stmt.executeUpdate();
            response.sendRedirect("thankyou.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    public void destroy() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
