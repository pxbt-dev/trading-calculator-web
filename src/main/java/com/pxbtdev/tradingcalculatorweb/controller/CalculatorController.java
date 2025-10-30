package com.pxbtdev.tradingcalculatorweb.controller;

import com.pxbtdev.tradingcalculatorweb.dto.ErrorResponseDto;
import com.pxbtdev.tradingcalculatorweb.dto.TradeRequestDto;
import com.pxbtdev.tradingcalculatorweb.dto.TradeResponseDto;
import com.pxbtdev.tradingcalculatorweb.model.PositionResult;
import com.pxbtdev.tradingcalculatorweb.model.TradeParameters;
import com.pxbtdev.tradingcalculatorweb.service.PositionSizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Trading Calculator API", description = "REST API for position size calculations")
public class CalculatorController {

    private final PositionSizeService positionService;

    public CalculatorController() {
        this.positionService = new PositionSizeService();
    }

    @Operation(summary = "Calculate position size")
    @PostMapping("/calculate")
    public ResponseEntity<?> calculatePosition(@RequestBody TradeRequestDto request) {
        try {
            // Use helper method for clarity
            double targetPriceValue = getTargetPriceWithDefault(request.getTargetPrice());

            // Validate inputs
            validateRequest(request);

            TradeParameters params = createTradeParameters(request, targetPriceValue);
            PositionResult result = positionService.calculatePosition(params);

            return ResponseEntity.ok(new TradeResponseDto(result));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ErrorResponseDto("Calculation failed: " + e.getMessage()));
        }
    }

    private double getTargetPriceWithDefault(Double targetPrice) {
        return (targetPrice != null) ? targetPrice : 0.0;
    }

    private void validateRequest(TradeRequestDto request) {
        if (request.getAccountSize() <= 0) {
            throw new IllegalArgumentException("Account size must be positive");
        }
        if (request.getRiskDollars() <= 0) {
            throw new IllegalArgumentException("Risk amount must be positive");
        }
        if (request.getEntryPrice() <= 0) {
            throw new IllegalArgumentException("Entry price must be positive");
        }
        if (request.getStopLoss() <= 0) {
            throw new IllegalArgumentException("Stop loss must be positive");
        }
        if (request.getRiskDollars() > request.getAccountSize()) {
            throw new IllegalArgumentException("Risk amount cannot exceed account size");
        }
    }

    private TradeParameters createTradeParameters(TradeRequestDto request, double targetPrice) {
        return new TradeParameters(
                request.getAccountSize(),
                request.getRiskDollars(),
                request.getEntryPrice(),
                request.getStopLoss(),
                targetPrice
        );
    }
}