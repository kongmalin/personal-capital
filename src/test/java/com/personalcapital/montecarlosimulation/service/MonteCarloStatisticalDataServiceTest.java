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
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonteCarloStatisticalDataServiceTest {

    @Autowired
    private MonteCarloSimulationService monteCarloSimulationService;

    @Test
    public void testRunMonteCarloSimulation_noPortofio() {
        Assert.assertTrue(monteCarloSimulationService.runMonteCarloSimulation(Collections.emptyList()).isEmpty());
    }

    @Test
    public void testRunMonteCarloSimulation() {
        PortfolioRequest test = createPortfolioRequest("test", 5500000.0, 0.020, 0.05, 0.04, 200, 1000000);
        PortfolioRequest aggressive = createPortfolioRequest("aggressinve", 100000.0, 0.035, 0.094324, 0.15675, 20, 10000);
        PortfolioRequest veryConservative = createPortfolioRequest("veryConservative", 100000.0, 0.035, 0.06189, 0.063438, 20, 10000);

        List<PortfolioResponse> responseList = monteCarloSimulationService.runMonteCarloSimulation(Arrays.asList(test, aggressive, veryConservative));
        Assert.assertTrue(!responseList.isEmpty());
        Assert.assertEquals(3, responseList.size());
    }

    private PortfolioRequest createPortfolioRequest(String portfolioType, double initialAmount, double inflation, double mean, double sd, int periodInYear, int numberOfSimulation) {
        PortfolioRequest portfolioRequest = new PortfolioRequest();
        portfolioRequest.setPortfolioType(portfolioType);
        portfolioRequest.setInitialAmount(initialAmount);
        portfolioRequest.setInflation(inflation);
        portfolioRequest.setMean(mean);
        portfolioRequest.setSd(sd);
        portfolioRequest.setPeriodInYear(periodInYear);
        portfolioRequest.setNumberOfSimulations(numberOfSimulation);
        return portfolioRequest;
    }

}
