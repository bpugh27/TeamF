
package edu.jsu.mcis.cs310.tas_fa21;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Punch {
    private int terminalid;
    private String badgeid;
    private long originaltimestamp;
    private String punchtype;
    private String adjustmentype;
    
    Punch(int terminalid, Badge badge, int punchtypeid){
        this.terminalid = terminalid;
        this.badgeid = badge.getId();
        
        switch (punchtypeid) {
            case 0:
                this.punchtype = PunchType.CLOCK_IN.toString();
                break;
            case 1:
                this.punchtype = PunchType.CLOCK_OUT.toString();
                break;
            default:
                this.punchtype = PunchType.TIME_OUT.toString();
                break;
        }
        
        this.originaltimestamp = System.currentTimeMillis();
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

    public long getOriginaltimestamp() {
        return originaltimestamp;
    }

    public void setOriginaltimestamp(long originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }

    public String getPunchtype() {
        return punchtype;
    }

    public void setPunchtype(String punchtype) {
        this.punchtype = punchtype;
    }
    
    public String printOriginal(){
        Calendar cal = new GregorianCalendar();
        String format = "EEE dd/MM/yyyy HGregorianCalendarH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        cal.setTimeInMillis(this.originaltimestamp);
        String formattedDate = sdf.format(cal.getTime()).toUpperCase();
        String s = "#" + this.getBadgeid() + " " + this.getPunchtype() + ": " + formattedDate;
        return s;
        
    }
    
    
}