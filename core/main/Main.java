/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 */
public class Main {
    
    public static void main(String[] args) {
        try {
            LanguageHelper helper = new LanguageHelper();
            helper.initLanguages();
        } catch (LanguageHelper.FileException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LanguageHelper.AntlrException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(LanguageHelper.FileParseException ex) {
            
        }
    }
    
}
