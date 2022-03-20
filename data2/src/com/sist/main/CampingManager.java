package com.sist.main;

import java.io.*;

public class CampingManager {

    public static void main(String[] args) {
        
        FileInputStream fis = null;
        try {
            CampingDAO dao = new CampingDAO();
            CampingVO vo = new CampingVO();
            
            fis = new FileInputStream("C:\\oracleDev\\project2\\campingdata.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while (true) {
                
                String line = br.readLine();
                
                if(line == null) break; // 데이터 없으면 끝
                
                String array[] = line.split(",",-1); // split에 -1 포함시 공백 포함
                
                // 경도나 위도가 없거나 올바르지 않은 숫자면 패스
                if(array[5].isEmpty() || array[6].isEmpty() || !array[5].matches("[+-]?\\d*(\\.\\d+)?") || !array[6].matches("[+-]?\\d*(\\.\\d+)?"))
                    continue;
                
                vo.setCamp_name(array[0]);
                vo.setCamp_type(array[1].replace("|",","));
                vo.setCamp_facility1(array[2].replace("|",","));
                vo.setCamp_facility2(array[3].replace("|",","));
                vo.setCamp_address(array[4]);
                vo.setCamp_lat(Double.parseDouble(array[5]));
                vo.setCamp_lang(Double.parseDouble(array[6]));
                
                dao.campingInsert(vo);
                System.out.println("데이터 넣는중");
            }
        } catch (Exception ex) {
            System.err.println("빼액!!!!!!!!!!!!!!!!!!!!!!! 오류발생");
            ex.printStackTrace();
        }
    }
}