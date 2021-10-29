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
    
    private int id, interval, gracePeriod, dock, lunchDeduct, lunchDuration, shiftDuration;
    private String description;
    private LocalTime start;
    private LocalTime stop;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    
    
    public Shift(int interval, int id, int gracePeriod, LocalTime begin, int dock,
            LocalTime lunchBegin, int lunchDeduct, LocalTime lunchEnd, LocalTime end, String description) {
        this.interval = interval;
        this.id = id;
        this.description = description;
        this.start = start;
        this.stop = stop;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchDeduct = lunchDeduct;
        this.lunchDuration = (int) MINUTES.between(lunchStart, lunchStop);
        this.shiftDuration = (int) MINUTES.between(start, stop);
    }
    
    public int getId() {
        return id;
    }

    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(LocalTime lunchStart) {
        this.lunchStart = lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(LocalTime lunchStop) {
        this.lunchStop = lunchStop;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    
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
