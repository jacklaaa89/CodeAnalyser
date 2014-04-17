package org.codeanalyser.metric.vmc;

import com.cybozu.labs.langdetect.Language;
import java.util.ArrayList;
import org.codeanalyser.core.generation.MethodProperty;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 *
 * @author Jack
 */
public class VariableNamingConventions implements MetricInterface {
    
    private ArrayList<String> variableNames;
    private String fileName, sourceLang;
    private LanguageDetect detector;

    @Override
    public Result getResults() {
        
        try {
            for(String text : variableNames) {
                //push through the camel case splitter.
                String t = MethodProperty.splitCamelCase(text, " ");
                //remove any numbers that might corrupt the detection process.
                ArrayList<Language> languages = detector.detectLanguage(t.replaceAll("[A-Za-z]", ""));
                if(!languages.isEmpty()) {
                    //TODO - determine if the highest prob is english.
                }
            }
        } catch (LanguageDetect.LanguageDetectionException e) {
            System.out.println(e.getMessage());
        }
        
        return Result.newInstance(fileName, sourceLang, this.getClass().getSimpleName(), "", true);
    }

    @Override
    public void start(EventState state) {
        if(state.getEventType().equals(EventType.ENTER_VARIABLE_DECLARATOR_ID)) {
            if(state.getContext() != null) {
                variableNames.add(state.getContext().getText());
            }
        }
    }

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) throws MetricInitialisationException {
        this.fileName = fileLocation;
        this.sourceLang = sourceLanguage;
        this.variableNames = new ArrayList<String>();
        try {
            this.detector = new LanguageDetect();
        } catch (LanguageDetect.LanguageDetectionException e) {
            throw new MetricInitialisationException(e.getMessage());
        }
    }
    
}
