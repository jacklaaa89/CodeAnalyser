/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.core.analyser;

/**
     * Exception class used when any of the language dependant Objects 
     * are attempted to be accessed but the language is not supported by the
     * system, i.e it does not have a generated Parser/Lexer from a grammar.
     * 
     * @author Jack Timblin - U1051575
     */
    public class UnsupportedLanguageException extends Exception {
        
        /**
         * initialises a new UnsupportedLanguageException
         * @param message the message to pass to the Exception
         */
        public UnsupportedLanguageException(String message) {
            super(message);
        }
    }
