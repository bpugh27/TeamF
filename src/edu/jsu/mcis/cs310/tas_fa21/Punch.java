package edu.jsu.mcis.cs310.tas_fa21;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.*;

public class Punch {
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    private int id;
    private int terminalid;
    private Badge badge;
    private LocalDateTime originaltimestamp;
    private PunchType punchtype;
    private String adjustmentype;
    private LocalDateTime adjustedtimestamp;
   
    
    public Punch(int terminalid, Badge badge, int punchtypeid){
        
        
        this.terminalid = terminalid;
        this.badge= badge;
        this.punchtype = PunchType.values()[punchtypeid];
        
        this.originaltimestamp = LocalDateTime.now();
    }
    
    Punch(int id, int terminalid, Badge badge, PunchType punchtypeid, LocalDateTime originaltimestamp){
        
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtypeid;
        this.originaltimestamp = originaltimestamp;
        
    }

    //new punch??
    public Punch(int terminalid, Badge badgeid, LocalDateTime originaltimestamp, int punchtypeid) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = PunchType.values()[punchtypeid];
        this.originaltimestamp = originaltimestamp;
    }

    Punch(int id, int terminalid, Badge badge, int punchtypeid, LocalDateTime originaltimestamp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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

    
    
    public void adjust(Shift s){
    
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
