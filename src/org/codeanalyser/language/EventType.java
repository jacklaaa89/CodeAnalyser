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
    ENTER_METHOD_BODY,
    /**
     * when the parser find a constructor declaration.
     */
    ENTER_CONSTRUCTOR_DECLARATION,
    /**
     * when the parser finds a variable name.
     */
    ENTER_VARIABLE_DECLARATOR_ID,
    /**
     * when the parser enters a statement.
     */
    ENTER_STATEMENT,
    /**
     * when the parser enters a switch block statement.
     */
    ENTER_SWITCH_BLOCK_STATEMENT_GROUP
}
