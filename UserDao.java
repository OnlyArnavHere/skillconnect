package com.skillconnect.dao;

import com.skillconnect.model.User;
import com.skillconnect.util.DBUtil;
import com.skillconnect.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    /**
     * Inserts a new user into the database after hashing their password.
     * @param user The User object containing registration details.
     * @return True if the user was successfully registered, false otherwise.
     */
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password_hash) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getEmail());
            // Hash the password before storing it
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            pst.setString(4, hashedPassword);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // A SQLException with code 1062 for MySQL indicates a duplicate entry
            if (e.getErrorCode() == 1062) {
                System.err.println("Registration failed: Email already exists.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Authenticates a user by checking their email and password.
     * @param email The user's email.
     * @param password The user's plain text password.
     * @return A User object if login is successful, otherwise null.
     */
    public User loginUser(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                // Check if the provided password matches the stored hash
                if (PasswordUtil.checkPassword(password, hashedPassword)) {
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("email"));
                    user.setBio(rs.getString("bio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
