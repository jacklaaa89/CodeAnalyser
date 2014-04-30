package org.codeanalyser.metric.vmc;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import java.util.ArrayList;
import java.util.List;
import org.codeanalyser.core.Application;

/**
 * Used to detect the language of some text. This is mainly used to determine if
 * variable names are considered to be written in English.
 * @version 1.1 - now utilizes an online API that is used to detect natural languages.
 * This more accurate than using static files as for one it is constantly being updated.
 * The old offline way is still used as a backup if the user has not got a internet connection.
 * @author Jack Timblin - U1051575
 */
public class LanguageDetect {

    private Detector detector;

    /**
     * Initialises the LanguageDetect Object, loads the language files.
     *
     * @throws LanguageDetect.LanguageDetectionException
     */
    public LanguageDetect() throws LanguageDetectionException {
        DetectLanguage.apiKey = "24c69c6ca861f7f1f3af61c1afcab223";
        try {
            DetectorFactory.loadProfile(Application.getSystemPath() + "antlr/profiles");
        } catch (LangDetectException e) {
            throw new LanguageDetectionException(e.getMessage());
        }
    }

    /**
     * attempts to determine the language of some written text.
     *
     * @param text the text to determine the language for.
     * @return the string language code that was determined or an empty string
     * if a language could not be determined.
     * @throws LanguageDetect.LanguageDetectionException if we could not create
     * a detector instance.
     */
    public Detection detectLanguage(String text) throws LanguageDetectionException {
        Detection r = new Detection();
        try {    
            if (Application.hasInternetConnection()) {
                List<Result> detected = DetectLanguage.detect(text);
                if (!detected.isEmpty()) {
                    Result result = detected.get(0);
                    r.setConfidence(result.confidence);
                    r.setDetectedLanguage(result.language);
                }
                return r;
            } else {
                detector = DetectorFactory.create();
                detector.append(text);
                ArrayList<Language> langs = detector.getProbabilities();
                if(!langs.isEmpty()) {
                    Language l = langs.get(0);
                    r.setConfidence(l.prob);
                    r.setDetectedLanguage(l.lang);
                }
            }
        } catch (Exception e) {
            throw new LanguageDetectionException(e.getMessage());
        }
        return r;
    }

    /**
     * Simple Exception class to handle errors from the LanguageDetect class.
     *
     * @author Jack Timblin - U1051575
     */
    public class LanguageDetectionException extends Exception {

        /**
         * constructor - initialises new LanguageDetectionException
         *
         * @param message the message to pass to the exception.
         */
        public LanguageDetectionException(String message) {
            super(message);
        }
    }

}
