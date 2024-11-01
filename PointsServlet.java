import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PointsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loyalty?serverTimezone=UTC", "root", "root");
            String username = request.getParameter("Username");
            int addPoints = 0;
            int spendPoints = 0;

           
            String addPoints1 = request.getParameter("AddPoints");
            String spendPoints1 = request.getParameter("SpendPoints");

            if (addPoints1 != null && !addPoints1.isEmpty()) 
            {
                addPoints = Integer.parseInt(addPoints1);
            }
            if (spendPoints1 != null && !spendPoints1.isEmpty())
            {
                spendPoints = Integer.parseInt(spendPoints1);
            }

            PreparedStatement getUserPoints = connection.prepareStatement("SELECT Points FROM User WHERE Username = ?");
            getUserPoints.setString(1, username);
            ResultSet resultSet = getUserPoints.executeQuery();

            if (resultSet.next()) {
                int points = resultSet.getInt("Points");

                if (addPoints > 0) {
                    points += addPoints;

                    PreparedStatement updatePoints = connection.prepareStatement("UPDATE User SET Points = ? WHERE Username = ?");
                    updatePoints.setInt(1, points);
                    updatePoints.setString(2, username);
                    updatePoints.executeUpdate();

                    out.println("<html><head><title>LOYALTY POINTS ADDED </title></head>"
                            + "<body> <h1> " + addPoints + " have been added. Your new balance is " + points
                            + "</h1></body></html>");
                    updatePoints.close();
                }

                if (spendPoints > 0) {
                    if (points >= spendPoints) {
                        points -= spendPoints;

                        PreparedStatement updatePoints = connection.prepareStatement("UPDATE User SET Points = ? WHERE Username = ?");
                        updatePoints.setInt(1, points);
                        updatePoints.setString(2, username);
                        updatePoints.executeUpdate();

                        out.println("<html><head><title>LOYALTY POINTS Affected </title></head>"
                                + "<body> <h1> " + spendPoints + " have been spent. Your new balance is " + points
                                + "</h1></body></html>");
                        updatePoints.close();
                    } else {
                        out.println("<html><body><h1>Not enough points to spend, you have " + points + "</h1></body></html>");
                    }
                }
            }
            resultSet.close();
            getUserPoints.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
		
		
}}
