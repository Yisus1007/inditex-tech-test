package com.inditex.hiring.application.service;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.CreateOffer;
import com.inditex.hiring.domain.ports.DeleteOffer;
import com.inditex.hiring.domain.ports.RetrieveOffer;
import org.springframework.stereotype.Service;

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
    public OfferDto createOffer(OfferDto offerDto) throws ParseException {
        return createOffer.createOffer(offerDto);
    }

    @Override
    public boolean deleteOffer(Integer id) {
        return deleteOffer.deleteOffer(id);
    }

    @Override
    public void deleteAllOffers() {

    }

    @Override
    public Optional<OfferDto> getOfferById(Integer id) {
        return retrieveOffer.getOfferById(id);
    }

    @Override
    public List<OfferDto> getAllOffers() {
        return retrieveOffer.getAllOffers();
    }

    @Override
    public List<OfferDto> getNonExpireOffers() {
        return retrieveOffer.getNonExpireOffers();
    }
}
