package generation;

import java.io.File;
import main.Application;

/**
 * this class will be used to generate a listener for a 
 * given language which uses all of the installed metrics.
 * @author Jack
 */
public class ClassGeneration {
   
    public static void generateBaseListener(String grammarName) {
        
        //check that a listener has not already been generated.
        File listenerName = new File("CodeAnalyser/languages/"+grammarName+"/BaseListener.java");
        if(listenerName.exists()) {
            return;
        }
        
        //generate the java class.
        
    }
    
}
