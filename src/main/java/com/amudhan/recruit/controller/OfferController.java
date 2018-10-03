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

@RestController
@RequestMapping("v1/offers")
public class OfferController {

  private final OfferService offerService;

  public OfferController(final OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/{offerId}")
  public Offer getOffer(@PathVariable long offerId) throws EntityNotFoundException {
    return offerService.findById(offerId);
  }

  @GetMapping
  public List<OfferDTO> getOffers() {
    return OfferMapper.toDtoList(offerService.findAll());
  }


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OfferDTO createOffer(@Valid @RequestBody OfferDTO offerDTO) {
    return OfferMapper.toDto(offerService.create(OfferMapper.toEntity(offerDTO)));
  }

  @PutMapping
  public OfferDTO updateOffer(@Valid @RequestBody OfferDTO offerDTO)
      throws EntityNotFoundException {
    return OfferMapper.toDto(offerService.update(OfferMapper.toEntity(offerDTO)));
  }

  @DeleteMapping("/{offerId}")
  public void deleteOffer(@PathVariable long offerId) throws EntityNotFoundException {
    offerService.deleteById(offerId);
  }

}
