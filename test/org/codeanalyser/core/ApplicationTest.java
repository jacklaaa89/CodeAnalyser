/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.core;

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
public class ApplicationTest {

    /**
     * Test of getMetricsList method, of class Application.
     */
    @Test
    public void testGetMetricsList() {
        System.out.println("getMetricsList");
        ArrayList<String> result = Application.getMetricsList();
        if(result.isEmpty()) {
            fail("metricList should not be empty");
        }
        System.out.println(result);
    }

    /**
     * Test of getSupportedLanguages method, of class Application.
     */
    @Test
    public void testGetSupportedLanguages() {
        System.out.println("getSupportedLanguages");
        ArrayList<String> result = Application.getSupportedLanguages();
        if(result.isEmpty()) {
            fail("supportedLanguages should not be empty");
        }
        System.out.println(result);
    }
    
}
