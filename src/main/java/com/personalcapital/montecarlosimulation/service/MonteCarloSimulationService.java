package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.config.AppConfig;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;
import com.personalcapital.montecarlosimulation.model.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
            List<Future<Portfolio>> portfolioList = new ArrayList<>();

            portfolioRequestList.forEach(portfolioRequest -> {
                if (!StringUtils.isEmpty(portfolioRequest.getPortfolioType()) &&
                        portfolioRequest.getInflation() != null &&
                        portfolioRequest.getInitialAmount() != null &&
                        portfolioRequest.getMean() != null &&
                        portfolioRequest.getNumberOfSimulations() != null &&
                        portfolioRequest.getPeriodInYear() != null &&
                        portfolioRequest.getSd() != null
                ) {
                    Portfolio portfolio = new Portfolio(
                            portfolioRequest.getPortfolioType(),
                            portfolioRequest.getInitialAmount(),
                            portfolioRequest.getMean(),
                            portfolioRequest.getSd(),
                            portfolioRequest.getInflation(),
                            portfolioRequest.getNumberOfSimulations(),
                            portfolioRequest.getPeriodInYear()
                    );
                    PortfolioCallable portfolioRunnable = new PortfolioCallable(portfolio);
                    Future<Portfolio> portfolioFuture = threadPool.submit(portfolioRunnable);
                    portfolioList.add(portfolioFuture);
                }
            });

            threadPool.shutdown();

            return portfolioList.stream().map(portfolioFuture -> {
                Portfolio portfolio = null;
                try {
                    portfolio = portfolioFuture.get();
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
                PortfolioResponse portfolioResponse = new PortfolioResponse();
                portfolioResponse.setPortfolioType(portfolio.getPortfolioType());
                portfolioResponse.setInitialAmount(portfolio.getInitialAmount());
                portfolioResponse.setMean(portfolio.getMean());
                portfolioResponse.setSd(portfolio.getSd());
                portfolioResponse.setInflation(portfolio.getInflation());
                portfolioResponse.setNumberOfSimulations(portfolio.getNumberOfSimulations());
                portfolioResponse.setPeriodInYear(portfolio.getPeriodInYear());
                portfolioResponse.setMedian(portfolio.getStatisticalData().getDescriptiveStatistics().getPercentile(50));
                portfolioResponse.setTenPercentBestCase(portfolio.getStatisticalData().getDescriptiveStatistics().getPercentile(90));
                portfolioResponse.setTenPercentWorstCase(portfolio.getStatisticalData().getDescriptiveStatistics().getPercentile(10));
                return portfolioResponse;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
