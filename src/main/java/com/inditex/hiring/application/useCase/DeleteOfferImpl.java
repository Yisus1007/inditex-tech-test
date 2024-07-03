package com.inditex.hiring.application.useCase;

import com.inditex.hiring.domain.ports.DeleteOffer;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;

public class DeleteOfferImpl implements DeleteOffer {

    private final OfferRepositoryPort offerRepositoryPort;

    public DeleteOfferImpl(OfferRepositoryPort offerRepositoryPort) {
        this.offerRepositoryPort = offerRepositoryPort;
    }

    @Override
    public boolean deleteOffer(Integer id) {
        return offerRepositoryPort.deleteById(id);
    }

    @Override
    public void deleteAllOffers() {
        offerRepositoryPort.deleteAll();
    }
}
