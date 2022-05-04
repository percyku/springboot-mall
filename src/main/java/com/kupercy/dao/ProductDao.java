package com.kupercy.dao;

import com.kupercy.dto.ProductRequest;
import com.kupercy.model.Product;

public interface ProductDao {


    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

}
