package com.adri4m.prices_app.unit.application;

import com.adri4sm.prices_app.application.usecase.ProductUseCase;
import com.adri4sm.prices_app.domain.exception.ResourceNotFoundException;
import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.PriceRepository;
import com.adri4sm.prices_app.domain.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceUseCaseTest {

    private final PriceRepository priceRepository = mock(PriceRepository.class);

    private final ProductService productService = new ProductUseCase(priceRepository);

    private Long productId;
    private Integer brandId;
    private Price priceWithHighPriority;
    private Price priceWithLowPriority;

    @BeforeEach
    public void setUp() {
        productId = 1L;
        brandId = 2;

        priceWithHighPriority = mock(Price.class);
        priceWithLowPriority = mock(Price.class);
    }

    @Test
    void shouldThrowExceptionWhenNoPriceIsFound() {
        LocalDateTime now = LocalDateTime.now();
        //given
        when(priceRepository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, now)).thenReturn(List.of());

        //when-then
        assertThrows(ResourceNotFoundException.class, () -> productService.calculateProductPrice(productId, brandId, now));
    }

    @Test
    void shouldReturnPriceWithMaxPriorityOnSelectedDate() {
        //given
        LocalDateTime now = LocalDateTime.now();
        when(priceRepository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, now)).thenReturn(List.of(priceWithHighPriority, priceWithLowPriority));
        when(priceWithHighPriority.getPriority()).thenReturn(1);
        when(priceWithLowPriority.getPriority()).thenReturn(0);

        //when
        Price result = productService.calculateProductPrice(productId, brandId, now);

        //then
        Assertions.assertEquals(priceWithHighPriority, result);
    }

}
