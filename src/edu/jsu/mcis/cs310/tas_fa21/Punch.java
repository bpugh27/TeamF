package edu.jsu.mcis.cs310.tas_fa21;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
import java.util.*;

public class Punch {
    private int id;
    private int terminalid;
    private String badgeid;
    private LocalDateTime originaltimestamp;
    private PunchType punchtype;
//    private String adjustmentype;
    
    Punch(int terminalid, Badge badge, int punchtypeid){
        
        this.id = 0; // for new punches
        this.terminalid = terminalid;
        this.badgeid = badge.getId();
        this.punchtype = PunchType.values()[punchtypeid];
        
        this.originaltimestamp = LocalDateTime.now();
    }
    
    Punch(int id, int terminalid, Badge badge, int punchtypeid, LocalDateTime originaltimestamp){
        
        this.id = id;
        this.terminalid = terminalid;
        this.badgeid = badge.getId();
        this.punchtype = PunchType.values()[punchtypeid];
        this.originaltimestamp = originaltimestamp;
        
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }

    public PunchType getPunchtype() {
        return punchtype;
    }


    
    public String printOriginal(){
        
        // #D2C39273 CLOCK IN: WED 09/05/2018 07:00:07
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badgeid).append(' ');
        s.append(punchtype).append(": ").append(format.format(originaltimestamp).toUpperCase());
        
        return s.toString();
        
    }
    
    
}
