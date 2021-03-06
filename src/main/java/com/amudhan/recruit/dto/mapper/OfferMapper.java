package com.amudhan.recruit.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.dto.OfferDTO;

public class OfferMapper {

  public static OfferDTO toDto(Offer offer) {
    OfferDTO offerDTO = new OfferDTO();
    if (offer != null) {
      offerDTO.id(offer.getId()).startDate(offer.getStartDate())
          .jobApplicationsCount(offer.getJobApplicationsCount()).jobTitle(offer.getJobTitle());
    }
    return offerDTO;
  }

  public static List<OfferDTO> toDtoList(List<Offer> offerList) {
    List<OfferDTO> offerDtos = new ArrayList<>();
    offerList.stream().forEach(offer -> offerDtos.add(toDto(offer)));
    return offerDtos;
  }

  public static Offer toEntity(OfferDTO offerDTO) {
    Offer offer = new Offer();
    if (offerDTO.getJobTitle() != null) {
      offer.setJobTitle(offerDTO.getJobTitle());
    }
    if (offerDTO.getStartDate() != null) {
      offer.setStartDate(offerDTO.getStartDate());
    }
    offer.setId(offerDTO.getId());
    return offer;
  }

}
