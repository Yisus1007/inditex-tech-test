package com.inditex.hiring.application.useCase;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.CreateOffer;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;

import java.text.ParseException;
import java.util.Optional;

public class CreateOfferImpl implements CreateOffer {
    private final OfferRepositoryPort offerRepositoryPort;

    public CreateOfferImpl(OfferRepositoryPort offerRepositoryPort) {
        this.offerRepositoryPort = offerRepositoryPort;
    }

    @Override
    public Optional<OfferDto> createOffer(OfferDto offerDto) throws ParseException {
        return Optional.of(offerRepositoryPort.save(offerDto));
    }
}
