import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class strutsRegister {
	private String username;
	private String password;
	
	
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
	// login method
	public String RegisterMethodd() {
	    String result = "success";
	    Connection connection = null;

	    try {
	       
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");
	        
	        String register = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(register);
            

	        statement.setString(1, username); 
	        statement.setString(2, password); 
	        
	        int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
            {
                result = "success"; 
            } else 
            {
                result = "input";  
            }

            statement.close();

	    } 
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	        result = "error";
	    } 
	    
	        try 
	        {
	            if (connection != null) 
	            {
	                connection.close();
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	    return result;
	

}

}
