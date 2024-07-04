package com.inditex.hiring.application.useCase;

import com.inditex.hiring.domain.ports.DeleteOffer;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;

import java.util.Optional;

public class DeleteOfferImpl implements DeleteOffer {

    private final OfferRepositoryPort offerRepositoryPort;

    public DeleteOfferImpl(OfferRepositoryPort offerRepositoryPort) {
        this.offerRepositoryPort = offerRepositoryPort;
    }

    @Override
    public Optional<Boolean> deleteOffer(Integer id) {
        return Optional.of(offerRepositoryPort.deleteById(id));
    }

    @Override
    public void deleteAllOffers() {
        offerRepositoryPort.deleteAll();
    }
}
