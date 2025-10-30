package com.pxbtdev.tradingcalculatorweb.model;

public class PositionResult {
    private double riskPercentage;
    private double unitsToBuy;
    private double totalPositionSize;
    private double riskDollars;
    private double riskPerShare;
    private String riskRewardRatio; // NEW FIELD

    // UPDATED CONSTRUCTOR
    public PositionResult(double riskPercentage, double unitsToBuy, double totalPositionSize,
                          double riskDollars, double riskPerShare, String riskRewardRatio) {
        this.riskPercentage = riskPercentage;
        this.unitsToBuy = unitsToBuy;
        this.totalPositionSize = totalPositionSize;
        this.riskDollars = riskDollars;
        this.riskPerShare = riskPerShare;
        this.riskRewardRatio = riskRewardRatio; // NEW
    }

    // GETTERS AND SETTERS
    public double getRiskPercentage() { return riskPercentage; }
    public void setRiskPercentage(double riskPercentage) { this.riskPercentage = riskPercentage; }

    public double getUnitsToBuy() { return unitsToBuy; }
    public void setUnitsToBuy(double unitsToBuy) { this.unitsToBuy = unitsToBuy; }

    public double getTotalPositionSize() { return totalPositionSize; }
    public void setTotalPositionSize(double totalPositionSize) { this.totalPositionSize = totalPositionSize; }

    public double getRiskDollars() { return riskDollars; }
    public void setRiskDollars(double riskDollars) { this.riskDollars = riskDollars; }

    public double getRiskPerShare() { return riskPerShare; }
    public void setRiskPerShare(double riskPerShare) { this.riskPerShare = riskPerShare; }

    // NEW GETTER/SETTER
    public String getRiskRewardRatio() { return riskRewardRatio; }
    public void setRiskRewardRatio(String riskRewardRatio) { this.riskRewardRatio = riskRewardRatio; }
}