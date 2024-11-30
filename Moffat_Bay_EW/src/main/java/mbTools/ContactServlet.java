package mbTools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet //("/contact")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of the MySQL server
    private String jdbcURL = "jdbc:mysql://localhost:3306/Moffat";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String reservation = request.getParameter("reservation");
        String message = request.getParameter("message");

        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish connection
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // SQL query to insert the data
            String sql = "INSERT INTO contact_entries (name, email, phone, reservation, message) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, reservation);
            pstmt.setString(5, message);

            // Execute the query
            pstmt.executeUpdate();
            
            // Redirect or send a success response
            response.sendRedirect("thankyou.html");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
