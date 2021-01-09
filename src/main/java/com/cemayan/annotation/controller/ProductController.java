package com.cemayan.annotation.controller;

import com.cemayan.annotation.aop.LogExecutionTime;
import com.cemayan.annotation.aop.ProductNullGuard;
import com.cemayan.annotation.dto.ProductDTO;
import com.cemayan.annotation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @LogExecutionTime
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String productId) {
        return productService.getProductId(productId)
                .map(productDTO -> ResponseEntity.ok().body(productDTO))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist"));
    }

    @LogExecutionTime
    @ProductNullGuard(fieldNames = {"productName"})
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        try  {
            return ResponseEntity.ok().body(productService.save(productDTO));
        }
        catch (Exception e) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
