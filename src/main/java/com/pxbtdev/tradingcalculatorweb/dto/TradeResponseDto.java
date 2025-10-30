package com.pxbtdev.tradingcalculatorweb.dto;

import com.pxbtdev.tradingcalculatorweb.model.PositionResult;

public class TradeResponseDto {

    private double unitsToBuy;
    private double totalPositionSize;
    private double riskPerShare;
    private double riskPercentage;
    private String riskRewardRatio;

    // UPDATED CONSTRUCTOR
    public TradeResponseDto(PositionResult result) {
        this.unitsToBuy = result.getUnitsToBuy();
        this.totalPositionSize = result.getTotalPositionSize();
        this.riskPerShare = result.getRiskPerShare();
        this.riskPercentage = result.getRiskPercentage();
        this.riskRewardRatio = result.getRiskRewardRatio(); // NEW
    }

    // Default constructor
    public TradeResponseDto() {}

    // Getters and setters
    public double getUnitsToBuy() { return unitsToBuy; }
    public void setUnitsToBuy(double unitsToBuy) { this.unitsToBuy = unitsToBuy; }

    public double getTotalPositionSize() { return totalPositionSize; }
    public void setTotalPositionSize(double totalPositionSize) { this.totalPositionSize = totalPositionSize; }

    public double getRiskPerShare() { return riskPerShare; }
    public void setRiskPerShare(double riskPerShare) { this.riskPerShare = riskPerShare; }

    public double getRiskPercentage() { return riskPercentage; }
    public void setRiskPercentage(double riskPercentage) { this.riskPercentage = riskPercentage; }

    public String getRiskRewardRatio() { return riskRewardRatio; }
    public void setRiskRewardRatio(String riskRewardRatio) { this.riskRewardRatio = riskRewardRatio; }

}