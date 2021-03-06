package com.kupercy.controller;

import com.kupercy.constant.ProductCategory;
import com.kupercy.dto.ProductQueryParams;
import com.kupercy.dto.ProductRequest;
import com.kupercy.model.Product;
import com.kupercy.service.ProductService;
import com.kupercy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    //public ResponseEntity<List<Product>> getProducts(
    public ResponseEntity<Page<Product>> getProducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset

    ){
        ProductQueryParams productQueryParams =new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //取得 product list
        List<Product> productList = productService.getProducts(productQueryParams);

        //取得 product 總數
        Integer total =productService.countProduct(productQueryParams);

        //分頁
        Page<Product> page =new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

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

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product>updateProduct(@PathVariable Integer productId,
                                                @RequestBody @Valid ProductRequest productRequest){
        //check product  exist or not
        Product product =productService.getProductById(productId);

        if(product == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        //update product
        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
