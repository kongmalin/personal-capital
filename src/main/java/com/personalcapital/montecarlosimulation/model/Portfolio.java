package com.personalcapital.montecarlosimulation.model;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Portfolio {

    private double initialAmount;
    private double mean;
    private double sd;
    private double inflation;
    private int numberOfSimulations;
    private int periodInYear;

    private PortfolioType portfolioType;
    private Simulation simulation;

    public Portfolio() {
    }

    public Portfolio(double initialAmount, double mean, double sd, double inflation, int numberOfSimulations, int periodInYear) {
        this.initialAmount = initialAmount;
        this.mean = mean;
        this.sd = sd;
        this.inflation = inflation;
        this.numberOfSimulations = numberOfSimulations;
        this.periodInYear = periodInYear;
        this.simulation = new Simulation(mean, sd);
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

    public PortfolioType getPortfolioType() {
        return portfolioType;
    }

    public void setPortfolioType(PortfolioType portfolioType) {
        this.portfolioType = portfolioType;
    }

    public Simulation getSimulation() {
        return simulation;
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

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public enum PortfolioType {
        AGGRESSIVE,
        VERY_CONSERVATIVE
    }

    public static class Simulation{

        private NormalDistribution normalDistribution;
        private DescriptiveStatistics descriptiveStatistics;

        public Simulation(double mean, double standardDeviation) {
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
