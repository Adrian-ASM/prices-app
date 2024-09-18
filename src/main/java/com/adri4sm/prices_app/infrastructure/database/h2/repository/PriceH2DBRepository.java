package com.adri4sm.prices_app.infrastructure.database.h2.repository;

import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceH2DBRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(value = "SELECT p FROM PriceEntity p WHERE p.productId = ?1 AND p.brandId = ?2 AND p.startDate <= ?3 AND p.endDate > ?3 ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> findPriorPriceByProductIdAndBrandIdOnSelectedDate(Long productId, Integer brandId, LocalDateTime selectedDate);

}
