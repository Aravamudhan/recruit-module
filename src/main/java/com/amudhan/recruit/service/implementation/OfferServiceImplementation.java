package com.amudhan.recruit.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.repository.OfferRepository;
import com.amudhan.recruit.service.OfferService;

@Service
public class OfferServiceImplementation implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImplementation(final OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  private Offer findOfferChecked(Long id) throws EntityNotFoundException {
    return offerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
  }

  @Override
  public Offer create(Offer offer) {
    return offerRepository.save(offer);
  }

  @Override
  public Offer findById(Long id) throws EntityNotFoundException {
    return findOfferChecked(id);
  }

  @Override
  public Offer update(Offer offer) throws EntityNotFoundException {
    return offerRepository.save(offer);
  }

  @Override
  public void deleteById(Long id) {
    offerRepository.deleteById(id);
  }

  @Override
  public List<Offer> findAll() {
    return offerRepository.findAll();
  }


}
