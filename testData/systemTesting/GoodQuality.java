/**
 * This class would be considered to be that of good quality in 
 * accordance to the metrics that I have defined.
 * This class would essentially just be a random set of methods.
 * @author Jack Timblin - U1051575
 */
public class GoodQuality {
    
    private String one;
    private int two;
    
    /**
     * constructor.
     * @param one a string variable.
     * @param two an integer variable.
     */
    public GoodQuality(String one, int two) {
        this.one = one;
        this.two = two;
    }
    
    /**
     * sets another variable.
     * @param one another variable.
     */
    public void setAnotherVariable(int one) {
        switch(one) {
            case 1 : 
                two = 1;
                break;
            case 2 :
                two = 2;
                break;
            default : 
                two = one;
                break;
        }
    }
}
