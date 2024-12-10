import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ViewItems {
	

    private List<addItem> items; // list to hold all items from the database

    // Getter and setter for allItems
    public List<addItem> gettems() 
    {
      
    	return items;
    }


    // Method to retrieve all items for sale
    public String viewItems() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");

            String viewitems = "SELECT * FROM items";  // gets everything from items
            PreparedStatement statement = connection.prepareStatement(viewitems);

            ResultSet rs = statement.executeQuery();
           

            while (rs.next()) 
            {
                addItem item = new addItem();

                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemDescription(rs.getString("item_description"));
                item.setStartingPrice(rs.getDouble("starting_price"));
              //prints out items from the list
                items.add(item);
            }

            rs.close();
            statement.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return "error";
            	//error handling
        }
		return "success";
    }
}
