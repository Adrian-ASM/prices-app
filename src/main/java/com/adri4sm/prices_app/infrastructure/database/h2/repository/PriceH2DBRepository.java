package com.adri4sm.prices_app.infrastructure.database.h2.repository;

import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceH2DBRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(value = "SELECT p FROM PriceEntity p WHERE p.productId = ?1 AND p.brandId = ?2 AND p.startDate <= ?3 AND p.startDate > ?3 AND p.priority = " +
            "(SELECT MAX(p2.priority) FROM PriceEntity p2 WHERE p2.productId = p.productId AND p2.brandId = p.brandId AND p2.startDate <= ?3 AND p2.endDate > ?3)")
    Optional<PriceEntity> findPriorPriceByProductIdAndBrandIdOnSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate);

}
