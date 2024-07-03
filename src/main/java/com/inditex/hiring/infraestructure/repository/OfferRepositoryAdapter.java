package com.inditex.hiring.infraestructure.repository;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;
import com.inditex.hiring.infraestructure.entity.Offer;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OfferRepositoryAdapter implements OfferRepositoryPort {

    final private OfferRepository offerJpaRepository;

    public OfferRepositoryAdapter(OfferRepository offerRepository) {
        this.offerJpaRepository = offerRepository;
    }

    @Override
    public OfferDto save(OfferDto offerDto) {
        return null;
    }

    @Override
    public Optional<OfferDto> findById(Integer id) {
        return null;
    }

    @Override
    public List<OfferDto> findAll() {
        return null;
    }

    @Override
    public List<OfferDto> findNonExpiredOffers() {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public void deleteAll() {

    }
}
