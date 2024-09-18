package com.adri4sm.prices_app.application.usecase;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.PriceRepository;
import com.adri4sm.prices_app.domain.service.PriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class PriceUseCase implements PriceService {

    private final PriceRepository repository;

    public PriceUseCase(PriceRepository priceRepository) {
        this.repository = priceRepository;
    }

    @Override
    public Price calculatePrice(Long productId, Integer brandId, LocalDateTime selectedDate) {
        return repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)
                .orElseThrow(() -> new NoSuchElementException("Price not found for product with id: " + productId
                        + " and brand with id: " + brandId + " on selected date: " + selectedDate));
    }
}
