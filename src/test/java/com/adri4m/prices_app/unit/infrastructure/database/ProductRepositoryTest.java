package com.adri4m.prices_app.unit;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.ProductRepository;
import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import com.adri4sm.prices_app.infrastructure.database.h2.mapper.PriceMapper;
import com.adri4sm.prices_app.infrastructure.database.h2.repository.PriceH2DBRepository;
import com.adri4sm.prices_app.infrastructure.database.h2.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    private final PriceH2DBRepository priceH2DBRepository = mock(PriceH2DBRepository.class);
    private final PriceMapper priceMapper = mock(PriceMapper.class);

    private final ProductRepository productRepository = new ProductRepositoryImpl(priceH2DBRepository, priceMapper);

    @Test
    void shouldFindPriceGivenParameters() {
        //given
        Long productId = 1L;
        int brandId = 2;
        LocalDateTime selectedDate = LocalDateTime.now();

        PriceEntity priceEntity = mock(PriceEntity.class);

        when(priceH2DBRepository.findPriorPriceByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(Optional.ofNullable(priceEntity));
        when(priceMapper.toDomain(priceEntity)).thenReturn(mock(Price.class));

        //when
        Optional<Price> result = productRepository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);

        //then
        verify(priceH2DBRepository, times(1)).findPriorPriceByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);
        verify(priceMapper, times(1)).toDomain(priceEntity);
        Assertions.assertNotNull(result);
    }
}
