package com.inditex.hiring.domain.ports;

import com.inditex.hiring.domain.dto.OfferDto;

import java.util.List;
import java.util.Optional;

public interface RetrieveOffer {
    Optional<OfferDto> getOfferById(Integer id);

    Optional<List<OfferDto>> getAllOffers();

    Optional<List<OfferDto>> getNonExpireOffers(String date);


}
