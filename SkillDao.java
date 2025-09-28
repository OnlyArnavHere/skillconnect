package com.skillconnect.dao;

import com.skillconnect.model.User;
import com.skillconnect.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDao {

    /**
     * Searches for users who have a specific skill.
     * @param skillName The name of the skill to search for.
     * @return A list of User objects who possess the skill.
     */
    public List<User> searchUsersBySkill(String skillName) {
        List<User> users = new ArrayList<>();
        // This SQL query joins three tables to find the users
        String sql = "SELECT u.* FROM users u " +
                     "JOIN user_skills us ON u.user_id = us.user_id " +
                     "JOIN skills s ON us.skill_id = s.skill_id " +
                     "WHERE s.skill_name LIKE ?";
        
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, "%" + skillName + "%"); // Use LIKE for partial matches
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setBio(rs.getString("bio"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
