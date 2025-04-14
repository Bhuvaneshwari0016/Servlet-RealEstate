package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish DB connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/realestate", "root", "bhuvana");

            // Prepare query
            String query = "SELECT * FROM register WHERE email = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            // Execute query
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Login success
                request.getRequestDispatcher("real.html").forward(request, response);
            } else {
                // Login failed
                request.setAttribute("errorMessage", "Invalid email or password.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Server error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
