package com.uni.jobfinder.controllers;

import com.uni.jobfinder.services.JobsBGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


public class JobsBGController {

    private JobsBGService jobsBGService;

    @Autowired
    public JobsBGController(JobsBGService jobsBGService) {
        this.jobsBGService = jobsBGService;
    }

    // get all offers from the page of jobs.bg
    @GetMapping("/jobsbg/offers")
    public String getAllOffers() {
        return jobsBGService.getAllOffers(5);
    }

    // get offers from the page of jobs.bg up to a specified amount
    @GetMapping("/jobsbg/offers/{total}")
    public String getLimited(int total) {
        return jobsBGService.getLimited(total, 5);
    }
}
