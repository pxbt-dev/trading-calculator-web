package com.pxbtdev.tradingcalculatorweb.model;

public class TradeParameters {
    private double accountSize;
    private double riskDollars;
    private double entryPrice;
    private double stopLossPrice;
    private double targetPrice;

    // No-arg constructor with sensible defaults
    public TradeParameters() {
        this.accountSize = 10000;
        this.riskDollars = 100;
        this.entryPrice = 0;
        this.stopLossPrice = 0;
        this.targetPrice = 0;
    }

    // Constructor with all arguments as we tried Lombok and ran into issues
    public TradeParameters(double accountSize, double riskDollars, double entryPrice,
                           double stopLossPrice, double targetPrice) {
        this.accountSize = accountSize;
        this.riskDollars = riskDollars;
        this.entryPrice = entryPrice;
        this.stopLossPrice = stopLossPrice;
        this.targetPrice = targetPrice;
    }

    // Manual getters and setters
    public double getAccountSize() { return accountSize; }
    public void setAccountSize(double accountSize) { this.accountSize = accountSize; }

    public double getRiskDollars() { return riskDollars; }
    public void setRiskDollars(double riskDollars) { this.riskDollars = riskDollars; }

    public double getEntryPrice() { return entryPrice; }
    public void setEntryPrice(double entryPrice) { this.entryPrice = entryPrice; }

    public double getStopLossPrice() { return stopLossPrice; }
    public void setStopLossPrice(double stopLossPrice) { this.stopLossPrice = stopLossPrice; }

    public double getTargetPrice() { return targetPrice; }
    public void setTargetPrice(double targetPrice) { this.targetPrice = targetPrice; }

    // Temporary for debugging: use toString
    @Override
    public String toString() {
        return String.format("TradeParameters{accountSize=%.2f, riskDollars=%.2f, entryPrice=%.4f, stopLoss=%.4f, target=%.4f}",
                accountSize, riskDollars, entryPrice, stopLossPrice, targetPrice);
    }
}