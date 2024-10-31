
public class LoyaltyServlet extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC","root", "root");
		
		
		
			String username = request.getParameter("Username");
			String password1 = request.getParameter("Password1");
			String password2 = request.getParameter("Password2");
			
			if(username == null || password1 == null || password2 == null)
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
				response.sendRedirect("LoyaltyHomePage.html");
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
