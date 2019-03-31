package com.personalcapital.montecarlosimulation.controller.dto;

import org.apache.commons.math3.util.Precision;

import java.io.Serializable;

public class PortfolioResponse extends Portfolio implements Serializable {

    private static final long serialVersionUID = -6339002160220409793L;

    private Double median;
    private Double tenPercentBestCase;
    private Double tenPercentWorstCase;

    public PortfolioResponse() {
    }

    public String getPortfolioType() {
        return portfolioType;
    }

    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Double getMean() {
        return mean;
    }

    public void setMean(Double mean) {
        this.mean = mean;
    }

    public Double getSd() {
        return sd;
    }

    public void setSd(Double sd) {
        this.sd = sd;
    }

    public Double getInflation() {
        return inflation;
    }

    public void setInflation(Double inflation) {
        this.inflation = inflation;
    }

    public Integer getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public void setNumberOfSimulations(Integer numberOfSimulations) {
        this.numberOfSimulations = numberOfSimulations;
    }

    public Integer getPeriodInYear() {
        return periodInYear;
    }

    public void setPeriodInYear(Integer periodInYear) {
        this.periodInYear = periodInYear;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = Precision.round(median, 2);
    }

    public Double getTenPercentBestCase() {
        return tenPercentBestCase;
    }

    public void setTenPercentBestCase(Double tenPercentBestCase) {
        this.tenPercentBestCase = Precision.round(tenPercentBestCase, 2);
    }

    public Double getTenPercentWorstCase() {
        return tenPercentWorstCase;
    }

    public void setTenPercentWorstCase(Double tenPercentWorstCase) {
        this.tenPercentWorstCase = Precision.round(tenPercentWorstCase, 2);
    }

    @Override
    public String toString() {
        return "PortfolioResponse{" +
                "portfolioType='" + portfolioType + '\'' +
                ", initialAmount=" + initialAmount +
                ", mean=" + mean +
                ", sd=" + sd +
                ", inflation=" + inflation +
                ", numberOfSimulations=" + numberOfSimulations +
                ", periodInYear=" + periodInYear +
                ", median=" + median +
                ", tenPercentBestCase=" + tenPercentBestCase +
                ", tenPercentWorstCase=" + tenPercentWorstCase +
                '}';
    }

}
