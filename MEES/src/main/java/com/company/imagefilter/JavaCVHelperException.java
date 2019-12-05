package com.company.imagefilter;

public class JavaCVHelperException extends Exception {
    /**
     * If Program Argument cause a eventual error , it  will catch the problem
     * @param message Error message
     */
    public JavaCVHelperException(String message) {

        super(message);
    }

    /**
     * If Program Argument cause a eventual error , it  will catch the problem and will indicate the cause
     * @param message Error Message
     * @param cause Cause of Error
     */
    public JavaCVHelperException(String message, Throwable cause) {

        super(message, cause);
    }
}
