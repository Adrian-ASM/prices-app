package com.adri4m.prices_app.unit.infrastructure.database;

import com.adri4sm.prices_app.domain.model.Price;
import com.adri4sm.prices_app.infrastructure.database.h2.entity.PriceEntity;
import com.adri4sm.prices_app.infrastructure.database.h2.mapper.PriceMapper;
import com.adri4sm.prices_app.infrastructure.database.h2.mapper.PriceMapperImpl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PriceMapperTest {

    private final PriceMapper priceMapper = new PriceMapperImpl();
    private final EasyRandom entityGenerator = new EasyRandom();
    
    @Test
    void shouldMapPriceEntityToPriceModelObject() {
        //given
        final PriceEntity priceEntity = entityGenerator.nextObject(PriceEntity.class);
        
        //when
        Price price = priceMapper.toDomain(priceEntity);
        
        //then
        assertEquals(priceEntity.getProductId(), price.getProductId());
        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getRateId(), price.getRateId());
        assertEquals(priceEntity.getStartDate(), price.getStartDate());
        assertEquals(priceEntity.getEndDate(), price.getEndDate());
        assertEquals(priceEntity.getProductPrice(), price.getProductPrice());
        
    }
    
}
