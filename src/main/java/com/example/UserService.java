package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String sql = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rs.getObject(1);
                }
            }

        } catch (UserServiceException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw new UserServiceException("Failed to find user: " + username, ex);
        }
    }

    public void deleteUser(String username) throws UserServiceException {
        Objects.requireNonNull(username, "username");

        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();

        } catch (UserServiceException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw new UserServiceException("Failed to delete user: " + username, ex);
        }
    }
}