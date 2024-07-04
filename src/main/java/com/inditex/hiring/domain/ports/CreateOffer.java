package com.inditex.hiring.domain.ports;

import com.inditex.hiring.domain.dto.OfferDto;

import java.text.ParseException;
import java.util.Optional;

public interface CreateOffer {
    Optional<OfferDto> createOffer(OfferDto offerDto) throws ParseException;
}
