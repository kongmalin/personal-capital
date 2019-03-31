package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.model.Portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.Callable;

public class PortfolioCallable implements Callable<Portfolio> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Portfolio portfolio;

    public PortfolioCallable(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public Portfolio call() throws Exception {
        logger.info("Running simulation for [portfolioType={}]", portfolio.getPortfolioType());
        try {
            for (int i = 0; i < portfolio.getNumberOfSimulations(); i++) {
                double amount = portfolio.getInitialAmount();
                double afterInflation = 1 - portfolio.getInflation();
                for (int j = 0; j < portfolio.getPeriodInYear(); j++) {
                    amount = amount * (1 + portfolio.getStatisticalData().getNormalDistribution().sample());
                    amount = amount * afterInflation;
                }
                portfolio.getStatisticalData().getDescriptiveStatistics().addValue(amount);
            }
        } catch(Exception ex) {
            logger.error("Error occurred while running simulation for [portfolioType={}]", portfolio.getPortfolioType(), ex);
        }
        logger.info("Finished running simulation for [portfolioType={}]", portfolio.getPortfolioType());
        return portfolio;
    }

}
