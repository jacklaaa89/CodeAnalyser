package org.codeanalyser.core.analyser;

import java.io.File;
import java.util.ArrayList;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.output.NoResultsDefinedException;
import org.codeanalyser.core.output.OutputGenerator;
import org.codeanalyser.core.output.OverallResult;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.language.SyntaxErrorAdapter;
import org.codeanalyser.language.SyntaxErrorException;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.Result;

/**
 * This class is the one that analyses the file/directory that is the input. It
 * determines the supported listener, parser and lexer from a single file and
 * then walks that tree notifying the listener when events occur.
 *
 * @author Jack Timblin - U1051575
 * @version 1.1 - updated analyse to use OverallResult instead of Result for
 * output generation.
 */
public class Analyser {

    private final File sourceCodeLocation;
    private final ArrayList<FileAnalyser> filesToAnalyse;
    private OutputGenerator output;
    private AnalyserResult result;

    /**
     * initialises the analyser object with a file/directory location.
     *
     * @param sourceCodeLocation the file or directory to analyse.
     * @param outputLocation the location to save output files to.
     * @throws AnalyserException if the given source code location does not
     * exist.
     */
    public Analyser(String sourceCodeLocation, String outputLocation) throws AnalyserException {
        this.sourceCodeLocation = new File(sourceCodeLocation);
        this.filesToAnalyse = new ArrayList<FileAnalyser>();
        if (!this.sourceCodeLocation.exists()) {
            throw new AnalyserException("file/directory provided does not exist.");
        }
        this.output = new OutputGenerator(outputLocation);
        determineFiles(this.sourceCodeLocation);
        Application.getLogger().log("Found " + this.filesToAnalyse.size() + " Files To Analyse in: " + this.sourceCodeLocation.getAbsolutePath());
        this.result = new AnalyserResult(this.filesToAnalyse);
    }

    /**
     * goes through the sourceCodeLocation and grabs all the of the files to
     * analyse.
     *
     * @param file the current file being determined.
     */
    private void determineFiles(File file) {
        if (file.isDirectory()) {
            for (File t : file.listFiles()) {
                if (t.isDirectory()) {
                    determineFiles(t);
                } else {
                    this.filesToAnalyse.add(new FileAnalyser(t.getAbsolutePath()));
                }
            }
        } else {
            this.filesToAnalyse.add(new FileAnalyser(file.getAbsolutePath()));
        }
    }

    /**
     * analyses the files using a strict source language.
     *
     * @param forcedLanguage the language to force files to be.
     */
    public void analyse(String forcedLanguage) {
        for (FileAnalyser file : this.filesToAnalyse) {
            file.setForcedLanguage(forcedLanguage);
        }
        this.analyse();
    }
    
    /**
     * gets the overall result from the analyser.
     * Obviously this is only relevant AFTER the analysis
     * has taken place or analyse() has been called.
     * @return the overall result.
     */
    public AnalyserResult getResults() {
        return this.result;
    }

    /**
     * Analyses the source location provided in the constructor.
     */
    public void analyse() {
        for (FileAnalyser file : this.filesToAnalyse) {
            try {
                Application.getLogger().log("Started Analysing File: " + file.getAbsolutePath());
                //generate parse tree from source file.
                SyntaxErrorAdapter ea = new SyntaxErrorAdapter(file);

                Lexer lexer = file.getSupportedLexer();
                
                //remove default error handlers and add my own custom one.
                lexer.removeErrorListeners();
                lexer.addErrorListener(ea);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ParserInterface parser = file.getSupportedParser(tokens);
                
                //same with the parser, sort the error listeners.
                parser.removeErrorListeners();
                parser.addErrorListener(ea);
                ParserRuleContext tree = parser.compilationUnit();

                //check to see if any syntaxical errors occured.
                if (!ea.getSyntaxErrors().isEmpty()) {
                    this.result.incrementNoOfSyntaxErrorFiles();
                    for (String er : ea.getSyntaxErrors()) {
                        this.result.addSyntaxError(er);
                    }
                    throw new SyntaxErrorException("A Syntax Error Occured Parsing File: " + file.getAbsolutePath());
                }

                ParseTreeListener listener = file.getSupportedListener();
                ParseTreeWalker walker = new ParseTreeWalker();

                //walk the parse tree calling methods in the metrics.
                walker.walk(listener, tree);
                
                ArrayList<Result> re = ((ListenerInterface) listener).getResults();
                //gather the results from the analysis.
                OverallResult res = new OverallResult(
                        re,
                        file.getAbsolutePath()
                );

                //add this to result array.
                this.result.addResult(res);

                //call destroy on metrics.
                ((ListenerInterface) listener).destroy();

            } catch (FileAnalyser.UnsupportedLanguageException e) {
                this.result.addUnsupportedFile(file.getName());
                Application.getLogger().log(e);
            } catch (NoResultsDefinedException e) {
                Application.getLogger().log(e);
            } catch (SyntaxErrorException e) {
                Application.getLogger().log(e);
            } catch (InvalidResultException e) {
                Application.getLogger().log(e);
            }
        }

        try {
            Application.getLogger().log("Generating Output");
            //render the output for this analysis.
            this.output.generateOutput(this.result);
        } catch (Exception e) {
            Application.getLogger().log(e);
        }
    }

}
