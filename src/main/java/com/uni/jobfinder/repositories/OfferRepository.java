package com.uni.jobfinder.repositories;

import com.uni.jobfinder.models.Offer;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository {

    Offer getByRef(int ref);

    Offer getByLocation(String location);

}
