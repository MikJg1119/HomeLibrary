package sample.model;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Book {
    private String title;
    private Author author;
    private String isbn;
    private StringBuilder urlBuild = new StringBuilder("https://isbnsearch.org/isbn/");

    public Book(String isbn) {
        this.isbn = isbn;
        urlBuild.append(isbn);
        try{

            Document webSite = Jsoup.connect(urlBuild.toString()).userAgent("mozilla/17.0").get();

            Elements results = webSite.select("h2.title");
            for (Element result : results) {
                System.out.println(result.getElementsByTag("span").first().text());
                this.title = result.getElementsByTag("span").first().text();
            }
            results = webSite.select("p.author");
            for (Element result : results) {
                System.out.println(result.getElementsByTag("strong").first().text());
                this.author = new Author(result.getElementsByTag("strong").first().text());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
