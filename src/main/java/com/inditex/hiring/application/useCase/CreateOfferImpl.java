package com.inditex.hiring.application.useCase;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.CreateOffer;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;

import java.text.ParseException;

public class CreateOfferImpl implements CreateOffer {
    private final OfferRepositoryPort offerRepositoryPort;

    public CreateOfferImpl(OfferRepositoryPort offerRepositoryPort) {
        this.offerRepositoryPort = offerRepositoryPort;
    }

    @Override
    public OfferDto createOffer(OfferDto offerDto) throws ParseException {
        return offerRepositoryPort.save(offerDto);
    }
}
