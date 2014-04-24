/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.core.analyser;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class LanguageDetectTest {
    
    private LanguageDetect detect;
    private final String SOURCE = "hello";
    private final String EXTENSION = "hello";
    private final int SUPPORTED_COUNT = 247;
    private final String UNSUPPORTED_EXTENSION = "unsupported";
    
    @Before
    public void setUp() {
        detect = new LanguageDetect();
    }
    
    @After
    public void tearDown() {
        detect = null;
    }

    /**
     * Test of getAllDetectableLanguages method, of class LanguageDetect.
     */
    @Test
    public void testGetAllDetectableLanguages() {
        System.out.println("getAllDetectableLanguages");
        ArrayList<Language> result = detect.getAllDetectableLanguages();
        assertEquals(SUPPORTED_COUNT, result.size());
    }

    /**
     * Test of getSupportedLanguage method, of class LanguageDetect.
     */
    @Test
    public void testGetSupportedLanguage() {
        System.out.println("getSupportedLanguage");
        Language result = detect.getSupportedLanguage(EXTENSION);
        assertNotNull(result);
        assertEquals(SOURCE, result.getName());
        Language falseResult = detect.getSupportedLanguage(UNSUPPORTED_EXTENSION);
        assertNull(falseResult);
    }
    
}
