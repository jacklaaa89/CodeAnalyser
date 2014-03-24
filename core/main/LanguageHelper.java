package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is used to check the grammar folder and determine whether their is
 * any new grammar files that need a parser generating for them.
 *
 * This will be determined by if there is a folder with the same name as the
 * grammar in the languages folder.
 *
 * This class will also use Antlr to generate parsers & lexers for any new
 * grammars.
 *
 * @author Jack Timblin - U1051575
 */
public class LanguageHelper {
    /**
     * the location of the grammars.
     */
    File grammars;
    /**
     * the arguments to pass to the system.
     */
    String[] arguments;
    
    /**
     * Initialises the LanguageHelper Object.
     * checks to see if the grammar file exists and that antlr is installed.
     * @throws main.LanguageHelper.FileException if the grammar directory could not be found.
     * @throws main.LanguageHelper.AntlrException if antlr is not installed.
     */
    public LanguageHelper() throws FileException, AntlrException {
        
        //determine if this is windows or unix, as this does bash commands
        //which have different command prompt commands.
        arguments = (System.getProperty("os.name").startsWith("Windows"))
                ? new String[]{"cmd.exe", "'antlr4' is not recognized"}
                : new String[]{"/usr/bin/bash", ""};

        //open grammar file.  
        grammars = new File("../../grammars");
        if (!grammars.exists() || !grammars.isDirectory()) {
            throw new FileException("Grammar directory does not exist.");
        }
        
        //check that antlr is available.
        ProcessBuilder process = new ProcessBuilder(
            arguments[0], "", "antlr4"
        );
        
        boolean isEnabled = true;
                    
        process.redirectErrorStream(false);
        
        //check the output.
        try {
            Process p = process.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = r.readLine()) != null) {
                if(line.startsWith(arguments[1])) {
                    //either antlr or java is not installed.
                    isEnabled = false;
                }
            }
        } catch (IOException e) {
            throw new AntlrException("Could not detect Antlr using cmd command 'antlr4'");
        }
        
        if(!isEnabled) {
            throw new AntlrException("Antlr or Java is not installed on the system.");
        }
        

    }
    
    /**
     * goes through all of the grammar files in the grammar directory
     * and puts them through Antlr if the parser and lexer files are not present.
     * @throws main.LanguageHelper.FileException
     * @throws main.LanguageHelper.FileParseException
     * @throws main.LanguageHelper.AntlrException 
     */
    public void initLanguages() throws FileException, FileParseException,
            AntlrException {

       //go through files in directory and 
        //see if there is any grammars that need initialising.
        for (File grammarFile : grammars.listFiles()) {
            if (grammarFile.getName().endsWith(".g4") || grammarFile.getName().endsWith(".G4")) {
                String grammarName = "";
                try (BufferedReader br = new BufferedReader(new FileReader(grammarFile))) {
                    for (String line; (line = br.readLine()) != null;) {
                        //see if this line contains the grammar name.
                        if (line.matches("(?i)(grammar )(.+?)(;)")) {
                            grammarName = line.replaceAll("(?i)(grammar )(.+?)(;)", "$2");
                            break;
                        }
                    }
                } catch (IOException e) {
                    throw new FileException("Could not read grammar file: " + grammarFile.getName());
                }

                if (grammarName.length() == 0) {
                    throw new FileParseException("Could not determine the name of the grammar in file: " + grammarFile.getName());
                }

                //check that this grammar has been defined.
                File grammarPackage = new File("../../languages/" + grammarName);
                if (!grammarPackage.exists()) {
                    System.out.println("Found new Grammar.");
                    //make new directory.
                    if (!grammarPackage.mkdir()) {
                        throw new FileException("Could not create folder for: " + grammarName);
                    }
                    //run Antlr using the grammar making the
                    //output location this new directory.
                    ProcessBuilder process = new ProcessBuilder(
                         arguments[0], "", "antlr4 "+grammarFile.getName()+" -o \"../../languages/"+grammarName+"\""
                    );
                    
                    process.redirectErrorStream(false);
                    try {
                        Process p = process.start();
                    } catch (IOException e) {
                        throw new AntlrException("Could not execute Antlr Command");
                    }
                }
            }
        }
    }
    
    /**
     * Thrown when any errors occurs to do with 
     * opening or manipulating files.
     */
    public class FileException extends Exception {

        public FileException(String message) {
            super(message);
        }

    }
    
    /**
     * Thrown when a file cannot be parsed correctly.
     */
    public class FileParseException extends Exception {

        public FileParseException(String message) {
            super(message);
        }

    }
    
    /**
     * thrown when an Antlr error occurs.
     */
    public class AntlrException extends Exception {

        public AntlrException(String message) {
            super(message);
        }

    }

}
