package edu.jsu.mcis.cs310.tas_fa21;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature1 {

    private TASDataBase db;
    
    @Before
    public void setup() {
        
        db = new TASDataBase();
        
    }
    
    @Test
    public void testGetBadges() {
		
        /* Retrieve Badges from Database */

        Badge b1 = db.getBadge("12565C60");
        Badge b2 = db.getBadge("08D01475");
        Badge b3 = db.getBadge("D2CC71D4");
		
        /* Compare to Expected Values */
        
        assertEquals("#12565C60 (Chapman, Joshua E)", b1.toString());
        assertEquals("#08D01475 (Littell, Amie D)", b2.toString());
        assertEquals("#D2CC71D4 (Lawson, Matthew J)", b3.toString());
        
    }
    
    @Test
    public void testGetPunches() {
		
        /* Retrieve Punches from Database */

        Punch p1 = db.getPunch(3433);
        Punch p2 = db.getPunch(3325);
        Punch p3 = db.getPunch(1963);
        
        Punch p4 = db.getPunch(5702);
        Punch p5 = db.getPunch(4976);
        Punch p6 = db.getPunch(2193);
        
        Punch p7 = db.getPunch(954);
        Punch p8 = db.getPunch(258);
        Punch p9 = db.getPunch(717);
		
        /* Compare to Expected Values */

        assertEquals("#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07", p1.printOriginal());
        assertEquals("#DFD9BB5C CLOCK IN: TUE 09/04/2018 08:00:00", p2.printOriginal());
        assertEquals("#99F0C0FA CLOCK IN: SAT 08/18/2018 06:00:00", p3.printOriginal());
        
        assertEquals("#0FFA272B CLOCK OUT: MON 09/24/2018 17:30:04", p4.printOriginal());
        assertEquals("#FCE87D9F CLOCK OUT: TUE 09/18/2018 17:34:00", p5.printOriginal());
        assertEquals("#FCE87D9F CLOCK OUT: MON 08/20/2018 17:30:00", p6.printOriginal());
        
        assertEquals("#618072EA TIME OUT: FRI 08/10/2018 00:12:35", p7.printOriginal());
        assertEquals("#0886BF12 TIME OUT: THU 08/02/2018 06:06:38", p8.printOriginal());
        assertEquals("#67637925 TIME OUT: TUE 08/07/2018 23:12:34", p9.printOriginal());
        
    }
    
    @Test
    public void testGetShiftByID() {
		
        /* Retrieve Shift Rulesets from Database */

        Shift s1 = db.getShift(1);
        Shift s2 = db.getShift(2);
        Shift s3 = db.getShift(3);
		
        /* Compare to Expected Values */

        assertEquals("Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)", s1.toString());
        assertEquals("Shift 2: 12:00 - 20:30 (510 minutes); Lunch: 16:30 - 17:00 (30 minutes)", s2.toString());
        assertEquals("Shift 1 Early Lunch: 07:00 - 15:30 (510 minutes); Lunch: 11:30 - 12:00 (30 minutes)", s3.toString());

    }
    
    @Test
    public void testGetShiftByBadge() {
        
        /* Create Badge Objects */
        
        Badge b1 = db.getBadge("B6902696");
        Badge b2 = db.getBadge("76E920D9");
        Badge b3 = db.getBadge("4382D92D");
        
		
        /* Retrieve Shift Rulesets from Database */

        Shift s1 = db.getShift(b1);
        Shift s2 = db.getShift(b2);
        Shift s3 = db.getShift(b3);
		
        /* Compare to Expected Values */

        assertEquals("Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)", s1.toString());
        assertEquals("Shift 2: 12:00 - 20:30 (510 minutes); Lunch: 16:30 - 17:00 (30 minutes)", s2.toString());
        assertEquals("Shift 1 Early Lunch: 07:00 - 15:30 (510 minutes); Lunch: 11:30 - 12:00 (30 minutes)", s3.toString());

    }
    
}