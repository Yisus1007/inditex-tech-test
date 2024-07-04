package com.inditex.hiring.application.rest;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.inditex.hiring.application.rest.exception.NoSuchResourceFoundException;
import com.inditex.hiring.application.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inditex.hiring.domain.dto.OfferDto;

import javax.validation.Valid;

@RestController
public class OfferController {

	private final OfferService offerService;

	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}

	//Crear
	@RequestMapping(value="/offer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<OfferDto> createOffer(@RequestBody @Valid OfferDto offerdto) throws ParseException {
		return Optional.ofNullable(offerService.createOffer(offerdto)
                .orElseThrow(() -> new NoSuchResourceFoundException("Error creating offer")));
	}

	//Borrar por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Object> deleteOfferById(@PathVariable("id") Long id){
		return Optional.ofNullable(offerService.deleteOffer(id.intValue())
                .orElseThrow(() -> new NoSuchResourceFoundException("Error trying to delete record")));
	}

	//Obtener por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Optional<OfferDto> getOfferById(@PathVariable Long id){
		return Optional.ofNullable(offerService.getOfferById(id.intValue())
				.orElseThrow(() -> new NoSuchResourceFoundException("Error retrieving data")));
	}

	//Eliminar todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteAllOffers() {
		offerService.deleteAllOffers();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	//Endopint para optener todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Optional<List<OfferDto>> getAllOffers() {
		return Optional.ofNullable(offerService.getAllOffers()
                .orElseThrow(() -> new NoSuchResourceFoundException("Error deleting all offers")));

	}

	//Endopint para optener las ofertas vigentes
	@RequestMapping(value = "/offer", method = RequestMethod.GET, params = {"date"})
	@ResponseStatus(HttpStatus.OK)
	public Optional<List<OfferDto>> getNonExpireOffers(@RequestParam String date) {
		return Optional.ofNullable(offerService.getNonExpireOffers(date)
				.orElseThrow(() -> new NoSuchResourceFoundException("Error deleting all offers")));

	}


}