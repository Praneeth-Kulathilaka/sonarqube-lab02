package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserService {

    private static final String JDBC_URL = "jdbc:mysql://localhost/db";
    private static final String JDBC_USER = "root";

    // SECURITY ISSUE (left as-is for the lab): Hardcoded credentials
    private final String password = "admin123";

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, password);
    }

    public void findUser(String username) throws UserServiceException {
        Objects.requireNonNull(username, "username");

        String query = "SELECT * FROM users WHERE name = '" + username + "'";

        try (Connection conn = openConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            // Intentionally ignoring results for this lab project.

        } catch (SQLException ex) {
            throw new UserServiceException("Failed to find user: " + username, ex);
        }
    }

    // SMELL: Unused method (left as-is for the lab)
    public void notUsed() {
        System.out.println("I am never called");
    }

    public void deleteUser(String username) throws UserServiceException {
        Objects.requireNonNull(username, "username");

        String query = "DELETE FROM users WHERE name = '" + username + "'";

        try (Connection conn = openConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate(query);

        } catch (SQLException ex) {
            throw new UserServiceException("Failed to delete user: " + username, ex);
        }
    }
}