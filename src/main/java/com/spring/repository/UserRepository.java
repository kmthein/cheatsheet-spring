package com.spring.repository;

import com.spring.model.Role;
import com.spring.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    public static Connection con = null;

    static {
        con = MyConnection.getConn();
    }

    public User getUserById(int id) {
        User user = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                user = new User();
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setName(set.getString("name"));
                user.setWebsite(set.getString("website"));
                user.setDescription(set.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                user = new User();
                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setName(set.getString("name"));
                user.setWebsite(set.getString("website"));
                user.setDescription(set.getString("description"));
                String roleString = set.getString("role");
                if (roleString != null) {
                    Role role = Role.valueOf(roleString.toUpperCase());
                    user.setRole(role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
