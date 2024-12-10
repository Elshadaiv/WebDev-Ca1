import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bids {
	private double bidAmount; 

    public double getBidAmount()
    {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount)
    {
        this.bidAmount = bidAmount;
    }//getters and setters for biding 
    
    public String viewItems() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");
            
            String bid = "INSERT INTO bids (bid_amount) VALUES (?)"; // inserts into the database
            PreparedStatement statement = connection.prepareStatement(bid);

            statement.setDouble(1, bidAmount);

            statement.executeUpdate();

            statement.close();

            return "success";
            
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        } 

}
}
