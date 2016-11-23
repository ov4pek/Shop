package ru.kpfu.entites;

/**
 * Created by danil on 23.11.16.
 */
public class Comment {
   private String userName;
   private String text;
   private String date;
    private int catalogId;

    public Comment(String userName, String text, String date) {
        this.userName = userName;
        this.text = text;
        this.date = date;
    }

    public Comment(String userName, String text, String date, int catalogId) {
        this.userName = userName;
        this.text = text;
        this.date = date;
        this.catalogId = catalogId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
