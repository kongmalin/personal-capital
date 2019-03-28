package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonteCarloSimulationServiceTest {

    @Autowired
    private MonteCarloSimulationService monteCarloSimulationService;

    @Test
    public void test() {
        PortfolioRequest aggressive = new PortfolioRequest();
        aggressive.setInitialAmount(100000.0);
        aggressive.setInflation(0.035);
        aggressive.setMean(0.094324);
        aggressive.setSd(0.15675);
        aggressive.setPeriodInYear(20);
        aggressive.setNumberOfSimulations(10000);

        PortfolioRequest veryConservative = new PortfolioRequest();
        veryConservative.setInitialAmount(100000.0);
        veryConservative.setInflation(0.035);
        veryConservative.setMean(0.06189);
        veryConservative.setSd(0.063438);
        veryConservative.setPeriodInYear(20);
        veryConservative.setNumberOfSimulations(10000);

        List<PortfolioResponse> responseList = monteCarloSimulationService.runMonteCarloSimulation(Arrays.asList(aggressive, veryConservative));
        Assert.assertTrue(!responseList.isEmpty());
    }

}
