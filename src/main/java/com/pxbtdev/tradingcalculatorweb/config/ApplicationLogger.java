package com.pxbtdev.tradingcalculatorweb.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple JSON logger that doesn't depend on external Logbook classes
 * Provides structured logging in JSON format
 */
public class ApplicationLogger {
    private final String loggerName;

    public ApplicationLogger(Class<?> clazz) {
        this.loggerName = clazz.getSimpleName();
    }

    public void info(String message, Object... args) {
        log("INFO", message, args);
    }

    public void warn(String message, Object... args) {
        log("WARN", message, args);
    }

    public void error(String message, Object... args) {
        log("ERROR", message, args);
    }

    public void debug(String message, Object... args) {
        log("DEBUG", message, args);
    }

    public void trace(String message, Object... args) {
        log("TRACE", message, args);
    }

    private void log(String level, String message, Object... args) {
        try {
            Map<String, Object> context = new HashMap<>();

            // Process key-value pairs from args
            if (args.length > 0) {
                for (int i = 0; i < args.length; i += 2) {
                    if (i + 1 < args.length && args[i] instanceof String) {
                        context.put((String) args[i], args[i + 1]);
                    }
                }
            }

            String jsonLog = LoggingConfig.createJsonLog(level, loggerName, message, context);
            System.out.println(jsonLog);

        } catch (Exception e) {
            System.err.println("Logging error: " + e.getMessage());
        }
    }
}