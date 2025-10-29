package com.pxbtdev.tradingcalculatorweb.controller;

import com.pxbtdev.tradingcalculatorweb.model.TradeParameters;
import com.pxbtdev.tradingcalculatorweb.model.PositionResult;
import com.pxbtdev.tradingcalculatorweb.service.PositionSizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
@Tag(name = "Trading Calculator", description = "Position size calculation APIs")
public class CalculatorController {

    private final PositionSizeService positionService;
    private final DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
    private final DecimalFormat percentFormat = new DecimalFormat("0.##%");

    public CalculatorController() {
        this.positionService = new PositionSizeService();
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        if (!model.containsAttribute("tradeParameters")) {
            model.addAttribute("tradeParameters", new TradeParameters());
        }
        // Add default message for results panel
        model.addAttribute("defaultMessage", getDefaultMessage());
        return "index";
    }

    @Operation(summary = "Calculate position size")
    @PostMapping("/calculate")
    public String calculatePosition(
            @RequestParam String accountSizeStr,    // Changed to String for decimal detection
            @RequestParam String riskDollarsStr,    // Changed to String for decimal detection
            @RequestParam String entryPriceStr,     // Changed to String for decimal detection
            @RequestParam String stopLossStr,       // Changed to String for decimal detection
            @RequestParam(required = false) String targetPriceStr, // Changed to String
            Model model) {

        try {
            // Parse values
            double accountSize = Double.parseDouble(accountSizeStr);
            double riskDollars = Double.parseDouble(riskDollarsStr);
            double entryPrice = Double.parseDouble(entryPriceStr);
            double stopLoss = Double.parseDouble(stopLossStr);
            Double targetPrice = targetPriceStr != null && !targetPriceStr.isEmpty()
                    ? Double.parseDouble(targetPriceStr) : null;

            // Pre-calculate for validation
            double priceDifference = Math.abs(entryPrice - stopLoss);
            double positionSize = riskDollars / priceDifference;
            double totalValue = positionSize * entryPrice;

            // Validate position doesn't exceed account balance
            if (totalValue > accountSize) {
                model.addAttribute("error",
                        String.format("Position size ($%,.2f) exceeds account balance ($%,.2f). Reduce risk amount or adjust entry/stop prices.",
                                totalValue, accountSize));
                model.addAttribute("defaultMessage", getDefaultMessage());
                return "index";
            }

            // Validate risk amount
            if (riskDollars > accountSize) {
                model.addAttribute("error", "Risk amount cannot exceed account size");
                model.addAttribute("defaultMessage", getDefaultMessage());
                return "index";
            }

            TradeParameters params = new TradeParameters(accountSize, riskDollars,
                    entryPrice, stopLoss,
                    targetPrice != null ? targetPrice : 0);

            PositionResult result = positionService.calculatePosition(params);

            // Add model attributes
            model.addAttribute("params", params);
            model.addAttribute("result", result);
            model.addAttribute("hasTarget", targetPrice != null);

            // Add dynamically formatted strings
            model.addAttribute("formattedAccountSize", formatDynamicCurrency(accountSizeStr, accountSize));
            model.addAttribute("formattedRiskDollars", formatDynamicCurrency(riskDollarsStr, riskDollars));
            model.addAttribute("formattedEntryPrice", formatDynamicCurrency(entryPriceStr, entryPrice));
            model.addAttribute("formattedStopLoss", formatDynamicCurrency(stopLossStr, stopLoss));
            model.addAttribute("formattedTargetPrice", targetPrice != null ?
                    formatDynamicCurrency(targetPriceStr, targetPrice) : "");
            model.addAttribute("formattedPositionSize", formatDynamicPosition(positionSize));
            model.addAttribute("formattedTotalInvestment", currencyFormat.format(totalValue));
            model.addAttribute("formattedRiskPercentage", percentFormat.format(result.getRiskPercentage()));

            // Calculate Risk/Reward ratio
            String riskReward = "N/A";
            if (targetPrice != null) {
                double reward = Math.abs(targetPrice - entryPrice);
                riskReward = String.format("%.2f:1", reward / priceDifference);
            }
            model.addAttribute("formattedRiskReward", riskReward);

        } catch (Exception e) {
            model.addAttribute("error", "Invalid input: " + e.getMessage());
            model.addAttribute("defaultMessage", getDefaultMessage());
        }

        return "index";
    }

    private String formatDynamicCurrency(String input, double value) {
        try {
            if (!input.contains(".")) return String.format("$%,.0f", value);
            int decimals = input.split("\\.")[1].length();
            return String.format("$%,." + decimals + "f", value);
        } catch (Exception e) {
            return String.format("$%,.4f", value); // fallback
        }
    }

    private String formatDynamicPosition(double size) {
        if (size >= 1000000) return String.format("%,.0f", size);
        if (size >= 1000) return String.format("%,.1f", size);
        if (size >= 100) return String.format("%,.1f", size);
        if (size >= 10) return String.format("%,.2f", size);
        if (size >= 1) return String.format("%,.3f", size);
        return String.format("%,.4f", size);
    }

    private String getDefaultMessage() {
        return "Enter your trading parameters and click CALCULATE to see your MAXIMUM position size based on the input risk metrics.\n" +
                "─────────────────────────────\n" +
                "Note: This is a risk calculation, not a trading recommendation.";
    }
}