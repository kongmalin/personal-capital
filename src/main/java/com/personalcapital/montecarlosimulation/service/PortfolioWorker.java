package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.model.Portfolio;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.Callable;

public class PortfolioWorker implements Callable<Portfolio> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Portfolio portfolio;

    public PortfolioWorker(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Use to execute simulate
     *
     * @return Portfolio
     */
    @Override
    public Portfolio call() {
        logger.info("Running simulation for [portfolioType={}]", portfolio.getPortfolioType());
        try {
            for (int i = 0; i < portfolio.getNumberOfSimulations(); i++) {
                double amount = portfolio.getInitialAmount();
                NormalDistribution normalDistribution = portfolio.getNormalDistribution();
                double afterInflation = 1 + portfolio.getInflation();
                for (int j = 0; j < portfolio.getPeriodInYear(); j++) {
                    // draw 1 random sample
                    double randomSample = 1 + normalDistribution.sample();
                    // calculate new amount with random sample
                    amount = amount * randomSample;
                    // calculate new amount after inflation
                    amount = amount * afterInflation;
                }
                // add the amount after each simulation to description-statistic to find median, best 10%, worst 10% later
                portfolio.getSimulationData().getDescriptiveStatistics().addValue(amount);
            }
        } catch(Exception ex) {
            logger.error("Error occurred while running simulation for [portfolioType={}]", portfolio.getPortfolioType(), ex);
        }
        logger.info("Finished running simulation for [portfolioType={}]", portfolio.getPortfolioType());
        return portfolio;
    }

}
