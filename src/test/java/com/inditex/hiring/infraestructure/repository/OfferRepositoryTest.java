package com.inditex.hiring.infraestructure.repository;

import com.inditex.hiring.infraestructure.entity.Offer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferRepositoryTest {

    @Autowired
    private OfferRepository offerRepository;

    private Offer offerEntity;

    @BeforeEach
    public void setUp() {
        Calendar endDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        offerEntity = Offer.builder()
                .offerId(10)
                .brandId(33)
                .startDate(new Timestamp(startDate.getTime().getTime()))
                .endDate(new Timestamp(endDate.getTime().getTime()))
                .productPartnumber("150250658")
                .priceListId(0)
                .priority(1)
                .price(new BigDecimal("175.30"))
                .currencyIso("EUR")
                .build();
    }

   @Test
    public void checkBrandIdInsertedInInitialData (){
        Optional<Offer> offer = offerRepository.findById(1);
        assertTrue(offer.isPresent(), "should be present");
        Integer brandId = offer.orElse(null).getBrandId();
        assertEquals(12, brandId, "BrandId Should be 1");
    }
    @Test
    public void checkAlltheDataInserted (){
        List<Offer> offer = offerRepository.findAll();
        assertFalse(offer.isEmpty(), "should have data");
    }

    @Test
    public void checkBrandUnexistentInitialData() {
        Optional<Offer> offer = offerRepository.findById(199);
        assertFalse(offer.isPresent(), "should not be present");
    }

    @Test
    public void saveNewDataAndValidateIt() {
       var offerSaved = offerRepository.save(offerEntity);
       var offerValidation = offerRepository.findById(offerSaved.getOfferId());
       assertEquals(10, offerValidation.map(Offer::getOfferId).orElse(null), "Offer id should be the inserted");
    }

    @Test
    public void deleteOfferById() {
        offerRepository.deleteById(1);
        var offerValidation = offerRepository.findById(1);
       assertNull(offerValidation.map(Offer::getOfferId).orElse(null));
    }
}
