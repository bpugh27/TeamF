package edu.jsu.mcis.cs310.tas_fa21;

import java.util.Calendar;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;



public class Shift {
    private Time shiftBegin, lunchBegin, lunchEnd, shiftEnd;
    private int id, gracePeriod, dock, interval, lunchDeduct;
    private final String description;
    
    public Shift(int interval, int id, int gracePeriod, Time begin, int dock, Time lunchBegin, int lunchDeduct, Time lunchEnd, Time end, String description) {
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

    public Time getShiftBegin() {
        return shiftBegin;
    }

    public Time getLunchBegin() {
        return lunchBegin;
    }

    public Time getLunchEnd() {
        return lunchEnd;
    }

    public Time getShiftEnd() {
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
    
    private long getElapsedTime(Time s, Time e) {
        Calendar BeginCal = GregorianCalendar.getInstance();
        Calendar endCal = GregorianCalendar.getInstance();
        BeginCal.setTimeInMillis(s.getTime());
        endCal.setTimeInMillis(e.getTime());
        long begin, end;
        begin = BeginCal.getTimeInMillis();
        end = endCal.getTimeInMillis();
        return (end - begin) / (60 * 1000);
    }
    @Override
    public String toString() {
    
        String beginTime = (new SimpleDateFormat("HH:mm")).format(shiftBegin.getTime());
        String lunchBeginTime = (new SimpleDateFormat("HH:mm")).format(lunchBegin.getTime());
        String lunchEndTime = (new SimpleDateFormat("HH:mm")).format(lunchEnd.getTime());
        String endTime = (new SimpleDateFormat("HH:mm")).format(shiftEnd.getTime());
        String data = "";
        
        data += description + ": ";
        data += beginTime + " - ";
        data += getElapsedTime(shiftBegin, shiftEnd) + " minutes);";
        data += " Lunch: " + lunchBeginTime + " - ";
        data += lunchEndTime + " (";
        data += getElapsedTime(lunchBegin, lunchEnd) + " minutes)";
        data += endTime + " (";
        return data;
    }
      
}