package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.main.*;

public class CategoryManager {

    public static void main(String[] args) {
        try {
            CategoryDAO dao = new CategoryDAO();
            CategoryVO vo = new CategoryVO();
            
            Document doc = Jsoup.connect("https://www.campinglist.co.kr/").timeout(30000).get();

            Elements cate = doc.select("div.m1 li");
            Elements link = cate.select("a");
            
            
            for (int i = 1; i <= 12; i++) {

                // 메인에서 대분류 이름 넣기
                System.out.println("----------대분류----------");
                if (i < 10) vo.setC_id("00" + i);
                else vo.setC_id("0" + i);

                vo.setC_name(cate.get(i).text());

                dao.categoryInsert(vo);
                System.out.println(vo.getC_name() + " " + vo.getC_id());
                
                // 대분류로 이동
                String path = "https://www.campinglist.co.kr" + link.get(i).attr("href");
                
                // 소분류 찾기
                Document doc2 = Jsoup.connect(path).timeout(30000).get();
                Elements cate2 = doc2.select("ul.depth1 li a");
                
                System.out.println("----------소분류----------");
                for (int j = 1; j < cate2.size(); j++) {
                    
                    if (i < 10) {
                        if (j < 10) vo.setC_id("00" + i + "0" + j);
                        else vo.setC_id("00" + i + j);
                    } else {
                        if (j < 10) vo.setC_id("0" + i + "0" + j);
                        else vo.setC_id("0" + i + j);
                    }
                    String name = cate2.get(j).text();
                    vo.setC_name(name.substring(0, name.lastIndexOf("()")));
                    dao.categoryInsert(vo);
                    System.out.println(vo.getC_name() + " " + vo.getC_id());
                }
            }
        } catch (Exception ex) {
            System.err.println("빼액!!!!!!!!!!!!!!!!!!!!!!! 오류발생");
            ex.printStackTrace();
        }
    }
}