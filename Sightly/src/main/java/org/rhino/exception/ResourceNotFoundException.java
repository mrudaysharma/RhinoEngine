package org.rhino.exception;

/**
 *
 * @author u.sharma
 */
public class ResourceNotFoundException extends ProjectException {

    public ResourceNotFoundException(String message, String methodName, String className) {
        this.message = message;
        this.methodName = methodName;
        this.className = className;
    }

    public ResourceNotFoundException() {
    }

}
