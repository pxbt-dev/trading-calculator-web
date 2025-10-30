package com.pxbtdev.tradingcalculatorweb.dto;

import java.time.Instant;

public class ErrorResponseDto {
    private String error;
    private String timestamp;

    public ErrorResponseDto(String error) {
        this.error = error;
        this.timestamp = Instant.now().toString();
    }

    // Getters and setters
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

}