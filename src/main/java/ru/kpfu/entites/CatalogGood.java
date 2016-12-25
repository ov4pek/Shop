package ru.kpfu.entites;

/**
 * Created by danil on 18.11.2016.
 */
public class CatalogGood {
    private int catalogId;
    private String name;
    private int price;
    private String description;
    private String img;
    private String type;

    public CatalogGood(int catalogId, String name, int price, String description, String img) {
        this.catalogId = catalogId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }
    public CatalogGood(String name, int price, String description, String img) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }
    public CatalogGood(String name, float price, String description, String img, String type) {
        this.name = name;
        this.price = (int)price;
        this.description = description;
        this.img = img;
        this.type = type;
    }
    public CatalogGood(int price, String name,  String description, String type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }
}
