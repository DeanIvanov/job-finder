package com.uni.jobfinder.services;

import org.springframework.stereotype.Service;


@Service
public interface JobsBGService {

    String getAllOffers(int timeout);
    String getLimited(int total, int timeout);

}
