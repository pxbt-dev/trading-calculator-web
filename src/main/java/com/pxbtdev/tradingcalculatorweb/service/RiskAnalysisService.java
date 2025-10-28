package com.pxbtdev.tradingcalculatorweb.service;


import com.pxbtdev.tradingcalculatorweb.model.PositionResult;
import com.pxbtdev.tradingcalculatorweb.model.TradeParameters;

import java.text.DecimalFormat;

public class RiskAnalysisService {
    private static final DecimalFormat percentFormat = new DecimalFormat("0.##%");

    public String analyzeRisk(PositionResult position, TradeParameters params) {
        if (params.getTargetPrice() == 0) return "";

        double riskPercentFromEntry = position.getRiskPerShare() / params.getEntryPrice();
        double rewardPerShare = Math.abs(params.getTargetPrice() - params.getEntryPrice());
        double riskRewardRatio = rewardPerShare / position.getRiskPerShare();

        StringBuilder analysis = new StringBuilder();
        analysis.append("\n📊 RISK ANALYSIS\n");
        analysis.append("════════════════════════════════\n");
        analysis.append(String.format("%-20s %s\n", "Risk % from Entry:", percentFormat.format(riskPercentFromEntry)));
        analysis.append(String.format("%-20s %s\n", "Risk Rating:", getRiskRating(riskPercentFromEntry)));
        analysis.append(String.format("%-20s %s\n", "Risk/Reward Ratio:", getFormattedRRRatio(riskRewardRatio)));
        analysis.append(String.format("%-20s %s\n", "Trade Suggestion:", getSuggestion(riskPercentFromEntry)));
        analysis.append("════════════════════════════════\n");

        return analysis.toString();
    }

    private String getRiskRating(double riskPercent) {
        if (riskPercent < 0.02) return "LOW";
        else if (riskPercent < 0.05) return "MEDIUM";
        else return "HIGH";
    }

    private String getFormattedRRRatio(double ratio) {
        if (ratio >= 1) {
            return "1:" + String.format("%.1f", ratio);
        } else {
            return String.format("%.1f", 1/ratio) + ":1";
        }
    }

    private String getSuggestion(double riskPercent) {
        if (riskPercent < 0.02) return "✅ Good setup";
        else if (riskPercent < 0.05) return "⚠️ Check entry";
        else return "🔴 Consider better entry";
    }
}
