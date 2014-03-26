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
    
    /**
     * Constructor - used constructor overloading to quickly populate variables.
     * @param name the name of the variable
     * @param type the type of variable (boolean, int, String, Object, etc.)
     */
    public VariableProperty(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    /**
     * get the type this parameter is.
     * @return string this parameters type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * get the variable name of this variable.
     * @return string name of the variable.
     */
    public String getName() {
        return this.name;
    }
    
}
