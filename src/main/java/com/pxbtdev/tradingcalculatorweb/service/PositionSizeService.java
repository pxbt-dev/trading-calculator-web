package com.pxbtdev.tradingcalculatorweb.service;

import com.pxbtdev.tradingcalculatorweb.model.PositionResult;
import com.pxbtdev.tradingcalculatorweb.model.TradeParameters;

public class PositionSizeService {

    public PositionResult calculatePosition(TradeParameters params) {
        double riskPercentage = params.getRiskDollars() / params.getAccountSize();
        double riskPerShare = Math.abs(params.getEntryPrice() - params.getStopLossPrice());
        double unitsToBuy = params.getRiskDollars() / riskPerShare;
        double totalPositionSize = unitsToBuy * params.getEntryPrice();

        return new PositionResult(riskPercentage, unitsToBuy, totalPositionSize,
                params.getRiskDollars(), riskPerShare);
    }
}