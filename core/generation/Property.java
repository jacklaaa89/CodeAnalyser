/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generation;

import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class Property {
    
    private String source;
    private String packageName;
    private ArrayList<String> imports;
    private String listenerName;
    
    public Property(String packageName, ArrayList<String> imports, 
            String listenerName) {
        this.imports = imports;
        this.listenerName = listenerName;
        this.packageName = packageName;
        this.source = packageName;
    }
    
    public String getPackageName() {
        return this.packageName;
    }
    
    public ArrayList<String> getImportStatements() {
        return this.imports;
    }
    
    public String getListenerName() {
        return this.listenerName;
    }
    
    public String getSourceLanguage() {
        return this.source;
    }
    
}
