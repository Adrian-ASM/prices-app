package com.adri4sm.prices_app.infrastructure.apirest.controller;

import com.adri4sm.prices_app.domain.service.ProductService;
import com.adri4sm.prices_app.infrastructure.apirest.dto.ProductResponse;
import com.adri4sm.prices_app.infrastructure.apirest.mapper.ProductResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class ProductController implements ProductControllerSwagger{

    private final ProductService productService;
    private final ProductResponseMapper productResponseMapper;

    @Autowired
    public ProductController(ProductService productService, ProductResponseMapper productResponseMapper) {
        this.productService = productService;
        this.productResponseMapper = productResponseMapper;
    }

    public ProductResponse getProductPriceByProductAndBrandInSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate) {

        log.info("Received request for getProductPriceByProductAndBrandInSelectedDate with params: productId: {}, brandId: {} on date: {}", productId, brandId, selectedDate);

        return productResponseMapper.toResponse(productService.calculateProductPrice(productId, brandId, selectedDate));
    }
}
