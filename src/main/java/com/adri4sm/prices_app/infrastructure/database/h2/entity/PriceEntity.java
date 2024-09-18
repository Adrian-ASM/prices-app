package com.adri4sm.prices_app.infrastructure.database.h2.entity;

import com.adri4sm.prices_app.domain.model.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer rateId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "PRICE", nullable = false)
    private Double productPrice;

    @Column(name = "CURR", nullable = false, length = 3)
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceEntity priceEntity)) return false;
        return Objects.equals(id, priceEntity.id) && Objects.equals(brandId, priceEntity.brandId) && Objects.equals(startDate, priceEntity.startDate) && Objects.equals(endDate, priceEntity.endDate) && Objects.equals(rateId, priceEntity.rateId) && Objects.equals(productId, priceEntity.productId) && Objects.equals(priority, priceEntity.priority) && Objects.equals(productPrice, priceEntity.productPrice) && Objects.equals(currency, priceEntity.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, rateId, productId, priority, productPrice, currency);
    }
}
