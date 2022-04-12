package com.sist.main;

import java.sql.*;

import com.sist.main.*;

public class GoodsDAO {
    
    private Connection conn;
    private PreparedStatement ps;
    private DataBase db = new DataBase();
    
    
    public void goodsInsert(GoodsVO vo) {
        try {
            conn = db.getConnection(conn);

            String sql = "INSERT INTO goods_1 VALUES (goods_id_seq_1.NEXTVAL,?,?,?,?,0,?,?,0,1,SYSDATE)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, vo.getC_id());
            ps.setString(2, vo.getG_name());
            ps.setString(3, vo.getG_brand());
            ps.setInt(4, vo.getG_price());
            ps.setString(5, vo.getG_image());
            ps.setString(6, vo.getG_detail());
            
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.disConnection(conn, ps);
        }
    }
}