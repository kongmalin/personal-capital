package com.personalcapital.montecarlosimulation.controller;

import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;
import com.personalcapital.montecarlosimulation.service.MonteCarloSimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonteCarloSimulationController {

    @Autowired
    private MonteCarloSimulationService monteCarloSimulationService;

    @PostMapping(value = "/monte-carlo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PortfolioResponse>> calculateMonteCarlo(@RequestBody List<PortfolioRequest> portfolioRequestList) {
        List<PortfolioResponse> portfolioResponseList = monteCarloSimulationService.runMonteCarloSimulation(portfolioRequestList);
        if (CollectionUtils.isEmpty(portfolioResponseList)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(portfolioResponseList, HttpStatus.OK);
    }

}
