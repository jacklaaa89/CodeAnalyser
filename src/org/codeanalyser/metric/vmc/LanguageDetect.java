package org.codeanalyser.metric.vmc;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

/**
 * Used to detect the language of some text. This is mainly used
 * to determine if variable names are considered to be written in English. 
 * @author Jack Timblin - U1051575
 */
public class LanguageDetect {
    
    private Detector detector;
    
    /**
     * Initialises the LanguageDetect Object, loads the language files.
     * @throws LanguageDetect.LanguageDetectionException 
     */
    public LanguageDetect() throws LanguageDetectionException {
        try {
            DetectorFactory.loadProfile("antlr/profiles");
        } catch(LangDetectException e) {
            throw new LanguageDetectionException(e.getMessage());
        }
    }
    
    /**
     * attempts to determine the language of some written text.
     * @param text the text to determine the language for.
     * @return the string language code that was determined or an empty string
     * if a language could not be determined.
     * @throws LanguageDetect.LanguageDetectionException if we could not create a detector instance. 
     */
    public String detectLanguage(String text) throws LanguageDetectionException {
        try {
            detector = DetectorFactory.create();
        } catch (LangDetectException e) {
            throw new LanguageDetectionException("Could not initialise detector");
        }
        try {
            detector.append(text);
            return detector.detect();
        } catch (LangDetectException e) {
            throw new LanguageDetectionException(e.getMessage());
        }
    }
    
    /**
     * Simple Exception class to handle errors from the LanguageDetect class.
     * @author Jack Timblin - U1051575
     */
    public class LanguageDetectionException extends Exception  {
        /**
         * constructor - initialises new LanguageDetectionException
         * @param message the message to pass to the exception.
         */
        public LanguageDetectionException(String message) {
            super(message);
        }
    }
    
}
