package com.adri4sm.prices_app.infrastructure.database.h2.mapper;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Map Price entity from database to model object
     *
     * @param priceEntity Price database entity object
     * @return  Price model object
     */
    Price toDomain(PriceEntity priceEntity);

    /**
     * Map Price entity list from database to model object list
     *
     * @param priceEntityList Price database entity list
     * @return  Price model object list
     */
    List<Price> toDomainList(List<PriceEntity> priceEntityList);

}
