package org.codeanalyser.core.analyser;

import java.util.ArrayList;
import org.codeanalyser.core.output.OverallResult;
import org.codeanalyser.language.SyntaxErrorException;
import org.codeanalyser.metric.ParserInfo;

/**
 * An interface which can be used to be notified for Analyser Events.
 * @author Jack Timblin - U1051575
 */
public interface AnalyserListener {
    /**
     * triggered when the files to be analysed has been determined by the analyser.
     * @param determinedFiles the absolute paths of the files to be analysed.
     * @param amountOfFilesDetermined the amount of files to be analysed.
     */
    public void onDeterminedFiles(ArrayList<String> determinedFiles, int amountOfFilesDetermined);
    /**
     * triggered when the analyser first starts analysis.
     */
    public void onStartAnalysis();
    /**
     * triggered when the analyser starts its attempt to analyse a file.
     * @param fileLocation the absolute path of the file.
     */
    public void onStartAnalysingFile(String fileLocation);
    /**
     * triggered when the analyser completes parsing a file.
     * @param fileLocation the absolute path of the file.
     * @param info the information the system collected about the file
     * including an instance of the Parser and Lexer that was used to parse the file.
     */
    public void onCompleteParsingFile(String fileLocation, ParserInfo info);
    /**
     * triggered when the analyser has gathered the metric results for a file.
     * @param fileLocation the absolute path of the file.
     * @param result the overall result from the metrics for that particular file.
     */
    public void onDeterminedResults(String fileLocation, OverallResult result);
    /**
     * triggered when the file currently being analysed is unsupported by the application.
     * @param exception the exception that was thrown.
     * @param fileLocation the absolute path of the file.
     * @param unsupportedFiles an updated list of the unsupported files found up to this point.
     */
    public void onUnsupportedLanguageFound(UnsupportedLanguageException exception, String fileLocation,
            ArrayList<String> unsupportedFiles);
    /**
     * triggered when the file currently being analysed has syntax errors.
     * @param exception the exception that was thrown.
     * @param fileLocation the absolute path of this file.
     * @param amountOfFilesWithSyntaxError the updated amount of files that have had syntax errors to this point.
     * @param syntaxErrors the updated list of syntax error messages that have been logged to this point.
     */
    public void onSyntaxErrorOccurred(SyntaxErrorException exception, String fileLocation,
            int amountOfFilesWithSyntaxError, ArrayList<String> syntaxErrors);
    /**
     * triggered when the analyser has finished its attempt to analyse the file, this
     * event gets triggered regardless if the file was successfully analysed or not.
     * @param fileLocation the absolute path of the file.
     */
    public void onFinishAnalysingFile(String fileLocation);
    /**
     * triggered when the analyser is ready to generate the output.
     * @param result the overall result of the analysis for all files.
     * @return TRUE to use the analysers output generator, or FALSE if 
     * you will be handling output manually.
     */
    public boolean onGenerateOutput(AnalyserResult result);
    /**
     * triggered when the analyser has completely finished analysis on all files.
     */
    public void onCompleteAnalysis();
}
