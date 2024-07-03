package com.inditex.hiring.infraestructure.repository;

import com.inditex.hiring.infraestructure.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByBrandId(Integer id);
    Optional<Offer> findByOfferId(Long id);

}
