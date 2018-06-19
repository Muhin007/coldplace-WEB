package com.github.muhin007.coldplaceweb;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class ParsingHTML {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.gismeteo.ru/weather-chelyabinsk-4565").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = String.valueOf(doc.select("div[class=value]"));


      System.out.println(title);

    }

}
