package lib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uafed
 */
public class TimePoint {
    private final int hour;
    private final int minute;

    public TimePoint(int hh, int mm) {
        this.hour   = hh;
        this.minute = mm;
    }
	
    public int getHour() {
        return hour;
    }
    
    public int getMinute() {
        return minute;
    }
    
    public int inMinutes() {
        return (hour * 60) + minute;
    }
    
    public String getHourString() {
        return (hour < 10 ? "0" + hour : "" + hour);
    }
    
    public boolean equals(TimePoint other) {
        return hour == other.hour && minute == other.minute;
    }
    
    public String getMinuteString() {
        return (minute < 10 ? "0" + minute : "" + minute);
    }
}
