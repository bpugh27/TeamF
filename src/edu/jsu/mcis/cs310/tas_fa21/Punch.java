package edu.jsu.mcis.cs310.tas_fa21;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class Punch {
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    private int id;
    private int terminalid;
    private Badge badge;
    private LocalDateTime originaltimestamp;
    private PunchType punchtype;
    private String adjustmenttype;
    private LocalDateTime adjustedtimestamp;
   
    
    public Punch(int terminalid, Badge badge, int punchtypeid){
        
        
        this.terminalid = terminalid;
        this.badge= badge;
        this.punchtype = PunchType.values()[punchtypeid];
        
        this.originaltimestamp = LocalDateTime.now();
    }
    
    Punch(int id, int terminalid, Badge badge, int punchtypeid, LocalDateTime originaltimestamp){
        
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = PunchType.values()[punchtypeid];
        this.originaltimestamp = originaltimestamp;
        
    }

    //new punch??
    public Punch(int terminalid, Badge badgeid, LocalDateTime originaltimestamp, int punchtypeid) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = PunchType.values()[punchtypeid];
        this.originaltimestamp = originaltimestamp;
    }

//    Punch(int id, int terminalid, Badge badge, int punchtypeid, LocalDateTime originaltimestamp) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    public int getPunchId() {
        return id;
    }

    public void setPunchId(int id) {
        this.id = id;
    }
    
    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }

    public PunchType getPunchtype() {
        return punchtype;
    }

    public void setOriginaltimestamp(LocalDateTime originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }

    
    
   public void adjust(Shift s) {
        TemporalField usWeekDay = WeekFields.of(Locale.US).dayOfWeek();
        int dayofweek = originaltimestamp.get(usWeekDay);
        
        adjustmenttype = null;
        
        LocalDateTime shiftStart = s.getShiftBegin().atDate(originaltimestamp.toLocalDate());
        LocalDateTime shiftStop = s.getShiftEnd().atDate(originaltimestamp.toLocalDate());
        LocalDateTime lunchStart = s.getLunchBegin().atDate(originaltimestamp.toLocalDate());
        LocalDateTime lunchStop = s.getLunchEnd().atDate(originaltimestamp.toLocalDate());

        LocalDateTime shiftStartInterval = shiftStart.minusMinutes(s.getInterval());
        LocalDateTime shiftStartGrace = shiftStart.plusMinutes(s.getGracePeriod());
        LocalDateTime shiftStartDock = shiftStart.plusMinutes(s.getDock());
        LocalDateTime shiftStopInterval = shiftStop.plusMinutes(s.getInterval());
        LocalDateTime shiftStopGrace = shiftStop.minusMinutes(s.getGracePeriod());
        LocalDateTime shiftStopDock = shiftStop.minusMinutes(s.getDock());
       
        int intervalRound = originaltimestamp.toLocalTime().getMinute()%s.getInterval();
        int halfInterval = s.getInterval()/2;
        long roundIntervalLong;

       if(dayofweek != Calendar.SATURDAY && dayofweek != Calendar.SUNDAY) {
           if(punchtype == PunchType.CLOCK_IN) {
               //adjust for the interval
               if((originaltimestamp.isAfter(shiftStartInterval) || originaltimestamp.isEqual(shiftStartInterval)) && originaltimestamp.isBefore(shiftStart)) {
                   adjustedtimestamp = shiftStart;
                   adjustmenttype = "Shift Start";
               }
               //adjust for the dock
               else if ((originaltimestamp.isBefore(shiftStartDock) || originaltimestamp.isEqual(shiftStartInterval)) && originaltimestamp.isAfter(shiftStartGrace)) {
                   adjustedtimestamp = shiftStartDock;
                   adjustmenttype = "Shift Dock";
               }
               //adjust for the grace
               else if ((originaltimestamp.isBefore(shiftStartGrace) || originaltimestamp.isEqual(shiftStartGrace)) && originaltimestamp.isAfter(shiftStart)) {
                   adjustedtimestamp = shiftStart;
                   adjustmenttype = "Shift Start";
               }
               //adjust for the lunch
               else if ((originaltimestamp.isBefore(lunchStop) || originaltimestamp.isEqual(lunchStop)) && originaltimestamp.isAfter(lunchStart)) {
                   adjustedtimestamp = lunchStop;
                   adjustmenttype = "Lunch Stop";
               }
           }
           else if (punchtype == PunchType.CLOCK_OUT) {
                //adjust for the interval
                if ((originaltimestamp.isBefore(shiftStopInterval) || originaltimestamp.isEqual(shiftStopInterval)) && originaltimestamp.isAfter(shiftStop)){
                    adjustedtimestamp = shiftStop;
                    adjustmenttype = "Shift Stop";
                }
                //adjust for the dock
                else if ((originaltimestamp.isAfter(shiftStopDock) || originaltimestamp.isEqual(shiftStopDock)) && originaltimestamp.isBefore(shiftStopGrace)){
                    adjustedtimestamp = shiftStopDock;
                    adjustmenttype = "Shift Dock";
                }
                //adjust for the grace
                else if ((originaltimestamp.isAfter(shiftStopGrace) || originaltimestamp.isEqual(shiftStopGrace)) && originaltimestamp.isBefore(shiftStop)){
                    adjustedtimestamp = shiftStop;
                    adjustmenttype = "Shift Stop";
                }
                //adjust for the lunch
                else if (originaltimestamp.isBefore(lunchStop) && (originaltimestamp.isAfter(lunchStart) || originaltimestamp.isEqual(lunchStart))){
                    adjustedtimestamp = lunchStart;
                    adjustmenttype = "Lunch Start";
                }    
            }
        }
        
        if (((punchtype == PunchType.CLOCK_IN || punchtype == PunchType.CLOCK_OUT)) && adjustmenttype == null) {
            
            if (intervalRound != 0) {
               
                if (intervalRound < halfInterval) {
                    
                    roundIntervalLong = new Long(intervalRound);
                    adjustedtimestamp = originaltimestamp.minusMinutes(roundIntervalLong).withSecond(0);
                    adjustmenttype = "Interval Round";   
                }
                
                else if (intervalRound >= halfInterval) {
                    roundIntervalLong = new Long(s.getInterval() - intervalRound);
                    adjustedtimestamp = originaltimestamp.plusMinutes(roundIntervalLong).withSecond(0);
                    adjustmenttype = "Interval Round";
                }
            }
                
            else {
                adjustmenttype = "None";
                adjustedtimestamp = originaltimestamp.withSecond(0).withNano(0);
            }    
       }
    }  

        
        
    
    public String printOriginal(){
        
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badge.getId()).append(' ');
        s.append(punchtype).append(": ").append(originaltimestamp.format(dtf));
        
        return s.toString().toUpperCase();
        
    }
    
    public String printAdjusted(){
        
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badge.getId()).append(' ');
        s.append(punchtype).append(": ").append(adjustedtimestamp.format(dtf));
        
        return s.toString().toUpperCase();
        
    }
    
}
