package org.codeanalyser.core.output;

/**
 * Simple Exception class for when Output IO Exceptions occur.
 * @author Jack Timblin - U1051575
 */
public class TemplateNotFoundException extends Exception {
    public TemplateNotFoundException(String message) {
        super(message);
    }
}
