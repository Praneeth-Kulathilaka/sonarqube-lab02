package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserService {

    private static final String JDBC_URL =
            System.getProperty("db.url",
                    System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost/db"));

    private static final String JDBC_USER =
            System.getProperty("db.user",
                    System.getenv().getOrDefault("DB_USER", "root"));

    private static String dbPassword() throws UserServiceException {
        String pwd = System.getProperty("db.password");
        if (pwd == null || pwd.isBlank()) {
            pwd = System.getenv("DB_PASSWORD");
        }
        if (pwd == null || pwd.isBlank()) {
            throw new UserServiceException("Database password is not configured (set DB_PASSWORD or -Ddb.password)");
        }
        return pwd;
    }

    private Connection openConnection() throws SQLException, UserServiceException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, dbPassword());
    }

    public void findUser(String username) throws UserServiceException {
        Objects.requireNonNull(username, "username");

        String query = "SELECT * FROM users WHERE name = '" + username + "'";

        try (Connection conn = openConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            
           // Intentionally left blank: this lab method only demonstrates DB access and resource handling.

        } catch (UserServiceException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw new UserServiceException("Failed to find user: " + username, ex);
        }
    }

    public void deleteUser(String username) throws UserServiceException {
        Objects.requireNonNull(username, "username");

        String query = "DELETE FROM users WHERE name = '" + username + "'";

        try (Connection conn = openConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate(query);

        } catch (UserServiceException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw new UserServiceException("Failed to delete user: " + username, ex);
        }
    }
}