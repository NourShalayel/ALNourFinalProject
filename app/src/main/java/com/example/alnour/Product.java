package com.example.alnour;

public class Product {

    private String id;
    private String name;
    private int code;
    private double price;
    private int unit;
    private String description;
    private String cat_id;
    private String sup_id;
    private String product_img;


    public Product(){

    }

    public Product(String id, String name, int code, double price, int unit, String description, String cat_id, String sup_id, String product_img) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.unit = unit;
        this.description = description;
        this.cat_id = cat_id;
        this.sup_id = sup_id;
        this.product_img = product_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public String getProImg() {
        return product_img;
    }

    public void setProImg(String product_img) {
        this.product_img = product_img;
    }
}
