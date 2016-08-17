package org.computelab.crypto;

public final class AlgorithmMissingException extends RuntimeException {

    private static final long serialVersionUID = -4744637328792077686L;

    public AlgorithmMissingException(Throwable cause) {
        super(cause);
    }

    public AlgorithmMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
