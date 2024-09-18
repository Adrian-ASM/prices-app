package com.adri4sm.prices_app.domain.repository;

import com.adri4sm.prices_app.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId,
                                                                  Integer brandId,
                                                                  LocalDateTime selectedDate);

}
