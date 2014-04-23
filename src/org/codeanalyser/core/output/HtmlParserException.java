package org.codeanalyser.core.output;

/**
 * Exception for when the HTML code passed from metrics which is to be injected
 * into the output template cannot be parsed correctly, or has invalid or illegal
 * HTML tags in.
 * @author Jack Timblin - U1051575
 */
public class HtmlParserException extends Exception {
    
    /**
     * initialises a new HtmlParserException
     * @param message the message to pass to the Exception
     */
    public HtmlParserException(String message) {
        super(message);
    }
    
}
