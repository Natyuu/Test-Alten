package com.example.alten.utils;

import com.example.alten.model.Product;

public class ProductUtils {

    public static final long ID_1 = 1;
    public static final long ID_2 = 2;
    public static final String NAME_1 = "Bamboo Watch";
    public static final String NAME_2 = "Black Watch";
    public static final String DESCRIPTION = "Product Description";
    public static final String CODE_1 = "f230fh0g3";
    public static final String CODE_2 = "nvklal433";
    public static final String IMAGE_1 = "bamboo-watch.jpg";
    public static final String IMAGE_2 = "black-watch.jpg";
    public static final int PRICE_1 = 65;
    public static final int PRICE_2 = 72;
    public static final String CATEGORY = "Accessories";
    public static final int QUANTITY_1 = 24;
    public static final int QUANTITY_2 = 61;
    public static final String INVENTORYSTATUS = "INSTOCK";
    public static final int RATING_1 = 5;
    public static final int RATING_2 = 4;

    public static Product createProduct(long id, String name, String code, String description, int price, int quantity,
                                        String inventoryStatus, String category, String image, int rating){
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setInventoryStatus(inventoryStatus);
        product.setCategory(category);
        product.setImage(image);
        product.setRating(rating);

        return product;
    }

    public static Product createProduct(String name, String code, String description, int price, int quantity,
                                        String inventoryStatus, String category, String image, int rating){
        Product product = new Product();
        product.setName(name);
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setInventoryStatus(inventoryStatus);
        product.setCategory(category);
        product.setImage(image);
        product.setRating(rating);

        return product;
    }
}
