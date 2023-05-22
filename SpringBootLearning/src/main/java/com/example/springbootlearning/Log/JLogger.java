package com.example.springbootlearning.Log;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class JLogger {
    private static final Logger logger = LoggerFactory.getLogger(JLogger.class);

    private JLogger(){}

    public static void info(String msg){
        JLogger.logger.info(msg);
    }

    public static void error(String msg){
        JLogger.logger.error(msg);
    }

    public static void logException(Throwable t){
        JLogger.logger.error(t.getLocalizedMessage(), t);
    }
}
