package com.inditex.hiring.infraestructure.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferRepositoryTest {

    @Autowired
    private OfferRepository offerRepository;

    @Test
    public void checkBrandIdInsertedInInitialData (){
        Optional<Offer> offer = offerRepository.findByOfferId(1L);
        assertTrue("should be present", offer.isPresent());
        Integer brandId = offer.orElse(null).getBrandId();
        assertTrue("BrandId Should be 1", brandId.equals(1));
    }

    @Test
    public void checkBrandUnexistentInitialData() {
        Optional<Offer> offer = offerRepository.findByOfferId(199L);
        assertFalse("should not be present",offer.isPresent());
    }
}
