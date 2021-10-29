//<<<<<<< OURS

package edu.jsu.mcis.cs310.tas_fa21;

import java.util.Calendar;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.*;
import java.time.temporal.ChronoUnit;



public class Shift {
    private LocalTime shiftBegin, lunchBegin, lunchEnd, shiftEnd;
    private int id, gracePeriod, dock, interval, lunchDeduct;
    private final String description;
    
    //Shift should read a timestamp as a long type
    //then converted and stored as LocalTime or LocalDateTime variables
    //Seconds and nanoseconds should be zeroed
    
    
    public Shift(int interval, int id, int gracePeriod, LocalTime begin, int dock,
            LocalTime lunchBegin, int lunchDeduct, LocalTime lunchEnd, LocalTime end, String description) {
        this.interval = interval;
        this.id = id;
        this.gracePeriod = gracePeriod;
        this.shiftBegin = begin;
        this.dock = dock;
        this.lunchBegin = lunchBegin;
        this.lunchDeduct = lunchDeduct;
        this.lunchEnd = lunchEnd;
        this.shiftEnd = end;
        this.description = description;
    }
    
    Shift(int interval, int Id, int gracePeriod, int dock, int lunchDeduct, String des, Time begin, Time end, Time lunchBegin, Time lunchEnd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //getter

    public LocalTime getShiftBegin() {
        return shiftBegin;
    }

    public LocalTime getLunchBegin() {
        return lunchBegin;
    }

    public LocalTime getLunchEnd() {
        return lunchEnd;
    }

    public LocalTime getShiftEnd() {
        return shiftEnd;
    }

    public int getId() {
        return id;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public int getInterval() {
        return interval;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public String getDescription() {
        return description;
    }
    
    
//    private long getElapsedTime(Time s, Time e) {
//        
//        Calendar BeginCal = GregorianCalendar.getInstance();
//        Calendar endCal = GregorianCalendar.getInstance();
//        BeginCal.setTimeInMillis(s.getTime());
//        endCal.setTimeInMillis(e.getTime());
//        long begin, end;
//        begin = BeginCal.getTimeInMillis();
//        end = endCal.getTimeInMillis();
//        
//        return (end - begin) / (60 * 1000);
//    }   
    
    @Override
    public String toString() {
    
        String beginTime = shiftBegin.toString();
        String lunchBeginTime = lunchBegin.toString();
        String lunchEndTime = lunchEnd.toString();
        String endTime = shiftEnd.toString();
        
//        data += description + ": ";
//        data += beginTime + " - ";
//        data += getElapsedTime(shiftBegin, shiftEnd) + " minutes);";
//        data += " Lunch: " + lunchBeginTime + " - ";
//        data += lunchEndTime + " (";
//        data += getElapsedTime(lunchBegin, lunchEnd) + " minutes)";
//        data += endTime + " (";
        
        //        assertEquals("Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)", s1.toString());
        //"Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)",
        StringBuilder d = new StringBuilder();
        d.append(description).append(": ").append(beginTime).append(" - ").append(endTime).append(" (");
        d.append(shiftBegin.until(shiftEnd, ChronoUnit.MINUTES)).append(" minutes); ");
        d.append("Lunch: ").append(lunchBeginTime).append(" - ").append(lunchEndTime).append(" (").append(lunchBegin.until(lunchEnd, ChronoUnit.MINUTES));
        d.append(" minutes)");
        
        
        return d.toString();
    }
}
