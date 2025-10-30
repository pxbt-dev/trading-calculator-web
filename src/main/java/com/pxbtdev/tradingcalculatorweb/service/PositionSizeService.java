package com.pxbtdev.tradingcalculatorweb.service;

import com.pxbtdev.tradingcalculatorweb.model.PositionResult;
import com.pxbtdev.tradingcalculatorweb.model.TradeParameters;
import org.springframework.stereotype.Service;

@Service
public class PositionSizeService {

    public PositionResult calculatePosition(TradeParameters params) {
        // FIX: Multiply by 100 for percentage
        double riskPercentage = (params.getRiskDollars() / params.getAccountSize()) * 100.0;
        double riskPerShare = Math.abs(params.getEntryPrice() - params.getStopLossPrice());
        double unitsToBuy = params.getRiskDollars() / riskPerShare;
        double totalPositionSize = unitsToBuy * params.getEntryPrice();

        // NEW: Calculate Risk/Reward Ratio
        String riskRewardRatio = calculateRiskRewardRatio(
                params.getEntryPrice(),
                params.getStopLossPrice(),
                params.getTargetPrice()
        );

        return new PositionResult(riskPercentage, unitsToBuy, totalPositionSize,
                params.getRiskDollars(), riskPerShare, riskRewardRatio);
    }

    private String calculateRiskRewardRatio(double entryPrice, double stopLossPrice, double targetPrice) {
        if (targetPrice <= 0) {
            return "N/A";
        }

        double risk = Math.abs(entryPrice - stopLossPrice);
        double reward = Math.abs(targetPrice - entryPrice);

        if (risk == 0) {
            return "N/A";
        }

        double ratio = reward / risk;
        return String.format("1:%.2f", ratio);
    }
}