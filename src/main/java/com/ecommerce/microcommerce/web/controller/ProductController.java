package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping(value="/Products")
    public MappingJacksonValue listProducts() {

        List<Product> productList = productDao.findAll();

        //Defining a filter to filter out buyingPrice from JSON
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("buyingPrice");
        FilterProvider filterList = new SimpleFilterProvider().addFilter("dynamicFilter", myFilter);
        MappingJacksonValue filteredProducts = new MappingJacksonValue(productList);

        filteredProducts.setFilters(filterList);
        return filteredProducts;
    }

    @PostMapping(value="/Products")
    public ResponseEntity<Void> addProduct(@RequestBody Product prod){

        Product addedProduct = productDao.save(prod);

        //Returns a 201 status for the post, or 204 if nothing added
        if(addedProduct == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(value="/Product/{id}")
    public Product displayProduct(@PathVariable int id){
        return productDao.findById(id).orElse(null);
    }


}
