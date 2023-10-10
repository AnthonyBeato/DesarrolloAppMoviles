package com.example.parcialuno.dto;

import com.example.parcialuno.entities.Product;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductList implements Serializable {

    @SerializedName("products")
    public List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }
}
