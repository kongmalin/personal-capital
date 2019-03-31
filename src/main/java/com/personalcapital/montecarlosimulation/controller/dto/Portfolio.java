package com.personalcapital.montecarlosimulation.controller.dto;

abstract class Portfolio {

    protected String portfolioType;
    protected Double initialAmount;
    protected Double mean;
    protected Double sd;
    protected Double inflation;
    protected Integer numberOfSimulations;
    protected Integer periodInYear;

}
