package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

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
     * Initialises the LanguageHelper Object. checks to see if the grammar directory
     * exists and that antlr is installed.
     *
     * @throws main.LanguageHelper.FileException if the grammar directory could
     * not be found.
     * @throws main.LanguageHelper.AntlrException if antlr is not installed.
     * @throws java.lang.InterruptedException
     */
    public LanguageHelper() throws FileException, AntlrException,
            InterruptedException {

        //determine if this is windows or unix, as this uses bash commands
        //which are different from command prompt commands.
        
        //this is the only class that has to be tailored to be used on different
        //platforms.
        String pathStart = System.getProperty("user.dir") + "/CodeAnalyser/antlr/";
        arguments = (System.getProperty("os.name").startsWith("Windows"))
                //command | arguments | error term 
                ? new String[]{"cmd", "/c",
                    //antlr location
                    pathStart + "antlr4.bat", "'antlr4' is not recognized", 
                    //javac command
                    "javac"
                }
                : new String[]{"bash", "-c",
                    pathStart + "antlr4.sh", "",
                    "javac"
                };

        //open grammar file.  
        grammars = new File("CodeAnalyser/grammars");
        if (!grammars.exists() || !grammars.isDirectory()) {
            throw new FileException("Grammar directory does not exist.");
        }

        boolean isEnabled = true;

        //check the output.
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    arguments[0], arguments[1], arguments[2]
            );
            builder.redirectErrorStream(false);
            Process p = builder.start();
            p.waitFor();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = r.readLine()) != null) {
                if (line.startsWith(arguments[3])) {
                    //either antlr or java is not installed.
                    isEnabled = false;
                    break; //only need to read the first line to determine error.
                }
            }
        } catch (IOException e) {
            throw new AntlrException("Could not detect Antlr using cmd command 'antlr4'");
        }

        if (!isEnabled) {
            throw new AntlrException("Antlr or Java is not installed on the system.");
        }

    }

    /**
     * goes through all of the grammar files in the grammar directory and puts
     * them through Antlr if the parser and lexer files are not present.
     * this method also compiles any newly created java files at runtime so that
     * they can be used immediately.
     *
     * @throws main.LanguageHelper.FileException
     * @throws main.LanguageHelper.FileParseException
     * @throws main.LanguageHelper.AntlrException
     * @throws java.lang.InterruptedException
     */
    public void initLanguages() throws FileException, FileParseException,
            AntlrException, InterruptedException {

        //go through files in directory and 
        //see if there is any grammars that need initialising.
        System.out.println("Checking for new grammars");
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
                File grammarPackage = new File("CodeAnalyser/languages/" + grammarName);
                if (!grammarPackage.exists()) {
                    System.out.println("Found new Grammar: '" + grammarName + "'. Generating Parser/Lexer");
                    //make new directory.
                    if (!grammarPackage.mkdir()) {
                        throw new FileException("Could not create directory for: " + grammarName);
                    }
                    //run Antlr using the grammar making the
                    //output location this new directory.
                    ProcessBuilder process = new ProcessBuilder(
                            arguments[0], arguments[1], arguments[2] + " " + grammarFile.getAbsolutePath() + " -o \"" + grammarPackage.getAbsolutePath() + "\" -visitor"
                    );

                    process.redirectErrorStream(true);
                    try {
                        Process p = process.start();
                        p.waitFor();
                    } catch (IOException e) {
                        throw new AntlrException("Could not execute Antlr Command");
                    }

                    //check that the new grammar was generated correctly.
                    File javaParser = new File("CodeAnalyser/languages/" + grammarName + "/" + grammarName + "Parser.java");
                    if (!javaParser.exists()) {
                        throw new AntlrException("Grammar: '" + grammarName + "' was not generated correctly");
                    }
                    
                    try {
                    //add the package declaration to new java files.
                        for(File javaFile : grammarPackage.listFiles()) {
                            if(javaFile.getName().endsWith(".java")) {
                                RandomAccessFile f = new RandomAccessFile(javaFile, "rw");
                                f.seek(0); // to the beginning
                                f.write("package Java;\n//".getBytes());
                                f.close();
                            }
                        }
                    } catch (FileNotFoundException e) {
                        throw new FileException("The requested Java file could not be found.");
                    } catch (IOException e) {
                        throw new FileException("Could not write to java file.");
                    }

                    //compile the grammar.
                    System.out.println("Compiling newly generated grammar: " + grammarName);
                    
                    ProcessBuilder builder = new ProcessBuilder(
                            arguments[0], arguments[1], arguments[4] + " " + grammarPackage.getAbsolutePath() + "/*.java"
                    );

                    builder.redirectErrorStream(true);

                    try {
                        Process pr = builder.start();
                        pr.waitFor();
                        StringBuilder b = new StringBuilder();
                        //get the output from console to check for errors.
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()))) {
                            for (String line; (line = br.readLine()) != null;) {
                                //see if this line contains the grammar name.
                                b.append(line);
                            }
                        } catch (IOException e) {
                            throw new FileException("Could not read grammar file: " + grammarFile.getName());
                        }
                        
                        if(b.toString().length() != 0) {
                            //an error occured.
                            throw new AntlrException(b.toString());
                        }
                    } catch (IOException e) {
                        throw new AntlrException("Could not compile new generated parser/lexer");
                    }
                }
            }
        }
    }

    /**
     * Thrown when any errors occurs to do with opening or manipulating files.
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
