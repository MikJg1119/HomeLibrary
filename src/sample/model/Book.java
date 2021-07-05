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
    private StringBuilder urlBuild = new StringBuilder("https://www.abebooks.com/servlet/SearchResults?sts=t&isbn=");
    private String publisher;
    private String publishedYear;
    private String language=null;

    public Book(String isbn) {
        this.isbn = isbn;
        urlBuild.append(isbn);
        try{

            Document webSite = Jsoup.connect(urlBuild.toString()).userAgent("mozilla/17.0").get(); //connection to the website
            // getting title based on ISBN
            Elements results = webSite.select("h2.title");
            for (Element result : results) {
                this.title = result.getElementsByTag("span").first().text();
            }
            // getting author
            results = webSite.select("p.author");
            for (Element result : results) {
                this.author = new Author(result.getElementsByTag("strong").first().text());
            }
            // getting publisher
            results = webSite.select("span.opt-publisher");
            for (Element result:results){
                this.publisher = result.getElementsByTag("span").first().text();
            }
            //getting year published
            results = webSite.select("span.opt-publish-date");
            for (Element result:results){
                this.publishedYear =  result.getElementsByTag("span").first().text();
            }

            if (this.language==null){
                this.language="Polski";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
