package com.spring.repository;

import com.spring.model.Section;
import com.spring.model.Subsection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubsectionRepository {
    @Autowired
    private SectionRepository sectionRepository;
    public static Connection con = null;

    static {
        con = MyConnection.getConn();
    }
    public Subsection getSubsectionById(int id) {
        Subsection subsection = null;
        String query = "SELECT * FROM subsection WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            while(set.next()) {
                subsection = new Subsection();
                subsection.setId(set.getInt("id"));
                subsection.setName(set.getString("name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subsection;
    }

    public List<Subsection> getSubsectionBySectionId(int sectionId) {
        String query = "SELECT * FROM subsection WHERE section_id = ?";
        List<Subsection> subsections = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, sectionId);
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Subsection subsection = new Subsection();
                subsection.setId(set.getInt("id"));
                subsection.setName(set.getString("name"));
                subsections.add(subsection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subsections;
    }

    public List<Subsection> getAllSubsections() {
        List<Subsection> subsections = new ArrayList<>();
        String query = "SELECT * FROM subsection";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                Subsection subsection = new Subsection();
                subsection.setId(set.getInt("id"));
                subsection.setType(set.getString("type"));
                subsection.setName(set.getString("name"));
                Section section = sectionRepository.getSectionById(set.getInt("section_id"));
                if (section != null) {
                    subsection.setSection(section);
                }
                subsections.add(subsection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subsections;
    }
}
