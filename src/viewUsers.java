import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class viewUsers {

    private List<User> users; 

   
    public List<User> getUsers() {
        return users;
    }

  
    public String viewUsers() {
        Connection connection = null;
        try {
            
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");

           
            String viewUsers = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(viewUsers);
            ResultSet rs = statement.executeQuery();

         
            users = new ArrayList<>();// making into an arraylist

          
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                // add user to the list
                users.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) 
        {
            e.printStackTrace();
            return "error";  //error handling
        }

        return "success";  
    }
}
