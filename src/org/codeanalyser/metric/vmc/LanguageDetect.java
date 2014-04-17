package org.codeanalyser.metric.vmc;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class LanguageDetect {
    
    private Detector detector;
    
    public LanguageDetect() throws LanguageDetectionException {
        try {
            DetectorFactory.loadProfile("antlr/profiles");
            detector = DetectorFactory.create();
        } catch(LangDetectException e) {
            throw new LanguageDetectionException(e.getMessage());
        }
    }
    
    public String detectLanguage(String text) throws LanguageDetectionException {
        
        if(detector == null) {
            throw new LanguageDetectionException("Could not initialise detector");
        }
        try {
            detector.append(text);
            return detector.detect();
        } catch (LangDetectException e) {
            throw new LanguageDetectionException(e.getMessage());
        }
    }
    
    public class LanguageDetectionException extends Exception  {
        public LanguageDetectionException(String message) {
            super(message);
        }
    }
    
}
