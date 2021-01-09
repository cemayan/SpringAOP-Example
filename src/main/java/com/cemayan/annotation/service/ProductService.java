package com.cemayan.annotation.service;

import com.cemayan.annotation.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface ProductService {
    ProductDTO save(ProductDTO productDTO);
    Optional<ProductDTO> getProductId(String productId);
}
