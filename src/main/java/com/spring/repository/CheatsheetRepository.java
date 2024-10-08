package com.spring.repository;

import com.spring.dto.BlockDTO;
import com.spring.dto.CellDTO;
import com.spring.entity.Block;
import com.spring.entity.Cell;
import com.spring.entity.Cheatsheet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CheatsheetRepository implements CheatsheetInterface {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cheatsheet> getAllCheatsheets() {
        Query query = em.createQuery("SELECT c FROM Cheatsheet c", Cheatsheet.class);
        List<Cheatsheet> cheatsheets = query.getResultList();
        return cheatsheets;
    }

    @Transactional
    @Override
    public int addCheatsheet(Cheatsheet cheatsheet) {
        int result = 0;
        try {
            em.persist(cheatsheet);
            result = 1;
        } catch (Exception e) {
            result = 0;
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Cheatsheet> getCheatsheetsByUser(int userId) {
        List<Cheatsheet> cheatsheetList = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Cheatsheet c WHERE c.user.id = :id", Cheatsheet.class);
            query.setParameter("id", userId);
            cheatsheetList = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return cheatsheetList;
    }

    @Override
    public Cheatsheet getCheatsheetById(int id) {
        Cheatsheet cheatsheet = null;
        try {
            Query query = em.createQuery("SELECT c FROM Cheatsheet c WHERE c.id = :id", Cheatsheet.class);
            query.setParameter("id", id);
            cheatsheet = (Cheatsheet) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return cheatsheet;
        }
        return cheatsheet;
    }

    @Override
    public List<Cheatsheet> getCheatsheetsBySection(int sectionId) {
        List<Cheatsheet> cheatsheetList = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Cheatsheet c WHERE c.section.id = :id", Cheatsheet.class);
            query.setParameter("id", sectionId);
            cheatsheetList = query.getResultList();
        } catch (Exception e) {
            cheatsheetList = null;
            System.out.println(e.getMessage());
            return cheatsheetList;
        }
        return cheatsheetList;
    }

    @Transactional
    @Override
    public Block saveBlock(Block block) {
        try {
            em.persist(block);
            return block;
        } catch (Exception e) {
            block = null;
            System.out.println(e.getMessage());
        }
        return block;
    }

    @Transactional
    @Override
    public int saveCells(Cell cell) {
        int result = 0;
        try {
            em.persist(cell);
            result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = 0;
        }
        return result;
    }

    @Override
    public Block findBlockById(int id) {
        Block block = null;
        try {
            Query query = em.createQuery("SELECT b FROM Block b WHERE b.id = :id", Block.class);
            query.setParameter("id", id);
            block = (Block) query.getSingleResult();
        } catch (Exception e) {
            block = null;
            System.out.println(e.getMessage());
            return block;
        }
        return block;
    }

    @Override
    public List<BlockDTO> getBlocksByCheatsheet(int cheatsheetId) {
        // Query to fetch blocks with their cells
        List<Object[]> result = em.createQuery("SELECT b.id, b.blockTitle, cs.description, c.rowNum, c.colNum, c.content " +
                        "FROM Cell c " +
                        "JOIN c.block b " +
                        "JOIN b.cheatsheet cs " +
                        "WHERE cs.id = :cheatsheetId " +
                        "ORDER BY b.id, c.rowNum, c.colNum")
                .setParameter("cheatsheetId", cheatsheetId)
                .getResultList();

        Map<Integer, BlockDTO> blocksMap = new HashMap<>();
        for (Object[] row : result) {
            int blockId = (int) row[0];
            String blockTitle = (String) row[1];
            String description = (String) row[2];
            int rowNum = (int) row[3];
            int colNum = (int) row[4];
            String content = (String) row[5];

            BlockDTO block = blocksMap.get(blockId);
            if (block == null) {
                block = new BlockDTO(blockId, blockTitle, description, new ArrayList<>());
                blocksMap.put(blockId, block);
            }

            List<List<CellDTO>> rows = block.getRows();
            while (rows.size() <= rowNum) {
                rows.add(new ArrayList<>());
            }
            rows.get(rowNum).add(new CellDTO(colNum, content));
        }

        return new ArrayList<>(blocksMap.values());
    }
}
//package com.spring.repository;
//
//import com.spring.model.Cheatsheet;
//import com.spring.model.Section;
//import com.spring.model.Subsection;
//import com.spring.model.UserOld;
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
//public class CheatsheetRepository {
//    @Autowired
//    private SectionRepository sectionRepository;
//
//    @Autowired
//    private SubsectionRepository subsectionRepository;
//    public static Connection con = null;
//
//    static {
//        con = MyConnection.getConn();
//    }
//    public List<Cheatsheet> getAllCheatsheets() {
//        List<Cheatsheet> cheatsheets = new ArrayList<>();
//
//        String query = "SELECT * FROM cheatsheet";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            ResultSet set = stmt.executeQuery();
//            while(set.next()) {
//                Cheatsheet cheatsheet = new Cheatsheet();
//                cheatsheet.setId(set.getInt("id"));
//                cheatsheet.setName(set.getString("name"));
//                cheatsheet.setDescription(set.getString("description"));
//                cheatsheet.setColor(set.getString("color"));
//                cheatsheet.setContent(set.getString("content"));
//                cheatsheet.setStyle(set.getString("style"));
//                cheatsheet.setType(set.getString("type"));
//                UserOld user = userRepository.getUserById(set.getInt("user_id"));
//                if (user != null) {
//                    cheatsheet.setUser(user);
//                }
//                Timestamp timestamp = set.getTimestamp("updated_at");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                LocalDateTime updatedAt = timestamp.toLocalDateTime();
//                cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
//                cheatsheets.add(cheatsheet);
//            }
//        } catch (SQLException e) {
//            System.out.println("select error: " + e);
//        }
//        return cheatsheets;
//    }
//
//    public List<Cheatsheet> getCheatsheetsBySection(int sectionId) {
//        List<Cheatsheet> cheatsheets = new ArrayList<>();
//
//        String query = "SELECT * FROM cheatsheet WHERE section_id = ?";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setInt(1, sectionId);
//            ResultSet set = stmt.executeQuery();
//            while(set.next()) {
//                Cheatsheet cheatsheet = new Cheatsheet();
//                cheatsheet.setId(set.getInt("id"));
//                cheatsheet.setName(set.getString("name"));
//                cheatsheet.setDescription(set.getString("description"));
//                cheatsheet.setColor(set.getString("color"));
//                cheatsheet.setContent(set.getString("content"));
//                cheatsheet.setStyle(set.getString("style"));
//                cheatsheet.setType(set.getString("type"));
//                UserOld user = userRepository.getUserById(set.getInt("user_id"));
//                if (user != null) {
//                    cheatsheet.setUser(user);
//                }
//                Timestamp timestamp = set.getTimestamp("updated_at");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                LocalDateTime updatedAt = timestamp.toLocalDateTime();
//                cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
//                cheatsheets.add(cheatsheet);
//            }
//        } catch (SQLException e) {
//            System.out.println("select error: " + e);
//        }
//        return cheatsheets;
//    }
//
//    public Cheatsheet getCheatsheetById(int id) {
//        Cheatsheet cheatsheet = null;
//        String query = "SELECT * FROM cheatsheet WHERE id = ?";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setInt(1, id);
//            ResultSet set = stmt.executeQuery();
//            while(set.next()) {
//                cheatsheet = new Cheatsheet();
//                cheatsheet.setId(set.getInt("id"));
//                cheatsheet.setName(set.getString("name"));
//                cheatsheet.setDescription(set.getString("description"));
//                cheatsheet.setColor(set.getString("color"));
//                cheatsheet.setContent(set.getString("content"));
//                cheatsheet.setStyle(set.getString("style"));
//                cheatsheet.setType(set.getString("type"));
//                cheatsheet.setLanguage(set.getString("language"));
//                Section section = sectionRepository.getSectionById(set.getInt("section_id"));
//                Subsection subsection = subsectionRepository.getSubsectionById(set.getInt("subsection_id"));
//                if(section != null && subsection != null) {
//                    cheatsheet.setSection(section);
//                    cheatsheet.setSubsection(subsection);
//                }
//                UserOld user = userRepository.getUserById(set.getInt("user_id"));
//                if (user != null) {
//                    cheatsheet.setUser(user);
//                }
//                Timestamp timestamp = set.getTimestamp("updated_at");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                LocalDateTime updatedAt = timestamp.toLocalDateTime();
//                cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return cheatsheet;
//    }
//
//    public int save(String name, String description, String color, String content, String style, String type, String language, String layout, int userId, int sectionId, int subsectionId) {
//        int result = 0;
//        String query = "INSERT INTO cheatsheet(name, description, color, content, style, type, language, user_id, section_id, subsection_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try {
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setString(1, name);
//            stmt.setString(2, description);
//            stmt.setString(3, color);
//            stmt.setString(4, content);
//            stmt.setString(5, style);
//            stmt.setString(6, type);
//            stmt.setString(7, language);
//            stmt.setInt(8, userId);
//            stmt.setInt(9, sectionId);
//            stmt.setInt(10, subsectionId);
//            result = stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
//}
