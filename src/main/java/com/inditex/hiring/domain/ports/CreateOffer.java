package com.inditex.hiring.domain.ports;

import com.inditex.hiring.domain.dto.OfferDto;

import java.text.ParseException;

public interface CreateOffer {
    OfferDto createOffer(OfferDto offerDto) throws ParseException;
}
