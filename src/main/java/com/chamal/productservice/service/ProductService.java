package com.chamal.productservice.service;

import com.chamal.productservice.dto.ProductRequestDto;
import com.chamal.productservice.dto.ProductResponseDto;
import com.chamal.productservice.model.Product;
import com.chamal.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public void createProduct(ProductRequestDto productRequestDto){
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice()).build();

        productRepository.save(product);

        log.info("Poduct {} is saved",product.getId());
    }

    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponseDto mapToProductResponse(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName()).build();
    }
}
