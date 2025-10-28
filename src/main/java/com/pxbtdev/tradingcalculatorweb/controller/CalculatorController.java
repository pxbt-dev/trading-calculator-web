package com.pxbtdev.tradingcalculatorweb.controller;

import com.pxbtdev.tradingcalculatorweb.model.TradeParameters;
import com.pxbtdev.tradingcalculatorweb.model.PositionResult;
import com.pxbtdev.tradingcalculatorweb.service.PositionSizeService;
import com.pxbtdev.tradingcalculatorweb.service.RiskAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class CalculatorController {

    private final PositionSizeService positionService;
    private final RiskAnalysisService riskService;
    private final DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
    private final DecimalFormat percentFormat = new DecimalFormat("0.##%");
    private final DecimalFormat numberFormat = new DecimalFormat("#,###.##");

    public CalculatorController() {
        this.positionService = new PositionSizeService();
        this.riskService = new RiskAnalysisService();
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        // Only create new TradeParameters if not already set from calculation
        if (!model.containsAttribute("tradeParameters")) {
            model.addAttribute("tradeParameters", new TradeParameters());
        }
        return "index";
    }

    @PostMapping("/calculate")
    public String calculatePosition(
            @RequestParam double accountSize,
            @RequestParam double riskDollars,
            @RequestParam double entryPrice,
            @RequestParam double stopLoss,
            @RequestParam(required = false) Double targetPrice,
            Model model) {

        try {
            TradeParameters params = new TradeParameters(accountSize, riskDollars,
                    entryPrice, stopLoss,
                    targetPrice != null ? targetPrice : 0);

            PositionResult result = positionService.calculatePosition(params);

            // Format the values in the controller instead of the template
            model.addAttribute("params", params);
            model.addAttribute("result", result);
            model.addAttribute("hasTarget", targetPrice != null);

            // Add formatted strings
            model.addAttribute("formattedAccountSize", currencyFormat.format(params.getAccountSize()));
            model.addAttribute("formattedRiskDollars", currencyFormat.format(params.getRiskDollars()));
            model.addAttribute("formattedEntryPrice", currencyFormat.format(params.getEntryPrice()));
            model.addAttribute("formattedStopLoss", currencyFormat.format(params.getStopLossPrice()));
            model.addAttribute("formattedRiskPerUnit", currencyFormat.format(result.getRiskPerShare()));
            model.addAttribute("formattedPositionSize", currencyFormat.format(result.getTotalPositionSize()));
            model.addAttribute("formattedRiskPercentage", percentFormat.format(result.getRiskPercentage()));
            model.addAttribute("formattedUnitsToBuy", numberFormat.format(result.getUnitsToBuy()));

        } catch (Exception e) {
            model.addAttribute("error", "Invalid input: " + e.getMessage());
        }

        return "index";
    }
}