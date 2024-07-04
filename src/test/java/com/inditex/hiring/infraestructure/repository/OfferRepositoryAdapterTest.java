package com.inditex.hiring.infraestructure.repository;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.infraestructure.entity.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferRepositoryAdapterTest {

    @InjectMocks
    private OfferRepositoryAdapter offerRepositoryAdapter;

    @Mock
    private OfferRepository offerRepository;

    private List<Offer> offerList;

    private Offer offerObject;

    @BeforeEach
    public void setUp() {
        Calendar endDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 10);
        Calendar endDate2 = Calendar.getInstance();
        endDate2.add(Calendar.DATE, 14);
        endDate.add(Calendar.DATE, 10);
        Calendar endDate3 = Calendar.getInstance();
        endDate3.add(Calendar.DATE, 90);
        offerList = new ArrayList<>(
                Arrays.asList(
                        new Offer(12, 1, new Timestamp(startDate.getTime().getTime()), new Timestamp(endDate.getTime().getTime()),3,
                                "000100233",0, new BigDecimal(35.50),"EUR",null, null,null),
                        new Offer(15, 3, new Timestamp(new Date().getTime()), new Timestamp(endDate2.getTime().getTime()),4,
                                "150250658",0, new BigDecimal(135.50),"EUR",null, null,null),
                        new Offer(19, 3, new Timestamp(new Date().getTime()), new Timestamp(endDate3.getTime().getTime()),4,
                                "150250658",0, new BigDecimal(135.50),"EUR",null, null,null)
                        )
        );
        offerObject = Offer.builder()
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
    public void should_get_all_data() {
        when(offerRepository.findAll()).thenReturn(offerList);
        var allOffers = offerRepositoryAdapter.findAll();
        assertFalse(allOffers.isEmpty(), "List should be greater than zero");
        assertEquals(12L, allOffers.get(0).getOfferId(), "Offer id should be the same");
    }

    @Test
    public void should_save_data_correctly() throws ParseException {
        when(offerRepository.save(any())).thenReturn(offerObject);
        var offerTobeSaved = OfferDto.builder()
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
        var savedOffer = offerRepositoryAdapter.save(offerTobeSaved);
        assertEquals(offerTobeSaved.getBrandId(), savedOffer.getBrandId(), "Both brand ids should be the same");
    }

    @Test
    public void should_get_data_by_id() {
        Optional<Offer> offerDtoOptional = Optional.of(offerObject);
        when(offerRepository.findById(anyInt())).thenReturn(offerDtoOptional);
        var oneOffer = offerRepositoryAdapter.findById(10);
        assertTrue(oneOffer.isPresent(), "Should be present");
        assertEquals(10L, oneOffer.map(OfferDto::getOfferId).orElse(null), "Should be the same offerId");
    }

    @Test
    public void get_non_expired_offers() {
       when(offerRepository.findAll()).thenReturn(offerList);
       var offerFiltered = offerRepositoryAdapter.findNonExpiredOffers("2024-08-14T23.59.59Z");
        assertEquals(1, offerFiltered.size(), "Should have one value");
    }

    @Test
    public void should_delete_offer_by_id() {
        when(offerRepository.existsById(anyInt())).thenReturn(true);

        var deleted = offerRepositoryAdapter.deleteById(1);
        assertTrue(deleted, "should be true");
    }

    @Test
    public void should_not_delete_offer_by_id() {
        when(offerRepository.existsById(anyInt())).thenReturn(false);
        var deleted = offerRepositoryAdapter.deleteById(45);
        assertFalse(deleted, "should be false");
    }

    @Test
    public void should_throw_an_exception_due_dates() {
        var offerTobeSaved = OfferDto.builder()
                .offerId(1L)
                .brandId(33)
                .startDate("2024-06-25")
                .endDate("2024-07-25T23.59.59Z")
                .priceListId(155L)
                .productPartnumber("110250354")
                .priority(0)
                .price(new BigDecimal("155.0"))
                .currencyIso("EUR")
                .build();
        var exception = assertThrows(Exception.class, () -> offerRepositoryAdapter.save(offerTobeSaved));
        assertTrue(exception.getMessage().contains("parsed"));
        offerTobeSaved.setStartDate("2024-07-25T23.59.59Z");
        offerTobeSaved.setEndDate("2024-07-25");
        exception = assertThrows(Exception.class, () -> offerRepositoryAdapter.save(offerTobeSaved));
        assertTrue(exception.getMessage().contains("parsed"), "Should throw a parse related message");
    }

    @Test
    public void should_throw_an_exception_due_index() {
        var offerTobeSaved = OfferDto.builder()
                .offerId(1L)
                .brandId(33)
                .startDate("2024-07-25T23.59.59Z")
                .endDate("2024-07-25T23.59.59Z")
                .priceListId(155L)
                .productPartnumber("1102")
                .priority(0)
                .price(new BigDecimal("155.0"))
                .currencyIso("EUR")
                .build();
        var exception = assertThrows(Exception.class, () -> offerRepositoryAdapter.save(offerTobeSaved));
        System.out.println("exception: " + exception);
        assertTrue(exception.getMessage().contains("begin"), "Should throw an index related message");
    }
}
