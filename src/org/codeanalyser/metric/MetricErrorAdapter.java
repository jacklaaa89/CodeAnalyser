package org.codeanalyser.metric;

import org.antlr.v4.runtime.Lexer;
import org.codeanalyser.core.analyser.UnsupportedLanguageException;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.language.SyntaxErrorException;

/**
 * An Error Adapter that can be used for metrics to customise how they handle error.
 * @author Jack Timblin - U1051575
 */
public interface MetricErrorAdapter {
    
    /**
     * called when a metric cannot be initialised correctly.
     * @param e the exception that was thrown
     * @param logger an instance of the logger.
     */
    public void onInitialisationError(MetricInitialisationException e, Logger logger);
    
    /**
     * called when the result retrieved from a metric was invalid.
     * @param e the exception that was thrown.
     * @param result the invalid result.
     * @param logger an instance of the logger.
     */
    public void onInvalidResultException(InvalidResultException e, Result result, Logger logger);
    
    /**
     * called when the parser/lexer reports that a syntax error occurred.
     * @param e the exception that was thrown.
     * @param fileName the absolute path of the file where the syntax error occurred.
     * @param parser the parser that was used to attempt to parse the file.
     * @param lexer the lexer that was used to attempt lexical analysis.
     * @param logger an instance of the logger.
     */
    public void onSyntaxErrorOccured(SyntaxErrorException e, String fileName,
            ParserInterface parser, Lexer lexer, Logger logger);
    
}
