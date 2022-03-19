package com.sist.main;

import java.sql.*;

import com.sist.main.*;

public class CategoryDAO {
    
    private Connection conn;
    private PreparedStatement ps;
    private DataBase db = new DataBase();
    
    
    public void categoryInsert(CategoryVO vo) {
        try {
            conn = db.getConnection(conn);

            String sql = "INSERT INTO category_1 VALUES (?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, vo.getC_id());
            ps.setString(2, vo.getC_name());
            
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
    }
}