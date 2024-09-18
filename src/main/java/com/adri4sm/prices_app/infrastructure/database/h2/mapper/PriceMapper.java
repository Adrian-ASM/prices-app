package com.adri4sm.prices_app.infrastructure.database.h2.mapper;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * MMap Price entity from database to model object
     *
     * @param priceEntity Price database entity object
     * @return  Price model object
     */
    Price toDomain(PriceEntity priceEntity);

}
