package com.chamal.productservice.controller;

import com.chamal.productservice.dto.ProductRequestDto;
import com.chamal.productservice.dto.ProductResponseDto;
import com.chamal.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDto productRequestDto){

        productService.createProduct(productRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProducts(){

      return   productService.getAllProducts();
    }
}
