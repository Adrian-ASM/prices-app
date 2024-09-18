package com.adri4sm.prices_app.infrastructure.apirest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("rate_id")
    private Integer rateId;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty
    private Double price;

}
