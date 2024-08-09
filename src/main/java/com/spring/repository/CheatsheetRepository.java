package com.spring.repository;

import com.spring.model.Cheatsheet;
import com.spring.model.Section;
import com.spring.model.Subsection;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CheatsheetRepository {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubsectionRepository subsectionRepository;
    public static Connection con = null;

    static {
        con = MyConnection.getConn();
    }
    public List<Cheatsheet> getAllCheatsheets() {
        List<Cheatsheet> cheatsheets = new ArrayList<>();

        String query = "SELECT * FROM cheatsheet";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                Cheatsheet cheatsheet = new Cheatsheet();
                cheatsheet.setId(set.getInt("id"));
                cheatsheet.setName(set.getString("name"));
                cheatsheet.setDescription(set.getString("description"));
                cheatsheet.setColor(set.getString("color"));
                cheatsheet.setContent(set.getString("content"));
                cheatsheet.setStyle(set.getString("style"));
                cheatsheet.setType(set.getString("type"));
                User user = userRepository.getUserById(set.getInt("user_id"));
                if (user != null) {
                    cheatsheet.setUser(user);
                }
                Timestamp timestamp = set.getTimestamp("updated_at");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime updatedAt = timestamp.toLocalDateTime();
                cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
                cheatsheets.add(cheatsheet);
            }
        } catch (SQLException e) {
            System.out.println("select error: " + e);
        }
        return cheatsheets;
    }

    public Cheatsheet getCheatsheetById(int id) {
        Cheatsheet cheatsheet = null;
        String query = "SELECT * FROM cheatsheet WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                cheatsheet = new Cheatsheet();
                cheatsheet.setId(set.getInt("id"));
                cheatsheet.setName(set.getString("name"));
                cheatsheet.setDescription(set.getString("description"));
                cheatsheet.setColor(set.getString("color"));
                cheatsheet.setContent(set.getString("content"));
                cheatsheet.setStyle(set.getString("style"));
                cheatsheet.setType(set.getString("type"));
                cheatsheet.setLanguage(set.getString("language"));
                Section section = sectionRepository.getSectionById(set.getInt("section_id"));
                Subsection subsection = subsectionRepository.getSubsectionById(set.getInt("subsection_id"));
                if(section != null && subsection != null) {
                    cheatsheet.setSection(section);
                    cheatsheet.setSubsection(subsection);
                }
                User user = userRepository.getUserById(set.getInt("user_id"));
                if (user != null) {
                    cheatsheet.setUser(user);
                }
                Timestamp timestamp = set.getTimestamp("updated_at");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime updatedAt = timestamp.toLocalDateTime();
                cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cheatsheet;
    }
}
