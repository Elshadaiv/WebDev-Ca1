import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Username");
        String password = request.getParameter("Password"); //http request
        response.setContentType("text/html"); // respond to html

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC", "root", "root")) 
        {
      
            PreparedStatement PreparedStatement = connection.prepareStatement("SELECT Points FROM User WHERE Username = ? AND Password = ?");
            PreparedStatement.setString(1, username);
            PreparedStatement.setString(2, password);
            ResultSet resultSet = PreparedStatement.executeQuery();

            if (resultSet.next())
            {
                int points = resultSet.getInt("Points");
                response.sendRedirect("LoyaltyHomePage.html"); //if login successful this message appears
            } else 
            {
                PrintWriter out = response.getWriter();
                out.println("<html><body><h3>Invalid username or password.</h3></body></html>"); //if not this message appears
            }
        } catch (Exception e1) 
        {
            e1.printStackTrace();
   
        }
    }
}
