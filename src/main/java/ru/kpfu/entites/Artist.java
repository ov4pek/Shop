package ru.kpfu.entites;

/**
 * Created by Admin on 01.11.2016.
 */
public class Artist {
    private String name;
    private String sizeListener;
    private String img;

    public Artist(String name, String sizeListener, String img) {
        this.name = name;
        this.sizeListener = sizeListener;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getSizeListener() {
        return sizeListener;
    }

    public String getImg() {
        return img;
    }
}
