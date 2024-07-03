package com.inditex.hiring.application.useCase;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;
import com.inditex.hiring.domain.ports.RetrieveOffer;

import java.util.List;
import java.util.Optional;

public class RetrieveOfferImpl implements RetrieveOffer {

    private final OfferRepositoryPort offerRepositoryPort;

    public RetrieveOfferImpl(OfferRepositoryPort offerRepositoryPort) {
        this.offerRepositoryPort = offerRepositoryPort;
    }

    @Override
    public Optional<OfferDto> getOfferById(Integer id) {
        return offerRepositoryPort.findById(id);
    }

    @Override
    public List<OfferDto> getAllOffers() {
        return offerRepositoryPort.findAll();
    }

    @Override
    public List<OfferDto> getNonExpireOffers() {
        return null;
    }
}
