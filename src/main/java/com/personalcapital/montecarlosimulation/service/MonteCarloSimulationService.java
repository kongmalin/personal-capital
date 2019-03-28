package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.config.AppConfig;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;
import com.personalcapital.montecarlosimulation.model.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class MonteCarloSimulationService {

    @Autowired
    private AppConfig appConfig;

    public List<PortfolioResponse> runMonteCarloSimulation(List<PortfolioRequest> portfolioRequestList) {
        if (!CollectionUtils.isEmpty(portfolioRequestList)) {
            ExecutorService threadPool = Executors.newFixedThreadPool(appConfig.threadPoolSize);
            List<Portfolio> portfolioList = new ArrayList<>();

            portfolioRequestList.forEach(portfolioRequest -> {
                Portfolio portfolio = new Portfolio(
                        portfolioRequest.getInitialAmount(),
                        portfolioRequest.getMean(),
                        portfolioRequest.getSd(),
                        portfolioRequest.getInflation(),
                        portfolioRequest.getNumberOfSimulations(),
                        portfolioRequest.getPeriodInYear()
                );
                PortfolioCallable portfolioRunnable = new PortfolioCallable(portfolio);
                Future<Portfolio> portfolioFuture = threadPool.submit(portfolioRunnable);
                try {
                    portfolioList.add(portfolioFuture.get());
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            });

            threadPool.shutdown();

            return portfolioList.stream().map(portfolio -> {
                PortfolioResponse portfolioResponse = new PortfolioResponse();
                portfolioResponse.setInitialAmount(portfolio.getInitialAmount());
                portfolioResponse.setMean(portfolio.getMean());
                portfolioResponse.setSd(portfolio.getSd());
                portfolioResponse.setInflation(portfolio.getInflation());
                portfolioResponse.setNumberOfSimulations(portfolio.getNumberOfSimulations());
                portfolioResponse.setPeriodInYear(portfolio.getPeriodInYear());
                portfolioResponse.setMedian(portfolio.getSimulation().getDescriptiveStatistics().getPercentile(50));
                portfolioResponse.setTenPercentBestCase(portfolio.getSimulation().getDescriptiveStatistics().getPercentile(90));
                portfolioResponse.setTenPercentWorstCase(portfolio.getSimulation().getDescriptiveStatistics().getPercentile(10));
                return portfolioResponse;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
