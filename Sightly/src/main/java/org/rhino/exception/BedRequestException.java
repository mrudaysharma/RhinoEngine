package org.rhino.exception;

/**
 *
 * @author u.sharma
 * If requested method parameters are null or empty then this get call as exception
 */
public class BedRequestException extends ProjectException {

    public BedRequestException(String message, String methodName, String className) {
        this.message = message;
        this.methodName = methodName;
        this.className = className;
    }

    public BedRequestException() {
    }

}
