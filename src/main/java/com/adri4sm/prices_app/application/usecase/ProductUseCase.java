package com.adri4sm.prices_app.application.usecase;

import com.adri4sm.prices_app.domain.exception.ResourceNotFoundException;
import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.domain.repository.PriceRepository;
import com.adri4sm.prices_app.domain.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class ProductUseCase implements ProductService {

    private final PriceRepository repository;

    public ProductUseCase(PriceRepository priceRepository) {
        this.repository = priceRepository;
    }

    @Override
    public Price calculateProductPrice(Long productId, Integer brandId, LocalDateTime selectedDate) {
        return repository.findPricesByProductIdAndBrandIdOnSelectedDate(productId, brandId, selectedDate)
                .stream().max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new ResourceNotFoundException("Price not found for product with id: " + productId
                        + " and brand with id: " + brandId + " on selected date: " + selectedDate));
    }
}
