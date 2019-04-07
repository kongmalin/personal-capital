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
public class MonteCarloSimulationServiceTest {

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
        responseList.forEach(portfolioResponse -> {
            if (portfolioResponse.getPortfolioType().equals(test.getPortfolioType())) {
                comparePortfolios(test, portfolioResponse);
            } else if (portfolioResponse.getPortfolioType().equals(aggressive.getPortfolioType())) {
                comparePortfolios(aggressive, portfolioResponse);
            } else {
                comparePortfolios(veryConservative, portfolioResponse);
            }
        });
    }

    private void comparePortfolios(PortfolioRequest portfolioRequest, PortfolioResponse portfolioResponse) {
        Assert.assertEquals(portfolioRequest.getPortfolioType(), portfolioResponse.getPortfolioType());
        Assert.assertEquals(portfolioRequest.getInitialAmount(), portfolioResponse.getInitialAmount());
        Assert.assertEquals(portfolioRequest.getInflation(), portfolioResponse.getInflation());
        Assert.assertEquals(portfolioRequest.getMean(), portfolioResponse.getMean());
        Assert.assertEquals(portfolioRequest.getNumberOfSimulations(), portfolioResponse.getNumberOfSimulations());
        Assert.assertEquals(portfolioRequest.getPeriodInYear(), portfolioResponse.getPeriodInYear());
        Assert.assertEquals(portfolioRequest.getSd(), portfolioResponse.getSd());
        Assert.assertTrue(portfolioResponse.getInitialAmount() < portfolioResponse.getMedian() &&
                portfolioResponse.getInitialAmount() < portfolioResponse.getTenPercentWorstCase() &&
                portfolioResponse.getMedian() < portfolioResponse.getTenPercentBestCase() &&
                portfolioResponse.getMedian() > portfolioResponse.getTenPercentWorstCase());
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
