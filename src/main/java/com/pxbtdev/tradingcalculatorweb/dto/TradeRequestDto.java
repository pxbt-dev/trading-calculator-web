package com.pxbtdev.tradingcalculatorweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradeRequestDto {
    private double accountSize;
    private double riskDollars;
    private double entryPrice;
    private double stopLoss;

    // Use Double (nullable) - no conversion logic in DTO
    @JsonProperty(required = false)
    private Double targetPrice;

    // No-arg constructor
    public TradeRequestDto() {}

    // Simple getters and setters - no business logic
    public double getAccountSize() { return accountSize; }
    public void setAccountSize(double accountSize) { this.accountSize = accountSize; }

    public double getRiskDollars() { return riskDollars; }
    public void setRiskDollars(double riskDollars) { this.riskDollars = riskDollars; }

    public double getEntryPrice() { return entryPrice; }
    public void setEntryPrice(double entryPrice) { this.entryPrice = entryPrice; }

    public double getStopLoss() { return stopLoss; }
    public void setStopLoss(double stopLoss) { this.stopLoss = stopLoss; }

    // Return the raw Double (could be null)
    public Double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }
}