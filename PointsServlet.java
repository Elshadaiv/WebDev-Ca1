
public class PointsServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC","root", "root");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		String username = request.getParameter("Username");
		int AddPoints = request.getParameter("AddPoints");
		int SpendPoints = request.getParameter("SpendPoints");
		PreparedStatement getUserPoints = connection.prepareStatement("SELECT Points FROM user WHERE Username = ?");
		getUserPoints.setString(1, username);
	}
}
