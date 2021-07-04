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

    public Book(String isbn) {
        this.isbn = isbn;

    }

    private StringBuilder urlBuild = new StringBuilder("https://isbnsearch.org/isbn/");

    public Book getBookByISBN (String isbn) throws IOException {
        urlBuild.append(isbn);
        URL url = new URL(urlBuild.toString());
        URLConnection conn = url.openConnection();
        Document webSite = Jsoup.connect(url.toString()).userAgent("mozilla/17.0").get();

        Elements results = webSite.select("h2.title");
        for (Element result:results){
            System.out.println(result.getElementsByTag("span").first().text());
            String title = result.getElementsByTag("span").first().text();
        }
        results = webSite.select("p.author");
        for (Element result:results){
            System.out.println(result.getElementsByTag("strong").first().text());
            String authorName = result.getElementsByTag("strong").first().text();
        }

        return new Book(isbn);


    }
}
