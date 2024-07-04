package com.inditex.hiring.infraestructure.repository;

import com.inditex.hiring.application.rest.exception.NoSuchResourceFoundException;
import com.inditex.hiring.application.rest.exception.ParseDatesException;
import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;
import com.inditex.hiring.infraestructure.entity.Offer;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OfferRepositoryAdapter implements OfferRepositoryPort {

    final private OfferRepository offerJpaRepository;

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH.mm.ssXXX";

    public OfferRepositoryAdapter(OfferRepository offerRepository) {
        this.offerJpaRepository = offerRepository;
    }

    @Override
    public OfferDto save(OfferDto offerDto) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime startDateToTmstmp = LocalDateTime.parse(offerDto.getStartDate(), dateFormat);
            LocalDateTime endDateToTmstmp = LocalDateTime.parse(offerDto.getEndDate(), dateFormat);
            Offer offerToSave = Offer.builder()
                    .offerId(offerDto.getOfferId().intValue())
                    .brandId(offerDto.getBrandId())
                    .startDate(Timestamp.valueOf(startDateToTmstmp))
                    .endDate(Timestamp.valueOf(endDateToTmstmp))
                    .priceListId(Math.toIntExact(offerDto.getPriceListId()))
                    .productPartnumber(offerDto.getProductPartnumber())
                    .priority(offerDto.getPriority())
                    .price(offerDto.getPrice())
                    .currencyIso(offerDto.getCurrencyIso())
                    .size(offerDto.getProductPartnumber().substring(0, 2))
                    .model(offerDto.getProductPartnumber().substring(2, 6))
                    .quality(offerDto.getProductPartnumber().substring(6, 9))
                    .build();
            return offerJpaRepository.save(offerToSave).toDtoOffer();
        }
        catch (Exception e) {
            RuntimeException exception;
            exception = e.getMessage().contains("parsed")  ? new ParseDatesException("Error parsing dates, error Message: " + e.getMessage())
            : new NoSuchResourceFoundException("Extra error, cause: "+ (e.getMessage().isEmpty() ? " " : e.getMessage()));
            throw exception;
        }

    }

    @Override
    public Optional<OfferDto> findById(Integer id) {
       return offerJpaRepository.findById(id).map(Offer::toDtoOffer);
    }

    @Override
    public List<OfferDto> findAll() {
        return offerJpaRepository.findAll().stream()
                .map(Offer::toDtoOffer)
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findNonExpiredOffers(String date) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);
            Timestamp dateTmstp = Timestamp.valueOf(LocalDateTime.parse(date, dateFormat));
            return offerJpaRepository.findAll().stream()
                    .filter(offer -> offer.getEndDate().after(dateTmstp))
                    .map(Offer::toDtoOffer)
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            throw new ParseDatesException("Error parsing dates, error Message: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        if (offerJpaRepository.existsById(id)) {
            offerJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        offerJpaRepository.deleteAll();
    }
}
