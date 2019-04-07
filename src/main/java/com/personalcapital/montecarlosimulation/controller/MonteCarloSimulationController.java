package com.personalcapital.montecarlosimulation.controller;

import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;
import com.personalcapital.montecarlosimulation.service.MonteCarloSimulationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
public class MonteCarloSimulationController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private MonteCarloSimulationService monteCarloSimulationService;

    @PostMapping(value = "/monte-carlo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PortfolioResponse>> calculateMonteCarlo(@RequestBody List<PortfolioRequest> portfolioRequestList) {
        logger.info("Receive request to calculate monte carlo [portfolioRequestList={}].", portfolioRequestList);
        List<PortfolioResponse> portfolioResponseList = monteCarloSimulationService.runMonteCarloSimulation(portfolioRequestList);
        if (CollectionUtils.isEmpty(portfolioResponseList)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(portfolioResponseList, HttpStatus.OK);
    }

}
