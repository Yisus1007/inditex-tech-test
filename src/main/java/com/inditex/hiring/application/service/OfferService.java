package com.inditex.hiring.application.service;

import com.inditex.hiring.application.rest.exception.NoSuchResourceFoundException;
import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.CreateOffer;
import com.inditex.hiring.domain.ports.DeleteOffer;
import com.inditex.hiring.domain.ports.RetrieveOffer;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class OfferService implements CreateOffer, RetrieveOffer, DeleteOffer {

    private final CreateOffer createOffer;
    private final RetrieveOffer retrieveOffer;

    private final DeleteOffer deleteOffer;

    public OfferService(CreateOffer createOffer, RetrieveOffer retrieveOffer, DeleteOffer deleteOffer) {
        this.createOffer = createOffer;
        this.retrieveOffer = retrieveOffer;
        this.deleteOffer = deleteOffer;
    }

    @Override
    public Optional<OfferDto> createOffer(OfferDto offerDto) throws ParseException {
        return Optional.ofNullable(createOffer.createOffer(offerDto)
                .orElseThrow(() -> new NoSuchResourceFoundException("error")));
    }

    @Override
    public Optional<Boolean> deleteOffer(Integer id) {
        return deleteOffer.deleteOffer(id);
    }

    @Override
    public void deleteAllOffers() {
        deleteOffer.deleteAllOffers();
    }

    @Override
    public Optional<OfferDto> getOfferById(Integer id) {
        return retrieveOffer.getOfferById(id);
    }

    @Override
    public Optional<List<OfferDto>> getAllOffers() {
        return retrieveOffer.getAllOffers();
    }

    @Override
    public Optional<List<OfferDto>> getNonExpireOffers(String date) {
        return Optional.ofNullable(retrieveOffer.getNonExpireOffers(date).orElseThrow(() -> new NoSuchResourceFoundException("Error with in date")));
    }
}
