package com.inditex.hiring.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findByBrandId(Integer id);
    Optional<Offer> findByOfferId(Long id);

}
