package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.config.AppConfig;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioRequest;
import com.personalcapital.montecarlosimulation.controller.dto.PortfolioResponse;
import com.personalcapital.montecarlosimulation.model.Portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.invoke.MethodHandles;
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

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private AppConfig appConfig;

    /**
     * Use to calculate median, tenPercentBestCase, tenPercentWorstCase
     *
     * @param portfolioRequestList
     * @return PortfolioResponse list
     */
    public List<PortfolioResponse> runMonteCarloSimulation(List<PortfolioRequest> portfolioRequestList) {
        if (!CollectionUtils.isEmpty(portfolioRequestList)) {
            // create thread pool, default pool size is 2
            ExecutorService threadPool = Executors.newFixedThreadPool(appConfig.monteCarloThreadPoolSize);
            List<Future<Portfolio>> portfolioList = new ArrayList<>();

            // for each portfolio, create execution task and add to the thread pool
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
                    // create worker with initial portfolio
                    PortfolioWorker portfolioWorker = new PortfolioWorker(portfolio);
                    // add worker to the thread pool to execute the task and return future
                    Future<Portfolio> portfolioFuture = threadPool.submit(portfolioWorker);
                    portfolioList.add(portfolioFuture);
                }
            });

            threadPool.shutdown();

            // get the result nack from future after finished execution, and map to PortfolioResponse
            return portfolioList.stream().map(portfolioFuture -> {
                Portfolio portfolio = null;
                try {
                    portfolio = portfolioFuture.get();
                } catch (InterruptedException | ExecutionException ex) {
                    logger.error("Error while calculating monte carlo: ", ex);
                }
                PortfolioResponse portfolioResponse = new PortfolioResponse();
                portfolioResponse.setPortfolioType(portfolio.getPortfolioType());
                portfolioResponse.setInitialAmount(portfolio.getInitialAmount());
                portfolioResponse.setMean(portfolio.getMean());
                portfolioResponse.setSd(portfolio.getSd());
                portfolioResponse.setInflation(portfolio.getInflation());
                portfolioResponse.setNumberOfSimulations(portfolio.getNumberOfSimulations());
                portfolioResponse.setPeriodInYear(portfolio.getPeriodInYear());
                // get medium
                portfolioResponse.setMedian(portfolio.getSimulationData().getDescriptiveStatistics().getPercentile(50));
                // get 90% top
                portfolioResponse.setTenPercentBestCase(portfolio.getSimulationData().getDescriptiveStatistics().getPercentile(90));
                // get 10% bottom
                portfolioResponse.setTenPercentWorstCase(portfolio.getSimulationData().getDescriptiveStatistics().getPercentile(10));
                return portfolioResponse;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
