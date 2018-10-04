package com.ecommerce.microcommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
//@JsonFilter("dynamicFilter")
public class Product {

    //Not exposed:
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int price;
    private int priceBuyingPrice;

    public Product(int id, String name, int price, int buyingPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.priceBuyingPrice = buyingPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceBuyingPrice() {
        return priceBuyingPrice;
    }

    public void setPriceBuyingPrice(int priceBuyingPrice) {
        this.priceBuyingPrice = priceBuyingPrice;
    }

    public Product() {
    }
}
