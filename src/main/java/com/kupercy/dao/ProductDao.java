package com.kupercy.dao;

import com.kupercy.constant.ProductCategory;
import com.kupercy.dto.ProductRequest;
import com.kupercy.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
