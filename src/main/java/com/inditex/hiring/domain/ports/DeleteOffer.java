package com.inditex.hiring.domain.ports;

import java.util.Optional;

public interface DeleteOffer {
    Optional<Boolean> deleteOffer(Integer id);
    void deleteAllOffers();

}
