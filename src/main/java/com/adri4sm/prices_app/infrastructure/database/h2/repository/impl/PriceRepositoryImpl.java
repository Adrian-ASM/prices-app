package com.adri4sm.prices_app.infrastructure.database.h2.repository.impl;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.PriceRepository;
import com.adri4sm.prices_app.infrastructure.database.h2.mapper.PriceMapper;
import com.adri4sm.prices_app.infrastructure.database.h2.repository.PriceH2DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceH2DBRepository priceH2DBRepository;

    private final PriceMapper priceMapper;

    @Autowired
    public PriceRepositoryImpl(PriceH2DBRepository priceH2DBRepository, PriceMapper priceMapper) {
        this.priceH2DBRepository = priceH2DBRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Price> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate) {
        return priceMapper.toDomainList(
                priceH2DBRepository.findPriorPriceByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)
        );
    }
}
