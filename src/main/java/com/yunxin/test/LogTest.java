package com.yunxin.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static Logger logger = LoggerFactory.getLogger(LogTest.class.getName());
    public static void main(String[] args) {
        logger.info("xxx");
    }
}
