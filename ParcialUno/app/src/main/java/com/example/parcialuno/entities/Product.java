package com.example.parcialuno.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product {

    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("price")
    public double price;
    @SerializedName("discountPercentage")
    public double discountPercentage;
    @SerializedName("rating")
    public double rating;
    @SerializedName("stock")
    public  int stock;
    @SerializedName("brand")
    public String brand;
    @SerializedName("category")
    public String category;
    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("images")
    public List<String> images = new ArrayList<>();

    public Product(int id, String title, String description, double price, double discountPercentage, double rating, int stock, String brand, String category, String thumbnail, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", discountPercentage='" + discountPercentage + '\'' +
                ", rating='" + rating + '\'' +
                ", stock='" + stock + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
