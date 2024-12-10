import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class strutsLogin  {
	
	private String username;
	private String password;
	private Map<String, Object> session; // hashmap for the session

	
	//this is getters and setters for the login detials
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
		
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	// this the method to be called by the struts
	
	public String LoginMethod() {
	    String result = "success";
	    Connection connection = null;

	    try {
	       
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");
	        
	
	        String login = "SELECT * FROM users WHERE username = ? AND password = ?"; //selects the user username and password from the database
	        PreparedStatement statement = connection.prepareStatement(login);

	   
	        statement.setString(1, username); 
	        statement.setString(2, password); 

	        ResultSet rs = statement.executeQuery();
	        if (!rs.next())
	        {
	            result = "error"; //if theres nothing else next that it will return the error message
	        }
	      
	        rs.close();
	        statement.close();
	    } 
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	        result = "error"; // erorr handling
	    } 
	    
	        try 
	        {
	            if (connection != null) 
	            {
	                connection.close(); //if connection is null it closes
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    return result;
	}
}
