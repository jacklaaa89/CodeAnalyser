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
public class VariableProperty {
    
    private String type;
    private String name;
    
    public VariableProperty(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
}
