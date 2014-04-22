/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.language;

import org.codeanalyser.core.analyser.AnalyserException;

/**
 *
 * @author jack
 */
public class SyntaxErrorException extends AnalyserException {
    
    public SyntaxErrorException(String message) {
        super(message);
    }
}
