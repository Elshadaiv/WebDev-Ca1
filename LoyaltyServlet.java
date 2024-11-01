import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LoyaltyServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC","root", "root");
		
		
		
			String username = request.getParameter("Username");
			String password1 = request.getParameter("Password1");
			String password2 = request.getParameter("Password2");
			
			if(username == null || password1 == null || password2 == null || username.isEmpty() || password1.isEmpty() || password2.isEmpty())
			{
				response.getWriter().println("You must enter all fields");
			}
			
			if(!password1.equals(password2))
			{
				response.getWriter().println ("Password doesn't match");
			}

			if(connection != null && password1.equals(password2)) {
			PreparedStatement createUser = connection.prepareStatement(
					"INSERT into User "
							+ "(Username, Password, Points)" +" VALUES (?, ?, ?)");
			createUser.setString(1, request.getParameter("Username"));
			createUser.setString(2, request.getParameter("Password1"));
			createUser.setInt(3, 100);
			int rowsUpdated = createUser.executeUpdate();
			createUser.close();
			
			if (rowsUpdated > 0)
			{
				response.sendRedirect("Login.html");
			}
			
			else 
			{
				response.getWriter().println("Error, Please try again");
			}
			}
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	

			
	}
	}





}
