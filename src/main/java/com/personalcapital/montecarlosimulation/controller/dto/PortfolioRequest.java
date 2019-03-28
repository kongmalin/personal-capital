package com.personalcapital.montecarlosimulation.controller.dto;

import java.io.Serializable;

public class PortfolioRequest implements Serializable {

    private static final long serialVersionUID = 5446262181779868137L;

    private Double initialAmount;
    private Double mean;
    private Double sd;
    private Double inflation;
    private Integer numberOfSimulations;
    private Integer periodInYear;

    public PortfolioRequest() {
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

}
