
package edu.jsu.mcis.cs310.tas_fa21;
import java.time.LocalDateTime;

/**
 *
 * @author Brandon Pugh
 */


public class Punch {

        public static void main(String[] args) {
            
    }
        public enum PunchType {
    CLOCK_OUT("CLOCK OUT"),
    CLOCK_IN("CLOCK IN"),
    TIME_OUT("TIME OUT");
    private final String description;
    private PunchType(String d) { description = d; }
    @Override
    public String toString() { return description; }
}
        
    private int Punch;
    
    

    /**
     * Get the value of Punch
     *
     * @return the value of Punch
     */
    public int getPunch() {
        return Punch;
    }

    /**
     * Set the value of Punch
     *
     * @param Punch new value of Punch
     */
    public void setPunch(int Punch) {
        this.Punch = Punch;
    }

 
}
