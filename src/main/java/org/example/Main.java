package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Post> posts = new ArrayList<>();

        Document doc = Jsoup.connect("https://4pda.to/").get();
        System.out.println("Подключение к главной странице...");
        Elements postTitleElements = doc.getElementsByAttributeValue("itemprop","url");

        for(Element postTitleElement : postTitleElements){
            String detailsLink = postTitleElement.attr("href");
            Post post = new Post();
            post.setDetailsLink(detailsLink);
            post.setTitle(postTitleElement.attr("title"));
            Document postDetailsDoc = Jsoup.connect(detailsLink).get();
            System.out.println("Подключение к деталям о посте..." + detailsLink);
            try {
                Element authorElement = postDetailsDoc.getElementsByClass("name").first().child(0);
                post.setAuthor(authorElement.text());
                post.setAuthorDetailsLink(authorElement.attr("href"));
            } catch (NullPointerException e){
                post.setAuthor("No Author");
                post.setAuthorDetailsLink("No Author link");
            }
            Element date = postDetailsDoc.getElementsByClass("date").first();
            post.setDateOfCreated(date.text());
            posts.add(post);
        }
        posts.forEach(System.out::println);


    }
}