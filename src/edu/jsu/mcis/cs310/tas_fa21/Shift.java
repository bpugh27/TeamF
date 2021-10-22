package edu.jsu.mcis.cs310.tas_fa21;

import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Shift {
    
    private int id, interval, gracePeriod, dock, lunchDeduct, lunchDuration, shiftDuration;
    private String description;
    private LocalTime start;
    private LocalTime stop;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    

    public Shift(int id, String description, LocalTime start, LocalTime stop, int interval, 
            int gracePeriod, int dock, LocalTime lunchStart, LocalTime lunchStop, int lunchDeduct) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStart() {
        return start;
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

    
    public int getShiftDuration() {
        return shiftDuration;
    }

    //Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)
    
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        
        s.append(description).append(": ").append(start);
        s.append(" - ").append(stop).append(" (").append(shiftDuration);
        s.append(" minutes); Lunch: ").append(lunchStart).append(" - ");
        s.append(lunchStop).append(" (").append(lunchDuration).append(" minutes)");
        
        
        return s.toString();

    }
    
    
    
    
    
}