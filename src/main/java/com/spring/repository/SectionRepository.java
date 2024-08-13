package com.spring.repository;

import com.spring.model.Section;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SectionRepository {
    public static Connection con = null;

    static {
        con = MyConnection.getConn();
    }
    public Section getSectionById(int id) {
        Section section = null;
        String query = "SELECT * FROM section WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set= stmt.executeQuery();
            while(set.next()) {
                section = new Section();
                section.setId(set.getInt("id"));
                section.setName(set.getString("name"));
                Timestamp timestamp = set.getTimestamp("updated_at");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime updatedAt = timestamp.toLocalDateTime();
                section.setUpdatedAtFormatted(updatedAt.format(formatter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return section;
    }

    public Section getSectionByName(String name) {
        Section section = null;
        String query = "SELECT * FROM section WHERE name = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet set= stmt.executeQuery();
            while(set.next()) {
                section = new Section();
                section.setId(set.getInt("id"));
                section.setName(set.getString("name"));
                Timestamp timestamp = set.getTimestamp("updated_at");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime updatedAt = timestamp.toLocalDateTime();
                section.setUpdatedAtFormatted(updatedAt.format(formatter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return section;
    }

    public List<Section> getAllSections() {
        List<Section> sections = new ArrayList<>();
        String query = "SELECT * FROM section";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                Section section = new Section();
                section.setId(set.getInt("id"));
                section.setName(set.getString("name"));
                Timestamp timestamp = set.getTimestamp("updated_at");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDateTime updatedAt = timestamp.toLocalDateTime();
                section.setUpdatedAtFormatted(updatedAt.format(formatter));
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }

    public int save(String name) {
        int result = 0;
        String query = "INSERT INTO section(name) VALUES(?)";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
