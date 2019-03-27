package com.personalcapital.montecarlosimulation.model;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Portfolio {

    private long mean;
    private long sd;
    private PortfolioType portfolioType;
    private Simulation simulation;

    public Portfolio() {
    }

    public Portfolio(long mean, long sd, PortfolioType portfolioType) {
        this.mean = mean;
        this.sd = sd;
        this.portfolioType = portfolioType;
        this.simulation = new Simulation(mean, sd);
    }

    public long getMean() {
        return mean;
    }

    public void setMean(long mean) {
        this.mean = mean;
    }

    public long getSd() {
        return sd;
    }

    public void setSd(long sd) {
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

        public void saveSimulationResult(double simResult) {
            this.descriptiveStatistics.addValue(simResult);
        }

        public double nextSampleReturn() {
            return this.normalDistribution.sample();
        }

        public double getPercentile(double n){
            return this.descriptiveStatistics.getPercentile(n);
        }
    }

}
