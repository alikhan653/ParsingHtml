package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://4pda.to/").get();
        Elements postTitleElements = doc.getElementsByAttributeValue("itemprop","url");
        postTitleElements.forEach(postTitleElement -> System.out.println(postTitleElement.attr("title") + " | " + postTitleElement.attr("href")));


    }
}