import java.sql.*;


public class addItem 
{
    private String itemName;
    private String itemDescription;
    private double startingPrice;
    
    public String getItemName()
    {
        return itemName;
    }
    public String getItemDescription() 
    {
        return itemDescription;
    }
    
    public double getStartingPrice() 
    {
        return startingPrice;
    }
    
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }
    
    
    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }
    
    public void setStartingPrice(double startingPrice) 
    {
        this.startingPrice = startingPrice;
    }
    
    
    public String addItemMethod()
    {
	    String result = "success";
    	Connection connection = null;
    	String username = null;
    	
    	try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");
            String add = "INSERT INTO items ( item_name, item_description, starting_price) VALUES (?, ?, ?)";
            
            PreparedStatement statement = connection.prepareStatement(add);
            
            String getUserId = "SELECT id FROM users WHERE username = ?";
            PreparedStatement getUserIdStatement = connection.prepareStatement(getUserId);
            getUserIdStatement.setString(1, username);


	        ResultSet rs = statement.executeQuery();

            if (rs.next()) 
            {
             
            }
 
            statement.setString(2, itemName);
            statement.setString(3, itemDescription);
            statement.setDouble(4, startingPrice);
            statement.executeUpdate();
            
            statement.close();
            getUserIdStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            result = "error";
            
        } 
    	   return result;
    }
 
