package com.hajres.log4j2properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2PropertiesConf {
    private static Logger logger = LogManager.getLogger();
    public void performSomeTask() {
        logger.debug("This is a debug message");
        logger.info("This is a info message");
        logger.warn("This is a warn message");
        logger.error("This is a error message");
        logger.fatal("This is a fatal message");
    }
}
