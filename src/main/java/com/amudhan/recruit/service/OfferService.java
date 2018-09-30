package com.amudhan.recruit.service;

import java.util.List;

import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.exception.EntityNotFoundException;

public interface OfferService {
  public Offer create(Offer offer);

  public Offer findById(Long id) throws EntityNotFoundException;

  public Offer update(Offer offer) throws EntityNotFoundException;

  public void deleteById(Long id);

  public List<Offer> findAll();

}
