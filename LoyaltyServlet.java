
public class LoyaltyServlet extends HttpServlet {
public void doGet(HttpServletRequest requst, HttpServletResponse respone) throws ServletException, IOException
	{
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC","root", "root");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try {
			if(connection != null && request.getParameter("Password1").equals(request.getParameter("Password2")));
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
		
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

		
		if (request.getParameter("Password")!=(request.getParameter("Password2")));
		{
			response.getWriter().println("Password doesn't match, Please try again");

		}
			
	}

}
