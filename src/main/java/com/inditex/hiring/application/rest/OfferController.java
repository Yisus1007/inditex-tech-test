package com.inditex.hiring.application.rest;

import java.util.List;
import java.util.Optional;

import com.inditex.hiring.application.service.OfferService;
import com.inditex.hiring.domain.dto.OfferDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@RestController
public class OfferController {
	private final OfferService offerService;

	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}
	//Crear
	@RequestMapping(value="/offer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public boolean createOffer(@RequestBody @Valid OfferDto offerdto){
		return true;
	}

	//Borrar por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Long> deleteOfferById(@PathVariable("id") Long id){
		return null;
	}

	//Obtener por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public OfferDto getOfferById(@PathVariable Long id){
		return null;

	}

	//Eliminar todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllOffers() {


	}

	//Endopint para optener todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<OfferDto> getAllOffers() {
		return null;

	}


}