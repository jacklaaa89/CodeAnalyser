package org.codeanalyser.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import org.codeanalyser.core.generation.ClassGeneration;

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
     * @throws org.codeanalyser.core.LanguageHelper.FileException
     * @throws AntlrException
     * @throws InterruptedException 
     */
    public LanguageHelper() throws FileException, AntlrException,
            InterruptedException {

        //determine if this is windows or unix, as this uses bash commands
        //which are different from command prompt commands.
        
        //this is the only class that has to be tailored to be used on different
        //platforms.
        String pathStart = System.getProperty("user.dir") + "/antlr/";
        arguments = (System.getProperty("os.name").startsWith("Windows"))
                //command | arguments | error term 
                ? new String[]{"cmd", "/c",
                    //antlr location
                    pathStart + "antlr4.bat", "'antlr4' is not recognized", 
                    //ant command
                    "javac"
                }
                : new String[]{"bash", "-c",
                    pathStart + "antlr4.sh", "",
                    "javac"
                };

        //open grammar file.  
        grammars = new File("grammars");
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
     * @throws org.codeanalyser.core.LanguageHelper.FileException
     * @throws org.codeanalyser.core.LanguageHelper.FileParseException
     * @throws AntlrException
     * @throws InterruptedException 
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
                File grammarPackage = new File("src/org/codeanalyser/language/" + grammarName.toLowerCase());
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
                    File javaParser = new File("src/org/codeanalyser/language/" + grammarName + "/" + grammarName + "Parser.java");
                    if (!javaParser.exists()) {
                        throw new AntlrException("Grammar: '" + grammarName + "' was not generated correctly");
                    }
                    
                    try {
                    //add the package declaration to new java files.
                        for(File javaFile : grammarPackage.listFiles()) {
                            if(javaFile.getName().endsWith(".java")) {
                                RandomAccessFile f = new RandomAccessFile(javaFile, "rw");
                                f.seek(0); // to the beginning
                                String packageName = "package org.codeanalyser.language." + grammarName.toLowerCase() + ";\n//";
                                f.write(packageName.getBytes());
                                f.close();
                                //if this file is the generated parser file, make it implement ParserInterface.
                                if(javaFile.getName().startsWith(grammarName+"Parser")) {
                                    this.updateParserClass(javaFile, grammarName);
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        throw new FileException("The requested Java file could not be found.");
                    } catch (IOException e) {
                        throw new FileException("Could not write to java file.");
                    }
                    
                    //generate BaseListener class so that metrics can be invoked.
                    try {
                        System.out.println("Generating BaseListener for the grammar: " + grammarName);
                        ClassGeneration.generateBaseListener(grammarName);
                    } catch (IOException e) {
                        throw new FileException("Could not generate BaseListener for Grammar: "+grammarName);
                    }
                    
                    System.out.println("Compiling new Grammar/Lexer Files for Grammar: " + grammarName);
                    
                    //compile new classes and put them in build/classes.
                    File bGrammarFile = new File("build/classes/org/codeanalyser/language/"+grammarName.toLowerCase());
                    if(!bGrammarFile.exists()) {
                        if(!bGrammarFile.mkdirs()) {
                            throw new FileException("Could not make build directories.");
                        }
                        ProcessBuilder compile = new ProcessBuilder(
                                arguments[0], arguments[1], arguments[4] +
                                        " -sourcepath src/ -classpath antlr/*.jar " +
                                        "-d " + bGrammarFile.getAbsolutePath() + " " + grammarPackage.getAbsolutePath()+"/*.java"
                        );
                        
                        compile.redirectErrorStream(true);
                        
                        try {
                            Process p = compile.start();
                            p.waitFor();
                        } catch (IOException e) {
                            throw new AntlrException("Could not compile new Antlr files.");
                        }
                    }
                    
                    System.out.println("Completed Building Parser/Lexer for grammar: " + grammarName);
                    
                }
            }
        }
    }
    
    /**
     * updates a generated Parser class to make it implement ParserInterface
     * so that it can be used generically in the analyser regardless of language.
     * @param parserClass the file object of the parser class.
     * @param grammarName the name of the grammar.
     * @throws org.codeanalyser.core.LanguageHelper.FileException 
     */
    private void updateParserClass(File parserClass, String grammarName)
        throws FileException
    {
        try {
            String fileName = parserClass.getAbsolutePath();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".temp")));
            BufferedReader r = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = r.readLine()) != null) {
                if (line.startsWith("public class")) {
                    //either antlr or java is not installed.
                    line = "public class " + grammarName + "Parser extends Parser implements ParserInterface {";
                    
                } else if(line.startsWith("package")) {
                    line = line + "\nimport org.codeanalyser.language.ParserInterface;";
                }
                writer.println(line);
            }
            r.close();
            writer.close();
            
            File real = new File(fileName);
            real.delete();
            new File(fileName+".temp").renameTo(real);
        } catch (IOException e) {
            throw new FileException("Could not modify Parser Class");
        }
    }

    /**
     * Thrown when any errors occurs to do with opening or manipulating files.
     * @author Jack Timblin - U1051575
     */
    public class FileException extends Exception {

        public FileException(String message) {
            super(message);
        }

    }

    /**
     * Thrown when a file cannot be parsed correctly.
     * @author Jack Timblin - U1051575
     */
    public class FileParseException extends Exception {

        public FileParseException(String message) {
            super(message);
        }

    }
}
