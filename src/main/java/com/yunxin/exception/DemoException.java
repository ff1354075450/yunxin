package com.yunxin.exception;

import com.ff.enums.StudentEnum;

/**
 * spring 只接收运行期异常
 */
public class DemoException extends RuntimeException {
    public DemoException() {
    }

    public DemoException(StudentEnum studentEnum) {
       int state = studentEnum.getState();
       String stateInfo = studentEnum.getStateInfo();
    }

    public DemoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoException(Throwable cause) {
        super(cause);
    }

    public DemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
