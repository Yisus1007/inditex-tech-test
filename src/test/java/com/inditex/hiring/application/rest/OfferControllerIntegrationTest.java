package com.inditex.hiring.application.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.hiring.application.service.OfferService;
import com.inditex.hiring.domain.dto.OfferDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OfferControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OfferService offerService;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private  HttpEntity<Object> entity;

    private OfferDto offerTobeCreated;
    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        entity = new HttpEntity<>(null, headers);
        offerTobeCreated = OfferDto.builder()
                .offerId(1L)
                .brandId(33)
                .startDate("2024-06-25T00.00.00Z")
                .endDate("2024-07-25T23.59.59Z")
                .priceListId(155L)
                .productPartnumber("110250354")
                .priority(0)
                .price(new BigDecimal("155.0"))
                .currencyIso("EUR")
                .build();
    }

    @Test
    void createOffer() throws JsonProcessingException {
        entity = new HttpEntity<>(objectMapper.writeValueAsString(offerTobeCreated), headers);
        ResponseEntity<OfferDto> response = restTemplate.exchange("http://localhost:"+port+"/offer", HttpMethod.POST,
                entity,OfferDto.class);
        assertEquals(201, response.getStatusCodeValue(), "Status should be 201");
        assertNotNull(response.getBody());
        assertEquals(33, response.getBody().getBrandId() , "Brand Should be 33");
    }

    @Test
    void createOfferReturnsErrorDuePartNumber() throws JsonProcessingException {
        offerTobeCreated.setProductPartnumber("123");
        entity = new HttpEntity<>(objectMapper.writeValueAsString(offerTobeCreated), headers);
        ResponseEntity<OfferDto> response = restTemplate.exchange("http://localhost:"+port+"/offer", HttpMethod.POST,
                entity,OfferDto.class);
        assertEquals(404, response.getStatusCodeValue(), "Status should be 404");
        assertNotNull(response.getBody());
        assertNull(response.getBody().getBrandId() , "Brand Should be null");
        offerTobeCreated.setProductPartnumber(null);
        entity = new HttpEntity<>(objectMapper.writeValueAsString(offerTobeCreated), headers);
        response = restTemplate.exchange("http://localhost:"+port+"/offer", HttpMethod.POST,
                entity,OfferDto.class);
        assertEquals(500, response.getStatusCodeValue(), "Status should be 500");
    }
    @Test
    void createOfferReturnsErrorDueDates() throws JsonProcessingException {
        offerTobeCreated.setStartDate("2024-08-17");
        entity = new HttpEntity<>(objectMapper.writeValueAsString(offerTobeCreated), headers);
        ResponseEntity<OfferDto> response = restTemplate.exchange("http://localhost:"+port+"/offer", HttpMethod.POST,
                entity,OfferDto.class);
        assertEquals(400, response.getStatusCodeValue(), "Status should be 400");
        assertNotNull(response.getBody());
    }

    @Test
    void getOfferById() {
        ResponseEntity<OfferDto> response = restTemplate.exchange("http://localhost:"+port+"/offer/1", HttpMethod.GET,
                entity,new ParameterizedTypeReference<OfferDto>(){});
        var offerDto = response.getBody();
        assertEquals(200, response.getStatusCodeValue(), "Status should be 200");
        assertNotNull(offerDto);
        assertEquals(offerDto.getOfferId(), 1, "Should get offer id 1");
    }

    @Test
    void getAllOffers() throws Exception {
        ResponseEntity<List<OfferDto>> response = restTemplate.exchange("http://localhost:"+port+"/offer", HttpMethod.GET,
                entity,new ParameterizedTypeReference<List<OfferDto>>(){});
        List<OfferDto> allOffers = response.getBody();
        assertEquals(200, response.getStatusCodeValue(), "Status should be 200");
        assertNotNull(allOffers, "Should be not null");
        assertFalse(allOffers.isEmpty(), "should have data");
        assertEquals(9, allOffers.size(), "There are 10 records");
    }

    @Test
    void getNonExpireOffers() {
        ResponseEntity<List<OfferDto>> response = restTemplate.exchange("http://localhost:"+port+"/offer?date=2024-08-01T00.00.00Z", HttpMethod.GET,
                entity,new ParameterizedTypeReference<List<OfferDto>>(){});
        List<OfferDto> filteredOffers = response.getBody();
        assertEquals(200, response.getStatusCodeValue(), "Status should be 200");
        assertNotNull(filteredOffers, "Should be not null");
        assertFalse(filteredOffers.isEmpty(), "should have data");
        assertEquals(5, filteredOffers.size(), "After filter applied there are 5 records");
    }

    @Test
    void getNonExpireOffersWithError() {
        ResponseEntity<OfferDto> response = restTemplate.exchange("http://localhost:"+port+"/offer?date=2024-08-01", HttpMethod.GET,
                entity,new ParameterizedTypeReference<OfferDto>(){});
        assertEquals(400, response.getStatusCodeValue(), "Status should be 400");
        assertNotNull(response, "Should be not null");
    }

    @Test
    void deleteOfferById() {
        ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:"+port+"/offer/1", HttpMethod.DELETE,
                entity,new ParameterizedTypeReference<Boolean>(){});
        assertEquals(200, response.getStatusCodeValue(), "Status should be 200");
        assertNotNull(response.getBody());
    }

}