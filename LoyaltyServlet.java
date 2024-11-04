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
			//the connection to the databaase
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC","root", "root");
		
		
			// declaring the username password 
			String username = request.getParameter("Username");
			String password1 = request.getParameter("Password1");
			String password2 = request.getParameter("Password2");
			
			if(username == null || password1 == null || password2 == null || username.isEmpty() || password1.isEmpty() || password2.isEmpty())
			{
				response.getWriter().println("You must enter all fields"); /// this message comes up if user doesnt enter all the fields when pressing register
			}
			
			if(!password1.equals(password2))
			{
				response.getWriter().println ("Password doesn't match"); // this message appears when teh user passsword 1 doesnt match password 2
			}

			if(connection != null && password1.equals(password2)) {
			PreparedStatement createUser = connection.prepareStatement(
					"INSERT into User "
							+ "(Username, Password, Points)" +" VALUES (?, ?, ?)"); // adding it to the database
			createUser.setString(1, request.getParameter("Username")); 
			createUser.setString(2, request.getParameter("Password1"));
			createUser.setInt(3, 100); // points to 100 for users
			int rowsUpdated = createUser.executeUpdate(); // stores 
			createUser.close();
			
			if (rowsUpdated > 0)
			{
				response.sendRedirect("Login.html"); // this redirects to the login html page
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
