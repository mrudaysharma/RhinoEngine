package org.rhino.exception;

/**
 *
 * @author u.sharma
 */
public class FactoryProductionException extends ProjectException {

    public FactoryProductionException(String message, String methodName, String className) {
        this.message = message;
        this.methodName = methodName;
        this.className = className;
    }

}
