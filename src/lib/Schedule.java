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
public class Schedule implements Comparable<Schedule> {
    private final int       dayOfWeek;
    private final TimePoint timeFrom;
    private final TimePoint timeTo;
    
    /** TODO: Because doctors will be listed by sorted schedule, one way is to create
     *        a new array of Schedules, put all the schedules of all the doctors
     *        in, and then sort the array. But to show the doctor info, we need a 
     *        reference to the actual Doctor object who has this schedule.
    **/
    // private final Doctor associatedDoctor;
    
    /**
     * @param weekDay   The day of the week (0 - MON, 7 - SUN)
     * @param from      Time in hours   (0 - 24)
     * @param to        Time in minutes (0 - 60)
     */
    public Schedule(int weekDay, TimePoint from, TimePoint to) 
    {
        this.dayOfWeek = weekDay;
        this.timeFrom  = from;
        this.timeTo    = to;
    }
    
    public Schedule(int weekDay, int hhFrom, int mmFrom, int hhTo, int mmTo) 
    {
        this.dayOfWeek = weekDay;
        this.timeFrom  = new TimePoint(hhFrom, mmFrom);
        this.timeTo    = new TimePoint(hhTo, mmTo);
    }
   	
    public TimePoint getTimeFrom() {
        return timeFrom;     
    }
    
    public TimePoint getTimeTo() {
       	return timeTo; 
    }
    
    public int getDayOfWeek() {
        return dayOfWeek;
    }
    
    /**
     * @param other The schedule to compare with
     * @return an integer value of the difference in minutes
     */
    @Override
    public int compareTo(Schedule other) {
        return ((dayOfWeek - other.getDayOfWeek()) * 60 * 24) + 
                (timeFrom.inMinutes() - other.getTimeFrom().inMinutes());
    }
    
    // Note(uriel): mostly irrelevant since sa time is displayed in the UI, 
    // only useful for debug purposes
    @Override
    public String toString()
    {
        String[] str = { 
            "Monday",   "Tuesday", "Wednesday", 
            "Thursday", "Friday",  "Saturday", 
            "Sunday"
        };
        
        return str[dayOfWeek] + " " + timeFrom.getHour() + ":" + timeFrom.getMinute()
                              + "-" + timeTo.getHour()   + ":" + timeTo.getMinute(); 
    }
}
