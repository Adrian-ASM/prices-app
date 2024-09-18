package com.adri4m.prices_app.unit.application;

import com.adri4sm.prices_app.application.usecase.PriceUseCase;
import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.PriceRepository;
import com.adri4sm.prices_app.domain.service.PriceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceUseCaseTest {

    private final PriceRepository priceRepository = mock(PriceRepository.class);

    private final PriceService priceService = new PriceUseCase(priceRepository);

    private Long productId;
    private Integer brandId;
    private Price priceWithHighPriority;

    @BeforeEach
    public void setUp() {
        productId = 1L;
        brandId = 2;
        priceWithHighPriority = mock(Price.class);
    }

    @Test
    void shouldThrowExceptionWhenNoPriceIsFound() {
        LocalDateTime now = LocalDateTime.now();
        //given
        when(priceRepository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, now)).thenReturn(Optional.empty());

        //when-then
        assertThrows(NoSuchElementException.class, () -> priceService.calculatePrice(productId, brandId, now));
    }

    @Test
    void shouldReturnPriceWithMaxPriorityOnSelectedDate() {
        //given
        LocalDateTime now = LocalDateTime.now();
        when(priceRepository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, now)).thenReturn(Optional.of(priceWithHighPriority));

        //when
        Price result = priceService.calculatePrice(productId, brandId, now);

        //then
        Assertions.assertEquals(priceWithHighPriority, result);
    }

}
