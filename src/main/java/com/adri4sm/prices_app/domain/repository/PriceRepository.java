package com.adri4sm.prices_app.domain.repository;

import com.adri4sm.prices_app.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findPricesByProductIdAndBrandIdOnSelectedDate(Long productId,
                                                              Integer brandId,
                                                              LocalDateTime selectedDate);

}
