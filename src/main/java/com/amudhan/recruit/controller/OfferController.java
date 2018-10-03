package com.amudhan.recruit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.dto.OfferDTO;
import com.amudhan.recruit.dto.mapper.OfferMapper;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.service.OfferService;

/**
 * Endpoints for job offers <br/>
 * 
 * TODO: Secure the endpoints using a token based authentication system such as JWT<br/>
 * TODO: Secure the endpoints based on ROLEs too. Only admin users must have access to all the
 * endpoints below<br/>
 * 
 * @author amudhan
 *
 */
@RestController
@RequestMapping("v1/offers")
public class OfferController {

  private final OfferService offerService;

  public OfferController(final OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/{offerId}")
  public OfferDTO getOfferById(@PathVariable long offerId) throws EntityNotFoundException {
    Offer offer = offerService.findById(offerId);
    return OfferMapper.toDto(offer);
  }

  @GetMapping
  public List<OfferDTO> getAllOffers() {
    return OfferMapper.toDtoList(offerService.findAll());
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OfferDTO createOffer(@Valid @RequestBody OfferDTO offerDTO) {
    Offer offer = OfferMapper.toEntity(offerDTO);
    offer = offerService.create(offer);
    return OfferMapper.toDto(offer);
  }

  @PutMapping
  public OfferDTO updateOffer(@Valid @RequestBody OfferDTO offerDTO)
      throws EntityNotFoundException {
    Offer offer = OfferMapper.toEntity(offerDTO);
    offer = offerService.update(offer);
    return OfferMapper.toDto(offer);
  }

  @DeleteMapping("/{offerId}")
  public void deleteOffer(@PathVariable long offerId) throws EntityNotFoundException {
    offerService.deleteById(offerId);
  }

}
