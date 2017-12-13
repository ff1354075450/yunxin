package com.yunxin.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoggerFactory {
    private static final Log sysLogger;

    //mysql数据库日志
    private static final Log dbLogger;

    static {
        sysLogger = LogFactory.getLog("sysLogger");
        dbLogger = LogFactory.getLog("dbLogger");
    }

    public static Log getSysLogger() {
        return sysLogger;
    }

    public static Log getDbLogger(){
        return dbLogger;
    }

}
