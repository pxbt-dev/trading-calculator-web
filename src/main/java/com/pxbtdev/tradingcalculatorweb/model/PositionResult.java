package com.pxbtdev.tradingcalculatorweb.model;

public class PositionResult {
    private double riskPercentage;
    private double unitsToBuy;
    private double totalPositionSize;
    private double maxRiskDollars;
    private double riskPerShare;

    public PositionResult(double riskPercentage, double unitsToBuy, double totalPositionSize,
                          double maxRiskDollars, double riskPerShare) {
        this.riskPercentage = riskPercentage;
        this.unitsToBuy = unitsToBuy;
        this.totalPositionSize = totalPositionSize;
        this.maxRiskDollars = maxRiskDollars;
        this.riskPerShare = riskPerShare;
    }

    // Use manual getters rather than lombok
    public double getRiskPercentage() { return riskPercentage; }
    public double getUnitsToBuy() { return unitsToBuy; }
    public double getTotalPositionSize() { return totalPositionSize; }
    public double getMaxRiskDollars() { return maxRiskDollars; }
    public double getRiskPerShare() { return riskPerShare; }
}