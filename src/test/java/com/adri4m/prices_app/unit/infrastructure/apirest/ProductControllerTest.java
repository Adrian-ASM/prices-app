package com.adri4m.prices_app.unit.infrastructure.apirest;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.service.ProductService;
import com.adri4sm.prices_app.infrastructure.apirest.controller.ProductController;
import com.adri4sm.prices_app.infrastructure.apirest.controller.ProductControllerSwagger;
import com.adri4sm.prices_app.infrastructure.apirest.dto.ProductResponse;
import com.adri4sm.prices_app.infrastructure.apirest.mapper.ProductResponseMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private final ProductService productService = mock(ProductService.class);
    private final ProductResponseMapper mapper = mock(ProductResponseMapper.class);

    private final ProductControllerSwagger controller = new ProductController(productService, mapper);

    @Test
    void shouldReturnResultByProductIdBrandIdOnSelectedDate() {
        //given
        Long productId = 1L;
        int brandId = 2;
        LocalDateTime selectedDate = LocalDateTime.now();
        Price product = mock(Price.class);

        when(productService.calculateProductPrice(productId, brandId, selectedDate)).thenReturn(product);
        when(mapper.toResponse(product)).thenReturn(mock(ProductResponse.class));

        //when
        ProductResponse result = controller.getProductPriceByProductAndBrandInSelectedDate(productId, brandId, selectedDate);

        //then
        Assertions.assertTrue(Objects.nonNull(result));
    }

}
