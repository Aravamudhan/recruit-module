package com.amudhan.recruit.service;

import java.util.List;

import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.exception.EntityNotFoundException;

/**
 * OfferService interface<br/>
 * This service provides interface to perform operations on job offers<br/>
 * All implementation of this interface should be secured so that only admin users can access
 * it<br/>
 * 
 * @author amudhan
 *
 */
public interface OfferService {
  public Offer create(Offer offer);

  public Offer findById(Long id) throws EntityNotFoundException;

  public Offer update(Offer offer) throws EntityNotFoundException;

  public void deleteById(Long id);

  public List<Offer> findAll();

}
