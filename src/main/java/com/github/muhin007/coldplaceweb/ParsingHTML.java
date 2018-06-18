package com.github.muhin007.coldplaceweb;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParsingHTML {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://rp5.ru/Погода_в_Челябинске").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = String.valueOf(doc.select("forecastShort-content"));
      System.out.println(title);

    }

}
