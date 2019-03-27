package com.personalcapital.montecarlosimulation.controller;

import com.personalcapital.montecarlosimulation.service.MonteCarloSimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonteCarloSimulationController {

    @Autowired
    private MonteCarloSimulationService monteCarloSimulationService;

    @PostMapping(value = "/monte-carlo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> calculateMonteCarlo() {
        return null;
    }

}
