package com.cemayan.annotation.service.impl;

import com.cemayan.annotation.dto.ProductDTO;
import com.cemayan.annotation.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        return new ProductDTO();
    }

    @Override
    public Optional<ProductDTO> getProductId(String productId) {
        return Optional.of(new ProductDTO());
    }
}
