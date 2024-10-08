package com.spring.repository;

import com.spring.entity.Subsection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SubsectionRepository implements SubsectionInterface {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Subsection> getAllSubsections() {
        Query query = em.createQuery("SELECT s FROM Subsection s WHERE s.isDeleted = false", Subsection.class);
        List<Subsection> subsectionList = query.getResultList();
        return subsectionList;
    }
    @Override
    public List<Subsection> getSubsectionBySectionId(int sectionId) {
        Query query = em.createQuery("SELECT s FROM Subsection s WHERE s.section.id = :sectionId", Subsection.class);
        return query.setParameter("sectionId", sectionId).getResultList();
    }

    @Override
    public Subsection getSubsectionById(int id) {
        Subsection subsection = null;
        try {
            Query query = em.createQuery("SELECT s FROM Subsection s WHERE s.id = :id", Subsection.class);
            query.setParameter("id", id);
            subsection = (Subsection) query.getSingleResult();
        } catch (Exception e) {
            subsection = null;
            System.out.println(e.getMessage());
        }
        return subsection;
    }

    @Override
    public Subsection getSubsectionByName(String name) {
        Subsection subsection = null;
        try {
            Query query = em.createQuery("SELECT s FROM Subsection s WHERE s.name = :name", Subsection.class);
            query.setParameter("name", name);
            subsection = (Subsection) query.getSingleResult();
        } catch (Exception e) {
            subsection = null;
            return subsection;
        }
        return subsection;
    }

    @Transactional
    @Override
    public int save(Subsection subsection) {
        int result = 0;
        try {
            em.persist(subsection);
            result = 1;
        } catch (Exception e) {
            result = 0;
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Transactional
    @Override
    public int update(Subsection subsection) {
        int result = 0;
        try {
            em.merge(subsection);
            result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = 0;
        }
        return result;
    }
}
//package com.spring.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class SubsectionRepository {
//    @Autowired
//    private SectionRepository sectionRepository;
//    public static Connection con = null;
//
//    static {
//        con = MyConnection.getConn();
//    }
//    public Subsection getSubsectionById(int id) {
//        Subsection subsection = null;
//        String query = "SELECT * FROM subsection WHERE id = ?";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setInt(1, id);
//            ResultSet set = stmt.executeQuery();
//            while(set.next()) {
//                subsection = new Subsection();
//                subsection.setId(set.getInt("id"));
//                subsection.setName(set.getString("name"));
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return subsection;
//    }
//
//    public Subsection getSubsectionByName(String name) {
//        Subsection subsection = null;
//        String query = "SELECT * FROM subsection WHERE name = ?";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setString(1, name);
//            ResultSet set = stmt.executeQuery();
//            while(set.next()) {
//                subsection = new Subsection();
//                subsection.setId(set.getInt("id"));
//                subsection.setName(set.getString("name"));
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return subsection;
//    }
//
//    public List<Subsection> getSubsectionBySectionId(int sectionId) {
//        String query = "SELECT * FROM subsection WHERE section_id = ?";
//        List<Subsection> subsections = new ArrayList<>();
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setInt(1, sectionId);
//            ResultSet set = stmt.executeQuery();
//            while (set.next()) {
//                Subsection subsection = new Subsection();
//                subsection.setId(set.getInt("id"));
//                subsection.setName(set.getString("name"));
//                subsections.add(subsection);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return subsections;
//    }
//
//    public List<Subsection> getAllSubsections() {
//        List<Subsection> subsections = new ArrayList<>();
//        String query = "SELECT * FROM subsection";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            ResultSet set = stmt.executeQuery();
//            while (set.next()) {
//                Subsection subsection = new Subsection();
//                subsection.setId(set.getInt("id"));
//                subsection.setType(set.getString("type"));
//                subsection.setName(set.getString("name"));
//                Section section = sectionRepository.getSectionById(set.getInt("section_id"));
//                Timestamp timestamp = set.getTimestamp("updated_at");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                LocalDateTime updatedAt = timestamp.toLocalDateTime();
//                subsection.setUpdatedAtFormatted(updatedAt.format(formatter));
//                subsection.setSection(section);
//                subsections.add(subsection);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return subsections;
//    }
//
//    public int save(String name, String type, int sectionId) {
//        System.out.println(sectionId);
//        int result = 0;
//        String query = "INSERT INTO subsection(name, type, section_id) VALUES(?, ? , ?)";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setString(1, name);
//            stmt.setString(2, type);
//            stmt.setInt(3, sectionId);
//            result = stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
//}
