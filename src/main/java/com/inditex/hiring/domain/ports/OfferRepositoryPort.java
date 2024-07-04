package com.inditex.hiring.domain.ports;

import com.inditex.hiring.domain.dto.OfferDto;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface OfferRepositoryPort {

    OfferDto save(OfferDto offerDto) throws ParseException;
    Optional<OfferDto> findById(Integer id);
    List<OfferDto> findAll();
    List<OfferDto> findNonExpiredOffers(String date);
    boolean deleteById(Integer id);
    void deleteAll();
}
