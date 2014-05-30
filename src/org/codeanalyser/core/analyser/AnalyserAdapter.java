package org.codeanalyser.core.analyser;

import java.util.ArrayList;
import org.codeanalyser.core.output.OverallResult;
import org.codeanalyser.language.SyntaxErrorException;
import org.codeanalyser.metric.ParserInfo;

/**
 * An adapter to use to support implementing only when events are required to be notified for. 
 * @author Jack Timblin - U1051575
 */
public abstract class AnalyserAdapter implements AnalyserListener {

    @Override
    public void onDeterminedFiles(ArrayList<String> determinedFiles, int amountOfFilesDetermined) {
    }

    @Override
    public void onStartAnalysis() {
    }

    @Override
    public void onStartAnalysingFile(String fileLocation) {
    }

    @Override
    public void onCompleteParsingFile(String fileLocation, ParserInfo info) {
    }

    @Override
    public void onDeterminedResults(String fileLocation, OverallResult result) {
    }

    @Override
    public void onUnsupportedLanguageFound(UnsupportedLanguageException exception, String fileLocation, ArrayList<String> unsupportedFiles) {
    }

    @Override
    public void onSyntaxErrorOccurred(SyntaxErrorException exception, String fileLocation, int amountOfFilesWithSyntaxError, ArrayList<String> syntaxErrors) {
    }

    @Override
    public void onFinishAnalysingFile(String fileLocation) {
    }

    @Override
    public boolean onGenerateOutput(AnalyserResult result) {
        return true;
    }

    @Override
    public void onCompleteAnalysis() {
    }
    
}
