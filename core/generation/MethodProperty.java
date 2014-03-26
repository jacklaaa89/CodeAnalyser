/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generation;

/**
 *
 * @author Jack
 */
public class MethodProperty {
    
    private String name;
    private String context;
    
    public MethodProperty(String name, String context) {
        this.context = context;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getContext() {
        return this.context;
    }
    
}
