package com.assessment.part1.problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class ProductFilter {
 
    public static class Product{
        private int id;
        private String name;
        private double price;
        private String category;

        public Product(int id, String name, double price, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }
    }

    public List<Product> filterProducts(List<Product> products, Double minPrice, Double maxPrice, String category){

        Predicate<Product> predicate = product -> true;

        if (minPrice != null){
            predicate = predicate.and(product -> product.getPrice() >= minPrice);
        }

        if (maxPrice != null){
            predicate = predicate.and(product -> product.getPrice() <= maxPrice);
        }

        if (category != null){
            predicate = predicate.and(product -> category.equals(product.getCategory()));
        }

        return products.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        ProductFilter pf = new ProductFilter();

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 1200.0, "Electronics"));
        products.add(new Product(2, "Smartphone", 800.0, "Electronics"));
        products.add(new Product(3, "Jeans", 50.0, "Fashion"));
        products.add(new Product(4, "Blender", 150.0, "Home Appliances"));

        List<Product> result = pf.filterProducts(products, 100.0, 1000.0, "Electronics");
        List<Product> result2 = pf.filterProducts(products, 50.0, 70.0, "Fashion");
     
        System.out.println("Result Size: " + result.size());
        System.out.println("Result2 Size: " + result2.size());
    }

    
}

