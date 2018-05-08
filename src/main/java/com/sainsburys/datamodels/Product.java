package com.sainsburys.datamodels;

public class Product {

    private String title;
    private String kcal_per_100g = "";
    private double unit_price;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalories() {
        return kcal_per_100g;
    }

    public void setCalories(String calories) {
        this.kcal_per_100g = calories;
    }

    public double getUnitPrice() {
        return unit_price;
    }

    public void setUnitPrice(double unitPrice) {
        this.unit_price = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitPriceAsString() {
        return "£" + ((Double) unit_price).toString();
    }
}
