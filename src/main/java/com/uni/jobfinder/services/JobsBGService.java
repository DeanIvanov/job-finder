package com.uni.jobfinder.services;

import org.springframework.stereotype.Service;


@Service
public interface JobsBGService {

    String getAllOffers();
    String getLimited(int total);

}
