package com.personalcapital.montecarlosimulation.model;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Portfolio {

    private String portfolioType;
    private double initialAmount;
    private double mean;
    private double sd;
    private double inflation;
    private int numberOfSimulations;
    private int periodInYear;
    private StatisticalData statisticalData;

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
        this.statisticalData = new StatisticalData(mean, sd);
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

    public StatisticalData getStatisticalData() {
        return statisticalData;
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

    public void setStatisticalData(StatisticalData statisticalData) {
        this.statisticalData = statisticalData;
    }

    public static class StatisticalData {

        private NormalDistribution normalDistribution;
        private DescriptiveStatistics descriptiveStatistics;

        public StatisticalData(double mean, double standardDeviation) {
            this.normalDistribution = new NormalDistribution(mean, standardDeviation);
            this.descriptiveStatistics = new DescriptiveStatistics();
        }

        public NormalDistribution getNormalDistribution() {
            return normalDistribution;
        }

        public void setNormalDistribution(NormalDistribution normalDistribution) {
            this.normalDistribution = normalDistribution;
        }

        public DescriptiveStatistics getDescriptiveStatistics() {
            return descriptiveStatistics;
        }

        public void setDescriptiveStatistics(DescriptiveStatistics descriptiveStatistics) {
            this.descriptiveStatistics = descriptiveStatistics;
        }
    }

}
