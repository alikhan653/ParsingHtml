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
    private static final String URI = "https://stopgame.ru/blogs/topic/";
    public static void main(String[] args) throws IOException {

//        List<Post> posts = new ArrayList<>();
//
//        Document doc = Jsoup.connect(URI).get();
//        System.out.println("Подключение к главной странице...");
//        Elements postTitleElements = doc.getElementsByAttributeValue("itemprop","url");
//
//        for(Element postTitleElement : postTitleElements){
//            String detailsLink = postTitleElement.attr("href");
//            Post post = new Post();
//            post.setDetailsLink(detailsLink);
//            post.setTitle(postTitleElement.attr("title"));
//            Document postDetailsDoc = Jsoup.connect(detailsLink).get();
//            System.out.println("Подключение к деталям о посте..." + detailsLink);
//            try {
//                Element authorElement = postDetailsDoc.getElementsByClass("name").first().child(0);
//                post.setAuthor(authorElement.text());
//                post.setAuthorDetailsLink(authorElement.attr("href"));
//            } catch (NullPointerException e){
//                post.setAuthor("No Author");
//                post.setAuthorDetailsLink("No Author link");
//            }
//            Element date = postDetailsDoc.getElementsByClass("date").first();
//            post.setDateOfCreated(date.text());
//            posts.add(post);
//        }
        parsePosts().forEach(System.out::println);
    }

    private static List<Post> parsePosts() throws IOException {
        List<Post> posts = new ArrayList<>();
        for(int i = 1;i<=1;i++){
            System.out.println("Connecting to the " + i + " page.");
            Document doc = Jsoup.connect(i == 1 ? URI : URI + "p" + i + "/").get();
            Elements postTitleElements = doc.getElementsByClass("_card__image_givrd_407");
            for (Element postTitleElement : postTitleElements){
                String detailsLink = postTitleElement.attr("href");
                Post post = new Post();
                post.setDetailsLink(detailsLink);
                System.out.println("Connecting to the post - " + detailsLink);
                Document postDetailsDoc = Jsoup.connect("https://stopgame.ru" + detailsLink).get();
                post.setTitle(postDetailsDoc.title());
                Element author = postDetailsDoc.getElementsByClass("_author_pxu4o_1327").first().child(1);
                post.setAuthor(author.text());
                String authorDetailsLink = postDetailsDoc.getElementsByClass("_author_pxu4o_1327").attr("href");
                post.setAuthorDetailsLink(authorDetailsLink);
                Element date = postDetailsDoc.getElementsByClass("_date_pxu4o_538 _date--full_pxu4o_1").first();
                post.setDateOfCreated(date.text());
                posts.add(post);
            }
        }
        return posts;
    }
}