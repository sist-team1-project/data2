package com.sist.main;

import java.sql.*;

import com.sist.main.*;

public class CampingDAO {
    
    private Connection conn;
    private PreparedStatement ps;
    private DataBase db = new DataBase();
    
    
    public void campingInsert(CampingVO vo) {
        try {
            conn = db.getConnection(conn);

            String sql = "INSERT INTO camping_1 VALUES (camping_id_seq_1.NEXTVAL,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, vo.getCamp_name());
            ps.setString(2, vo.getCamp_type());
            ps.setString(3, vo.getCamp_facility1());
            ps.setString(4, vo.getCamp_facility2());
            ps.setString(5, vo.getCamp_address());
            ps.setDouble(6, vo.getCamp_lat());
            ps.setDouble(7, vo.getCamp_lang());
            
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
    }
}