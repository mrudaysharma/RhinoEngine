package org.rhino.exception;

/**
 *
 * @author u.sharma
 */
public class HTMLIncludedClassNotFound extends ProjectException {

    public HTMLIncludedClassNotFound(String message, String methodName, String className) {
        this.message = message;
        this.methodName = methodName;
        this.className = className;
    }

    public HTMLIncludedClassNotFound() {
    }

}
