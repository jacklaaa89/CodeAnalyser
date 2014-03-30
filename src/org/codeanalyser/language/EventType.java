package org.codeanalyser.language;

/**
 * Possible event types that can occur while
 * walking a parse tree using a BaseListener implementation.
 * @author Jack Timblin - U1051575
 */
public enum EventType {
    /**
     * when the parser finds a class declaration.
     */
    ENTER_CLASS_DECLARATION,
    /**
     * when the parser finds a method declaration.
     */
    ENTER_METHOD_DECLARATION,
    /**
     * when the parser enters a method body.
     */
    ENTER_METHOD_BODY
}
