
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
}
