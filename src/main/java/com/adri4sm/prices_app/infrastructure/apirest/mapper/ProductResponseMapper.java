package com.adri4sm.prices_app.infrastructure.apirest.mapper;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.infrastructure.apirest.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    /**
     * Map Price model object to API response object.
     *
     * @param price Object of Price model class.
     * @return Object of ProductResponse class.
     */
    ProductResponse toResponse(Price price);

}
