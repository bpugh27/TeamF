package edu.jsu.mcis.cs310.tas_fa21;

import java.time.Duration;
import java.util.ArrayList;
//"C:\Users\Graham\Desktop\JSU Resources\Software Engineering I\Team F Project\github\TeamF\src\edu\jsu\mcis\cs310\tas_fa21\TAS.java"




public class TAS {

    public static void main(String[] args) {
        
        TASDataBase test = new TASDataBase();
        
        int punchid = 147;
        test.getPunch(punchid);
        
    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        int time = 0;
        
        try {
            for (int i = 0; i < dailypunchlist.size(); i += 2) {
                Duration dur = Duration.between(dailypunchlist.get(i).getAdjustedtimestamp(), dailypunchlist.get(i + 1).getAdjustedtimestamp());
                int minutesTotal = (int)dur.toMinutes();
                time = time + minutesTotal;
            }
            boolean clockout = false;
            for(Punch p : dailypunchlist){ 
                if (p.getAdjustedtimestamp().toLocalTime().equals(shift.getLunchstart())){
                    clockout = true; 
                    break;
                    }
               }

               if(!clockout){
                   time = (int) (time - shift.getLunchduration());
               }
               
        }
        catch(IndexOutOfBoundsException e){System.out.println("calculateTotalMinutes Error!" + e);} 
        catch(Exception e){e.printStackTrace();}

        return time;
        
        }
            
        }
    
