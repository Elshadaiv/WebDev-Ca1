
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
			if(request.getParameter("Password1").equalsIgnoreCase("Password2"));
			PreparedStatement createUser = connection.prepareStatement(
					"INSERT into User "
							+ "(Loyalty)" +" VALUES (?, ?, ?)");
			createUser.setString(1, request.getParameter("username"));
			createUser.setString(2, request.getParameter("Password1"));
			createUser.setString(3, request.getParameter("Password2"));
			int rowsUpdated = createUser.executeUpdate();
			createUser.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
}
