package com.sist.main;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CampingManager {

    public static void main(String[] args) {
        try {
            CampingDAO dao = new CampingDAO();
            
            for (int i = 1; i <= 50; i++) {

                Document doc = Jsoup.connect("https://www.5gcamp.com/?c=camping&p=" + i).timeout(30000).get();
                Elements types = doc.select("span.sbjcat"); // 목록에서 캠핑 타입
                Elements photos = doc.select("div.photo img"); // 목록에서 썸네일
                Elements campings = doc.select("div.subject"); // 목록에서 캠핑 링크
                
                for (int j = 0; j < campings.size(); j++) {
                    
                    CampingVO vo = new CampingVO();
                    
                    String path = campings.get(j).child(0).attr("href");
                    path = path.substring(1);
                    
                    Document doc2 = Jsoup.connect("https://www.5gcamp.com" + path).timeout(30000).get();
                    
                    // 상세 페이지 - 이름
                    String name = doc2.select("h3.camp_subject").text();
                    if (name.contains("진입불가")) continue; // 진입 불가가 있으면 스킵
                    
                    // 상세 페이지 - 주소
                    String address = doc2.select("h4.address a").get(0).text();
                    if (address.contains("(로그인 후 주소 확인 가능)")) continue; // 주소가 올바르지 않으면 스킵
                    
                    // 상세 페이지 - 내용
                    Elements temp = doc2.select("div.short_cont");
                    if (temp.isEmpty()) continue; // 내용이 없으면 스킵
                    String content = temp.get(0).text();
                    
                    
                    String type = types.get(j).text();
                    String photo = photos.get(j).attr("src");
                    
                    
                    vo.setCamp_name(name);
                    vo.setCamp_address(address);
                    vo.setCamp_type(type);
                    vo.setCamp_content(content);
                    vo.setCamp_photo(photo);
                    
                    System.out.println("-- 데이터 넣는 중 --");
                    dao.campingInsert(vo);
                }
            }
            System.out.println("끝");
        } catch (Exception ex) {
            System.err.println("빼액!!!!!!!!!!!!!!!!!!!!!!! 오류발생");
            ex.printStackTrace();
        }
    }
}