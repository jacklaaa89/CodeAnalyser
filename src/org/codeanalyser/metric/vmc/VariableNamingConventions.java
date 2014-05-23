package org.codeanalyser.metric.vmc;

import com.cybozu.labs.langdetect.DetectorFactory;
import java.util.ArrayList;
import org.codeanalyser.core.generation.MethodProperty;
import org.codeanalyser.core.utils.OutputInterface;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;
import org.json.simple.JSONObject;

/**
 * This metric determines if the variables used in a file are acceptable, i.e
 * are CamelCased concatenations of English words.
 * @version 1.1 - the validity of using single words to determine the language
 * was too low, this is because even if the word is gibberish, it still contains
 * english characters, but the confidence in this detection was always low.
 * 
 * the way round this was to generate a single string with all the variable names
 * init space seperated with 'Hello World' at the end, the 'Hello World' is to force
 * the detection to english but the quality of the variable names would increase the
 * confidence in this detection, giving us a more accurate result.
 * @author Jack Timblin - U1051575
 */
public class VariableNamingConventions implements MetricInterface {
    
    private ArrayList<String> variableNames;
    private LanguageDetect detector;

    @Override
    public Result getResults() throws InvalidResultException {
        try {
            //concatenate all of the variable names together.
            StringBuilder b = new StringBuilder();
            for(String text : variableNames) {
                //push through the camel case splitter.
                String t = MethodProperty.splitCamelCase(text, " ");
                b.append(t);
                b.append(" ");
            }
            final Detection lang = detector.detectLanguage(b.toString().trim() + " Hello World");
            final String reason = ((lang.getDetectedLanguage().equalsIgnoreCase("en")) &&
                    (lang.getConfidence() == 0.01)) 
                    ? "<tr><td>Reason For Failure: Confidence in Language Detection was too low.</td></tr>" 
                    : "";
            final String result = "<table><tr><td>VariableNamingConvertions Results: </td></tr>"
                    + "<tr><td>Determined Overall Language of Variable Names: "+lang.getDetectedLanguage()+"</td></tr>"
                    + reason
                    + "<tr><td>Total Variables Found: "+variableNames.size()+"</td></tr></table>";
            
            return Result.newInstance(this.getClass().getSimpleName(), new OutputInterface() {

                @Override
                public String toHTML() {
                    return result;
                }

                @Override
                public JSONObject toJSON() {
                    JSONObject o = new JSONObject();
                    o.put("determinedLanguage", lang.getDetectedLanguage());
                    o.put("totalVariablesFound", variableNames.size());
                    if(!reason.equals("")) {
                        o.put("reasonForFailure", reason);
                    }
                    return o;
                }
                
            },
            (lang.getConfidence() != 0.01) && (lang.getDetectedLanguage().equalsIgnoreCase("en")));
            
        } catch (LanguageDetect.LanguageDetectionException e) {
            throw new InvalidResultException(e.getMessage());
        }
    }

    @Override
    public void onParserEvent(EventState state) {
        if(state.getEventType().equals("ENTER_VARIABLE_DECLARATOR_ID")) {
            if(state.getContext() != null) {
                variableNames.add(state.getContext().getText());
            }
        }
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
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
    
}
