package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private String getPassword() {
        return System.getenv("DB_PASSWORD");
    }

    public void findUser(String username) throws SQLException {

        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", getPassword());
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Found user: " + rs.getString("name"));
                }
            }
        }
    }

    public void deleteUser(String username) throws SQLException {

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", getPassword());
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
            System.out.println("User deleted.");
        }
    }
}