package com.uni.jobfinder.exceptions;

// throw exception if page does not have any offers
public class ResultsNotFoundException extends RuntimeException {
    public ResultsNotFoundException(String message) {
        super(message);
    }

}
