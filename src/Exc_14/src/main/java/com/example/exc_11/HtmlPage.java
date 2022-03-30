package com.example.exc_11;

public class HtmlPage {
    String get(String name, String group, int n) {
        return "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "  <meta charset='UTF-8'>\n" +
                "  <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h2>"+name+"</h2>\n" +
                "  <h2>"+group+"</h2>\n" +
                "  <h2>"+n+"</h2>\n" +
                "</body>\n" +
                "</html>";
    }
}
