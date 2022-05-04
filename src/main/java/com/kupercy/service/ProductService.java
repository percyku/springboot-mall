package com.kupercy.service;

import com.kupercy.dto.ProductRequest;
import com.kupercy.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
