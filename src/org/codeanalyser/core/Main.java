package org.codeanalyser.core;

import java.io.File;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.codeanalyser.core.analyser.Analyser;
import org.codeanalyser.core.analyser.AnalyserException;
import org.codeanalyser.core.utils.Logger;

/**
 * The Applications Main class, uses Apache CLI to
 * allow for arguments passed from the VM.
 * @author Jack Timblin - U1051575
 */
public class Main {
    
    /**
     * runs the application
     * @param args the arguments from the VM.
     * @throws org.codeanalyser.core.ApplicationException if system files could
     * not be found.
     */
    public static void main(String[] args) throws ApplicationException {
        
        //check that we can find all of the required files.
        String[] files = {"antlr", "config"};
        for(String file : files) {
            File f = new File(Application.getSystemPath()+file);
            if(!f.exists()) {
                throw new ApplicationException("System file: " + f.getAbsolutePath() + " could not be located");
            }
        }

        //create the command line parser.
        CommandLineParser parser = new BasicParser();

        Options options = new Options();
        options.addOption("ug", "update-grammars", false, "updates the supported grammars.");
        options.addOption(OptionBuilder.withLongOpt("source")
                .withDescription("source file location to analyse")
                .hasArg()
                .create("s"));
        options.addOption(OptionBuilder.withLongOpt("output")
                .withDescription("output file location, if the format is default")
                .hasArg()
                .create("o"));
        options.addOption(OptionBuilder.withLongOpt("force-language")
                .withDescription("force language, no auto-language detection will take place.")
                .hasArg()
                .create("fl"));
        options.addOption(OptionBuilder.withLongOpt("format")
                .withDescription("The format to output the result, 'default' is where logging takes place and the output is generated to HTML files, 'JSON' pushes the output as JSON with no logging.")
                .hasArg()
                .create("f"));
        options.addOption("h", "help", false, "Displays this help message");

        try {
            CommandLine line = parser.parse(options, args);

            if (line.getArgList().isEmpty() || line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("analyser", options);
                return;
            }
            
            if(line.hasOption("f")) {
                Application.initInterface(line.getOptionValue("f"));
            }

            //determines the output source location.
            File file = new File("output");
            if (line.hasOption("o")) {
                file = new File(line.getOptionValue("o"));
                if (!file.exists()) {
                    Application.getLogger().log("Output Destination Provided Does not Exist");
                }
            } else {
                if (!file.exists()) {
                    file.mkdir();
                }
            }

            //check if ug has been passed.
            if (line.hasOption("ug")) {
                try {
                    //use the language helper to update grammar location.
                    LanguageHelper helper = new LanguageHelper();
                    helper.initLanguages();
                } catch (Exception e) {
                    Application.getLogger().log(e, Logger.DEFAULT_STREAM);
                }
            } else {

                try {

                    if (line.hasOption("s")) {
                        
                        Analyser analyser = new Analyser(line.getOptionValue("s"), file.getAbsolutePath());
                        
                        boolean force = line.hasOption("fl");
                        if (force) {
                            if (!Application.getSupportedLanguages().contains(line.getOptionValue("fl"))) {
                                Application.getLogger().log("The Supplied Forced Language is not Supported");
                                return;
                            }
                            
                            analyser.analyse(line.getOptionValue("fl"));
                        } else {
                            analyser.analyse();
                        }
                    } else {
                        HelpFormatter formatter = new HelpFormatter();
                        formatter.printHelp("analyser", options);
                    }
                } catch (AnalyserException ex) {
                    Application.getLogger().log(ex);
                }
            } 

        } catch (ParseException e) {
            Application.getLogger().log(e);
        }

    }

}
