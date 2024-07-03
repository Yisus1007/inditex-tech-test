package com.inditex.hiring.domain.ports;

public interface DeleteOffer {
    boolean deleteOffer(Integer id);

    void deleteAllOffers();

}
