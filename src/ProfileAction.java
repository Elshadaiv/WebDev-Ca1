
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProfileAction {

	
	private String username; // The username of the profile to view
    private Map<String, Object> session;
    private String profileDetails; // Stores profile details to display

    public String getUsername()
    {
        return username;
    }
    
    public String getProfileDetails() 
    {
        return profileDetails;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setSession(Map<String, Object> session) 
    {
        this.session = session;
    }
    
    public String viewMyProfile() {
        String currentUser = (String) session.get("username");
		return currentUser;
    }
//getters and settings for the profile details, also has a session of current user and the session gets the current username.

    
    private String ProfileDetails(String username) {
        Connection connection = null;
        String result = "success";
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");

            String details = "SELECT * FROM users WHERE (username, email)  = ?, ?"; // selects the username and email from the database 
            PreparedStatement statement = connection.prepareStatement(details);
            statement.setString(1, username); // grabs the username

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            					//gets username and password and prints it below
                profileDetails = "Username: " + rs.getString("username") + "<br>" +
                                 "Email: " + rs.getString("email") + "<br>";
                		
               return result;  //returns success
}
        } catch (SQLException e)
        {
            e.printStackTrace();
            return "error"; //error handling
        }
		return "error"; 
    }
}

