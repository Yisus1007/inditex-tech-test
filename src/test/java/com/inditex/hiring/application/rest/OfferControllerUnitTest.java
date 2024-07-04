package com.inditex.hiring.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.hiring.application.service.OfferService;
import com.inditex.hiring.domain.dto.OfferDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OfferController.class)
public class OfferControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferService offerService;

    private List<OfferDto> offerDtoList;
    private OfferDto offerDto;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        offerDto = OfferDto.builder()
                .offerId(10L)
                .brandId(33)
                .startDate("2023-12-30T00.00.00Z")
                .endDate("2024-01-10T23.59.59Z")
                .priceListId(2L)
                .productPartnumber("101007991")
                .priority(2)
                .price(new BigDecimal("150.00"))
                .currencyIso("EUR")
                .build();
        offerDtoList = new ArrayList<>(
                Arrays.asList(
                        new OfferDto(1L, 12, "2024-07-01T00.00.00Z", "2024-07-14T23.59.59Z",
                                1L, "000100233", 0, new BigDecimal("35.50"), "EUR"),
                        new OfferDto(15L, 1, "2024-07-22T00.00.00Z", "2024-08-01T23.59.59Z",
                                1L, "000100233", 1, new BigDecimal("9.99"), "EUR"),
                        offerDto
                )
        );

    }

    @Test
    public void getAllOffersTest() throws Exception {
        when(offerService.getAllOffers()).thenReturn(Optional.of(offerDtoList));
        mockMvc.perform(get("/offer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$").isArray());
    }
    @Test
    public void getOfferById() throws Exception {
        when(offerService.getOfferById(anyInt())).thenReturn(Optional.of(offerDto));
        mockMvc.perform(get("/offer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").isNumber())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void createOffer() throws Exception {
        when(offerService.createOffer(any())).thenReturn(Optional.of(offerDto));
        mockMvc.perform(post("/offer")
                        .content(objectMapper.writeValueAsString(offerDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").isNumber())
                .andExpect(jsonPath("$").isNotEmpty());
    }

}
