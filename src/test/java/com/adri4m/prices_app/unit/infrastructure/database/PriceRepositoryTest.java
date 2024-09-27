package com.adri4m.prices_app.unit;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.PriceRepository;
import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import com.adri4sm.prices_app.infrastructure.database.h2.mapper.PriceMapper;
import com.adri4sm.prices_app.infrastructure.database.h2.repository.PriceH2DBRepository;
import com.adri4sm.prices_app.infrastructure.database.h2.repository.impl.PriceRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryTest {

    private final PriceH2DBRepository priceH2DBRepository = mock(PriceH2DBRepository.class);
    private final PriceMapper priceMapper = mock(PriceMapper.class);

    private final PriceRepository priceRepository = new PriceRepositoryImpl(priceH2DBRepository, priceMapper);

    @Test
    void shouldFindPriceGivenParameters() {
        //given
        Long productId = 1L;
        int brandId = 2;
        LocalDateTime selectedDate = LocalDateTime.now();

        PriceEntity priceEntity = mock(PriceEntity.class);

        when(priceH2DBRepository.findPriorPriceByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)).thenReturn(List.of(priceEntity));
        when(priceMapper.toDomainList(List.of(priceEntity))).thenReturn(List.of(mock(Price.class)));

        //when
        List<Price> result = priceRepository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);

        //then
        verify(priceH2DBRepository, times(1)).findPriorPriceByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate);
        verify(priceMapper, times(1)).toDomainList(List.of(priceEntity));
        Assertions.assertNotNull(result);
    }
}
