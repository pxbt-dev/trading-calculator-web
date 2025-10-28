package com.pxbtdev.tradingcalculatorweb.config;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple JSON logging configuration for the trading calculator
 * No external dependencies needed - pure Java
 */
public class LoggingConfig {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ISO_INSTANT;

    public static void setupSystemLogging() {

        // Configure system properties for better console logging
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %6$s%n");
    }

    public static String createJsonLog(String level, String logger, String message, Map<String, Object> context) {
        Map<String, Object> logData = new HashMap<>();
        logData.put("timestamp", formatter.format(Instant.now()));
        logData.put("level", level);
        logData.put("logger", logger);
        logData.put("message", message);

        if (context != null && !context.isEmpty()) {
            logData.put("context", context);
        }

        return convertToJson(logData);
    }

    private static String convertToJson(Map<String, Object> data) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!first) json.append(",");
            json.append("\"").append(entry.getKey()).append("\":");

            Object value = entry.getValue();
            if (value instanceof String) {
                json.append("\"").append(escapeJson(value.toString())).append("\"");
            } else if (value instanceof Map) {
                json.append(convertToJson((Map<String, Object>) value));
            } else {
                json.append(value);
            }
            first = false;
        }
        json.append("}");
        return json.toString();
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}