package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.main.*;

public class GoodsManager {

    public static void main(String[] args) {
        try {
            GoodsDAO dao = new GoodsDAO();
            GoodsVO vo = new GoodsVO();
            Document doc = Jsoup.connect("https://www.campinglist.co.kr/").timeout(30000).get();

            Elements cate = doc.select("div.m1 li");
            Elements link = cate.select("a");
            
            
            for (int i = 1; i <= 8; i++) {
                
                if (i==7) continue;
                // 메인에서 대분류로 이동
                String path = "https://www.campinglist.co.kr" + link.get(i).attr("href");
                Document doc2 = Jsoup.connect(path).timeout(30000).get();
                
                Elements cate2 = doc2.select("ul.depth1 li a");
                for (int j = 1; j < cate2.size(); j++) {
                    
                    // 대분류에서 소분류로 이동
                    String cate2path = "https://www.campinglist.co.kr" + cate2.get(j).attr("href");
                    Document doc3 = Jsoup.connect(cate2path).timeout(30000).get();
                    
                    // 소분류 카테고리 지정
                    if (i < 10) {
                        if (j < 10) vo.setC_id("00" + i + "0" + j);
                        else vo.setC_id("00" + i + j);
                    } else {
                        if (j < 10) vo.setC_id("0" + i + "0" + j);
                        else vo.setC_id("0" + i + j);
                    }
                    Elements glinks = doc3.select("div.df-prl-thumb a");
                    
                    for(int k = 0; k < glinks.size(); k++) {
                        // 제품 페이지로 이동
                        String glink = "https://www.campinglist.co.kr" + glinks.get(k).attr("href");
                        Document doc4 = Jsoup.connect(glink).timeout(30000).get();
                        
                        // 제품 이름
                        vo.setG_name(doc4.select("tr.product_name_css td").text());
                        
                        // 제품 브랜드
                        vo.setG_brand(doc4.select("tr.prd_brand_css td").text());
                        
                        // 제품 가격
                        String gprice = doc4.select("tr.product_price_css td").text();
                        if (gprice.equals("매장 판매 전용") || gprice.equals("스마트스토어 구매")) break; // 매장 판매 전용이나 스마트스토어 구매 이면 가격이 없으므로 스킵
                        int price = Integer.parseInt(gprice.replaceAll("[^0-9]","")); // 숫자만 추출
                        vo.setG_price(price);
                        
                        // 제품 이미지
                        Elements images = doc4.select("div.thumb img");
                        String image = "";
                        for (int h = 0; h < images.size(); h++) {
                            String temp = doc4.select("div.thumb img").get(h).attr("src");
                            image +=  "https://" + temp.substring(temp.indexOf("//") + 2) + ";";
                        }
                        image = image.substring(0, image.lastIndexOf(";"));
                        vo.setG_image(image);
                        
                        // 제품 상세
                        Elements details = doc4.select("div.cont img");
                        String detail = "";
                        for (int h = 0; h < details.size(); h++) {
                            String temp = details.get(h).attr("src");
                            
                            if(temp.startsWith("/web/upload/NNEditor/") && !temp.endsWith("_head.jpg") && !temp.endsWith("intro.jpg") && !temp.endsWith("377ff376212522bdd52106491350ea63.jpg") && !temp.endsWith("KakaoTalk_20201204_104029915.jpg") && !temp.endsWith("copy-1632732276-35a9f56db4ba58d6fc7f98ff9ee25557.jpg") && !temp.endsWith("1087e68914fd134227a5fa72d79b20b1.jpg")) {
                                detail = "https://campinglist.co.kr" + temp;
                                break;
                            }
                        }
                        vo.setG_detail(detail);
                        // 데이터 넣기
                        dao.goodsInsert(vo);
                    }
                    System.out.println("카테고리 " + vo.getC_id() + " 데이터 넣음...");
                }
            }
            System.out.println("---------- 끝 ----------");
        } catch (Exception ex) {
            System.err.println("빼액!!!!!!!!!!!!!!!!!!!!!!! 오류발생");
            ex.printStackTrace();
        }
    }
}
