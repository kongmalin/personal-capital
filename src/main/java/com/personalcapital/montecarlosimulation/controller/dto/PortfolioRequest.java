package com.personalcapital.montecarlosimulation.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PortfolioRequest extends Portfolio implements Serializable {

    private static final long serialVersionUID = 5446262181779868137L;

    public PortfolioRequest() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    @Override
    public String toString() {
        return "PortfolioRequest{" +
                "portfolioType='" + portfolioType + '\'' +
                ", initialAmount=" + initialAmount +
                ", mean=" + mean +
                ", sd=" + sd +
                ", inflation=" + inflation +
                ", numberOfSimulations=" + numberOfSimulations +
                ", periodInYear=" + periodInYear +
                '}';
    }
}
