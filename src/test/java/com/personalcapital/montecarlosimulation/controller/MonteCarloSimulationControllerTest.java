package com.personalcapital.montecarlosimulation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MonteCarloSimulationControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final ParameterizedTypeReference<List<PortfolioResponse>> PORTFOLIO_RESPONSE_LIST = new ParameterizedTypeReference<List<PortfolioResponse>>() {};

    @Test
    public void testCalculateMonteCarlo() throws JsonProcessingException {
        PortfolioRequest aggressive = createPortfolioRequest("aggressinve", 100000.0, 0.035, 0.094324, 0.15675, 20, 10000);
        PortfolioRequest veryConservative = createPortfolioRequest("veryConservative", 100000.0, 0.035, 0.06189, 0.063438, 20, 10000);

        String body = objectMapper.writeValueAsString(Arrays.asList(aggressive, veryConservative));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<List<PortfolioResponse>> responseEntity = testRestTemplate.exchange("/monte-carlo", HttpMethod.POST, new HttpEntity<>(body, headers), PORTFOLIO_RESPONSE_LIST);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertTrue(!responseEntity.getBody().isEmpty());
        Assert.assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testCalculateMonteCarlo_noContent() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Object> responseEntity = testRestTemplate.exchange("/monte-carlo", HttpMethod.POST, new HttpEntity<>("[{}]", headers), Object.class);
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testCalculateMonteCarlo_badRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ErrorResult> responseEntity = testRestTemplate.exchange("/monte-carlo", HttpMethod.POST, new HttpEntity<>("dummy_body", headers), ErrorResult.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
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
