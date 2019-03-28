package com.personalcapital.montecarlosimulation.service;

import com.personalcapital.montecarlosimulation.model.Portfolio;

import java.util.concurrent.Callable;

public class PortfolioCallable implements Callable<Portfolio> {

    private Portfolio portfolio;

    public PortfolioCallable(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public Portfolio call() throws Exception {
        try {
            for (int i = 0; i < portfolio.getNumberOfSimulations(); i++) {
                double amount = portfolio.getInitialAmount();
                double inflation = portfolio.getInflation();
                for (int j = 0; j < portfolio.getPeriodInYear(); j++) {
                    amount = amount * (1 + portfolio.getSimulation().getNormalDistribution().sample());
                    amount = amount * (1 - inflation);
                }
                portfolio.getSimulation().getDescriptiveStatistics().addValue(amount);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return portfolio;
    }

}
