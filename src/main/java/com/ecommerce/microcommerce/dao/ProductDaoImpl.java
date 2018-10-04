package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static List<Product> productList = new ArrayList<>();
    static {
        productList.add(new Product(1,"Table", 20, 18));
        productList.add(new Product(2,"Chair", 5, 3));
        productList.add(new Product(3,"Couch", 40, 37));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Optional<Product> findById(int id) {
        for (Product prod : productList) {
            if (prod.getId() == id) {
                return Optional.of(prod);
            }
        }
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        productList.add(product);
        return product;
    }
}
