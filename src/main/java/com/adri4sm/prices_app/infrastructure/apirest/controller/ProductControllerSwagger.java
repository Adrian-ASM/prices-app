package com.adri4sm.prices_app.infrastructure.apirest.controller;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.infrastructure.apirest.dto.ProductResponse;
import com.adri4sm.prices_app.infrastructure.apirest.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequestMapping("/products")
@Tag(name = "Products")
public interface ProductControllerSwagger {

    @Operation(summary = "Find product by product id, brand id and selected date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Price.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/{productId}", produces = "application/json")
    ProductResponse getProductPriceByProductAndBrandInSelectedDate(
            @Parameter(description = "ID of product", example = "35455", required = true) @PathVariable Long productId,
            @Parameter(description = "ID of product brand", example = "1", required = true) @RequestParam Integer brandId,
            @Parameter(description = "Selected date", example = "2020-06-14T10:00:00", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime selectedDate);
}
