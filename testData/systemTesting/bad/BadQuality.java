/**
 * This class would be considered to be that of bad quality in 
 * accordance to the metrics that I have defined.
 * This class would essentially just be a random set of methods, with
 * a low amount of commenting.
 * @author Jack Timblin - U1051575
 */
public class BadQuality {
    
    private String agvf;
    private int fjdfn;
    private double vfgfg;
    
    
    public BadQuality(String agvf, int fjdfn) {
        this.agvf = agvf;
        this.fjdfn = fjdfn;
    }
    
    
    public void setAnotherVariable(int fjdfn, double frdgdfgt,
            char gfijgfvgfv, Object o, double vfgfg, boolean istrue) {
        switch(fjdfn) {
            case 1 : 
                if(fjdfn > 10) {
                    if(fjdfn == 9) {
                        while(true) {
                            System.out.println("Printing While True");
                            if(vfgfg == 10) {
                                this.fjdfn = 1;
                            }
                            this.fjdfn = -1;
                        }
                    }
                } else if(fjdfn < 10) {
                    this.fjdfn = 10;
                }
                break;
            case 2 :
                for(int i = 0; i < fjdfn; i++) {
                    this.fjdfn = 12;
                }
                break;
            default : 
                this.fjdfn = fjdfn;
                break;
        }
        String[] gfAl = {"ONE", "TWO", "THREE"};
        for(String vdfd : gfAl) {
            if(vdfd.equals("ONE")) {
                break;
            } else if(vdfd.equals("TWO")) {
                continue;
            } else {
                this.agvf = vdfd;
            }
        }
        if(this.fjdfn < 1) {
            if(this.fjdfn > 0) {
                this.agvf = "THREE";
            }
        }
    }
}
