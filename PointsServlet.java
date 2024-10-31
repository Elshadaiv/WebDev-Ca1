
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
		ResultSet resultSet = getUserPoints.executeQuery();
		
		if(resultSet.next())
		{
			int points = resultSet.getInt("Points");
		
		
		if(AddPoints > 0)
		{
			points +=AddPoints;
			
			PreparedStatement updatePoints = connection.prepareStatement("UPDATE User SET Points = ? WHERE Username = ?");
			updatePoints.setInt(1, points);
			updatePoints.setString(2, username);
			updatePoints.executeUpdate();
			
			System.out.println("<html><head><title>LOYALTY POINTS ADDED </title></head>"
					+ "<body> <h1> "+AddPoints+" have been added. Your new balance is " + points
					+"</h1></body></html>");
			
			updatePoints.close();
			
		}
	}
}
