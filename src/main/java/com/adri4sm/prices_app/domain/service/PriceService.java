package com.adri4sm.prices_app.domain.service;

import com.adri4sm.prices_app.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceService {

    /**
     * Calculate the price of a product given the product id and brand id on a specific date
     *
     * @param productId Product identifier
     * @param brandId Brand identifier
     * @param selectedDate Selected date
     * @return Price of the product
     */
    Price calculatePrice(Long productId, Integer brandId, LocalDateTime selectedDate);

}
