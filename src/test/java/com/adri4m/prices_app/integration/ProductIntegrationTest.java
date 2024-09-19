package com.adri4m.prices_app.integration;

import com.adri4sm.prices_app.ProductsAppApplication;
import com.adri4sm.prices_app.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductsAppApplication.class)
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    private static final String BASE_PRODUCTS_URL = "/products";
    private static final String PRODUCT_ID_PATH_PARAM = "/{productId}";
    private static final String BRAND_ID_QUERY_PARAM = "brandId";
    private static final String SELECTED_DATE_QUERY_PARAM = "selectedDate";

    @Autowired
    private MockMvc mockMvc;

    private static Stream<Arguments> dateAndExpectedPriceResult() {
        return Stream.of(
                arguments("2020-06-14T10:00:00", 35.5),
                arguments("2020-06-14T16:00:00", 25.45),
                arguments("2020-06-14T21:00:00", 35.5),
                arguments("2020-06-15T10:00:00", 30.5),
                arguments("2020-06-16T21:00:00", 38.95)
        );
    }

    @ParameterizedTest
    @MethodSource("dateAndExpectedPriceResult")
    void testByProductIdBrandIdOnDates(String date, Double expectedPrice) throws Exception {
        Long productId = 35455L;
        int brandId = 1;

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM, productId)
                        .queryParam(BRAND_ID_QUERY_PARAM, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_QUERY_PARAM, date))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }


    @Test
    void testNotValidProductId() throws Exception {
        Long productId = Long.MAX_VALUE;
        int brandId = 1;
        String date = "2020-06-14T00:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM, productId)
                        .queryParam(BRAND_ID_QUERY_PARAM, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_QUERY_PARAM, date))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()));
    }

    @Test
    void testNotValidBrandId() throws Exception {
        Long productId = 35455L;
        int brandId = Integer.MAX_VALUE;
        String date = "2020-06-14T00:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM, productId)
                        .queryParam(BRAND_ID_QUERY_PARAM, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_QUERY_PARAM, date))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()));
    }

    @Test
    void testNoPriceFoundOnSelectedDate() throws Exception {
        Long productId = 35455L;
        int brandId = 1;
        String date = "2024-06-14T00:00:00";

        this.mockMvc.perform(get(BASE_PRODUCTS_URL + PRODUCT_ID_PATH_PARAM, productId)
                        .queryParam(BRAND_ID_QUERY_PARAM, Integer.toString(brandId))
                        .queryParam(SELECTED_DATE_QUERY_PARAM, date))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()));
    }
}
