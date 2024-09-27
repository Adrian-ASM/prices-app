package com.adri4sm.prices_app.infrastructure.database.h2.repository;

import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceH2DBRepository extends JpaRepository<PriceEntity, Integer> {

    /**
     * Find price and product info given the product identifier and brand identifier on selected date.
     *
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @param selectedDate Selected date.
     * @return Optional product price entity object.
     */
    @Query(value = "SELECT p FROM PriceEntity p WHERE p.productId = ?1 AND p.brandId = ?2 AND p.startDate <= ?3 AND p.endDate > ?3")
    List<PriceEntity> findPriorPriceByProductIdAndBrandIdOnSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate);

}
