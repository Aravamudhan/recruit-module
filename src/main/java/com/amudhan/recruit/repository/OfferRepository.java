package com.amudhan.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amudhan.recruit.domain.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}
