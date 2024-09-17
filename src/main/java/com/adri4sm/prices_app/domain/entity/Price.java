package com.adri4sm.prices_app.domain.entity;

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
public class Price {

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
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price price)) return false;
        return Objects.equals(id, price.id) && Objects.equals(brandId, price.brandId) && Objects.equals(startDate, price.startDate) && Objects.equals(endDate, price.endDate) && Objects.equals(rateId, price.rateId) && Objects.equals(productId, price.productId) && Objects.equals(priority, price.priority) && Objects.equals(productPrice, price.productPrice) && Objects.equals(currency, price.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, rateId, productId, priority, productPrice, currency);
    }
}
