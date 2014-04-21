package org.codeanalyser.metric.vmc;

import com.cybozu.labs.langdetect.DetectorFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.codeanalyser.core.generation.MethodProperty;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * This metric determines if the variables used in a file are acceptable, i.e
 * are CamelCased concatenations of English words.
 * @author Jack Timblin - U1051575
 */
public class VariableNamingConventions implements MetricInterface {
    
    private ArrayList<String> variableNames;
    private String fileName, sourceLang;
    private LanguageDetect detector;
    private int tr = 0, fl = 0;

    @Override
    public Result getResults() throws InvalidResultException {
        boolean s = false; //default to failed.
        String result = "";
        ArrayList<Variable> vn = new ArrayList<Variable>();
        try {
            for(String text : variableNames) {
                //push through the camel case splitter.
                String t = MethodProperty.splitCamelCase(text, " ");
                //remove any numbers that might corrupt the detection process.
                String lang = detector.detectLanguage(t);
                Variable v = new Variable();
                v.setVariableName(text);
                v.setDetectedLanguage(lang);
                vn.add(v);
            }
            //generate the result string to inject into the template.
            s = this.isMajorityAcceptable(vn);
            
            result = "<table><tr><td>VariableNamingConvertions Results: </td></tr>"
                    + "<tr><td>Number of Variables Considered Acceptable: "+tr+"</td></tr>"
                    + "<tr><td>Number of Variables Considered Unacceptable: "+fl+"</td></tr>"
                    + "<tr><td>Total Variables Found: "+variableNames.size()+"</td></tr></table>";
            
        } catch (LanguageDetect.LanguageDetectionException e) {
            System.out.println(e.getMessage());
        }
        
        return Result.newInstance(fileName, sourceLang, this.getClass().getSimpleName(), result, s);
    }

    @Override
    public void start(EventState state) {
        if(state.getEventType().equals("ENTER_VARIABLE_DECLARATOR_ID")) {
            if(state.getContext() != null) {
                variableNames.add(state.getContext().getText());
            }
        }
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
        this.fileName = initialInformation.getFileName();
        this.sourceLang = initialInformation.getSourceLanguage();
        this.variableNames = new ArrayList<String>();
        try {
            this.detector = new LanguageDetect();
        } catch (LanguageDetect.LanguageDetectionException e) {
            throw new MetricInitialisationException(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        DetectorFactory.clear();
    }
    
    /**
     * determines if the majority of variables used in a file are 
     * 'conventional' returns true if the majority of entries were true, or false 
     * otherwise.
     * @param vn the array of variable objects.
     * if the name was detected to be english.
     * @return true on success, false otherwise.
     */
    private boolean isMajorityAcceptable(ArrayList<Variable> vn) {
       boolean isSuccessful = false;
       
       if(!vn.isEmpty()) {
           for(Variable v : vn) {
               if(v.getDetectedLanguage().equalsIgnoreCase("en")) {
                   tr++;
               } else {
                   fl++;
               }
           }
           isSuccessful = (tr > fl);
       }
       return isSuccessful;
    }
    
}
