package com.personalcapital.montecarlosimulation.model;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Portfolio {

    private String portfolioType;
    private double initialAmount;
    // return as double
    private double mean;
    // risk as double
    private double sd;
    private double inflation;
    private int numberOfSimulations;
    private int periodInYear;
    // use to draw random sample data with mean and sd
    private NormalDistribution normalDistribution;
    // simulation history data
    private simulationData simulationData;

    public Portfolio() {
    }

    public Portfolio(String portfolioType, double initialAmount, double mean, double sd, double inflation, int numberOfSimulations, int periodInYear) {
        this.portfolioType = portfolioType;
        this.initialAmount = initialAmount;
        this.mean = mean;
        this.sd = sd;
        this.inflation = inflation;
        this.numberOfSimulations = numberOfSimulations;
        this.periodInYear = periodInYear;
        this.normalDistribution = new NormalDistribution(mean, sd);
        this.simulationData = new simulationData();
    }

    public String getPortfolioType() {
        return portfolioType;
    }

    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public simulationData getSimulationData() {
        return simulationData;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public int getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public void setNumberOfSimulations(int numberOfSimulations) {
        this.numberOfSimulations = numberOfSimulations;
    }

    public int getPeriodInYear() {
        return periodInYear;
    }

    public void setPeriodInYear(int periodInYear) {
        this.periodInYear = periodInYear;
    }

    public void setSimulationData(simulationData simulationData) {
        this.simulationData = simulationData;
    }

    public NormalDistribution getNormalDistribution() {
        return normalDistribution;
    }

    public void setNormalDistribution(NormalDistribution normalDistribution) {
        this.normalDistribution = normalDistribution;
    }

    public static class simulationData {
        // use to store dataset of each calculation and compute descriptive statistics
        private DescriptiveStatistics descriptiveStatistics;

        public simulationData() {
            this.descriptiveStatistics = new DescriptiveStatistics();
        }

        public DescriptiveStatistics getDescriptiveStatistics() {
            return descriptiveStatistics;
        }

        public void setDescriptiveStatistics(DescriptiveStatistics descriptiveStatistics) {
            this.descriptiveStatistics = descriptiveStatistics;
        }
    }

}
