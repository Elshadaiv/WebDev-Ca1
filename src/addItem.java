import java.sql.*;


public class addItem 
{
	private int itemId;
    private String itemName;
    private String itemDescription;
    private double startingPrice;
    
    public int getItemId() 
    {
        return itemId;
    }
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
    
    public void setItemId(int itemId) 
    {
        this.itemId = itemId;
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
    
    //getters and setters for the item information that is saved in the database
    public String addItemMethod()
    {
	    String result = "success";
    	Connection connection = null;
    	String username = null;
    	
    	try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");
            String add = "INSERT INTO items ( item_name, item_description, starting_price) VALUES (?, ?, ?)"; // inserts it the database
            
            PreparedStatement statement = connection.prepareStatement(add);
    

	        ResultSet rs = statement.executeQuery();

            if (rs.next()) 
            {
             
            }
 
            statement.setString(1, itemName);
            statement.setString(2, itemDescription);
            statement.setDouble(3, startingPrice);
            statement.executeUpdate();
            
            statement.close();// prints out statement above

            
        } catch (SQLException e) {
            e.printStackTrace();
            result = "error"; //error handling
            
        } 
    	   return result;
    }
 
}