package com.adri4sm.prices_app.infrastructure.database.h2.repository.impl;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.ProductRepository;
import com.adri4sm.prices_app.infrastructure.database.h2.mapper.PriceMapper;
import com.adri4sm.prices_app.infrastructure.database.h2.repository.PriceH2DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductRepositoryImpl implements ProductRepository {

    private final PriceH2DBRepository priceH2DBRepository;

    private final PriceMapper priceMapper;

    @Autowired
    public ProductRepositoryImpl(PriceH2DBRepository priceH2DBRepository, PriceMapper priceMapper) {
        this.priceH2DBRepository = priceH2DBRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public Optional<Price> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate) {
        return priceH2DBRepository.findPriorPriceByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)
                .map(priceMapper::toDomain);
    }
}
