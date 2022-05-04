package com.kupercy.controller;

import com.kupercy.dto.ProductRequest;
import com.kupercy.model.Product;
import com.kupercy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){

        Product product=productService.getProductById(productId);

        if(product !=null )
            return ResponseEntity.status(HttpStatus.OK).body(product);
        else
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
/*
{
"productName": "Toyota",
"category": "CAR",
"imageUrl": "https://cdn.pixabay.com/photo/2014/02/01/17/28/apple-256261__480.jpg",
"price": 200,
"stock": 5,
"description": "這是來自日本的車！"

}
 */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
