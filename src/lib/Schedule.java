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
    private final boolean   isByAppointment;
    
    /** TODO: Because doctors will be listed by sorted schedule, one way is to create
     *        a new array of Schedules, put all the schedules of all the doctors
     *        in, and then sort the array. But to show the doctor info, we need a 
     *        reference to the actual Doctor object who has this schedule.
    **/
    // private final Doctor associatedDoctor;
    
    /**
     * @param weekDay   The day of the week (0 - MON, 7 - SUN)
     * @param from      Time in hours   (0 - 23)
     * @param to        Time in minutes (0 - 59)
     * @param isByAppt  If this schedule is by appointment
     */
    public Schedule(int weekDay, TimePoint from, TimePoint to, boolean isByAppt) 
    {
        this.dayOfWeek = weekDay;
        this.timeFrom  = from;
        this.timeTo    = to;
        
        this.isByAppointment = isByAppt;
    }
    
    /**
     * 
     * @param weekDay   The day of the week (0 - MON, 7 - SUN)
     * @param hhFrom    The hour of the start of the schedule   (0 - 23)
     * @param mmFrom    The minute of the start of the schedule (0 - 59)
     * @param hhTo      The hour of the end of the schedule     (0 - 23)
     * @param mmTo      The minute of the end of the schedule   (0 - 59)
     * @param isByAppt  If this schedule is by appointment
     */
    public Schedule(int weekDay, int hhFrom, int mmFrom, int hhTo, int mmTo, boolean isByAppt) 
    {
        this.dayOfWeek = weekDay;
        this.timeFrom  = new TimePoint(hhFrom, mmFrom);
        this.timeTo    = new TimePoint(hhTo, mmTo);
        this.isByAppointment = isByAppt;
    }
   	
    public TimePoint getTimeFrom() {
        return timeFrom;     
    }
    
    public TimePoint getTimeTo() {
       	return timeTo; 
    }
    
    public int getDay() {
        return dayOfWeek;
    }
    
    public boolean isByAppointment() {
        return isByAppointment;
    }
    
    /**
     * @param other The schedule to compare with
     * @return an integer value of the difference in minutes
     */
    @Override
    public int compareTo(Schedule other) {
        return ((dayOfWeek - other.getDay()) * 60 * 24) + 
                (timeFrom.inMinutes() - other.getTimeFrom().inMinutes());
    }
    
    public String getDayString() {
        String[] str = { 
            "Monday",   "Tuesday", "Wednesday", 
            "Thursday", "Friday",  "Saturday", 
            "Sunday"
        };
        
        return str[dayOfWeek];
    }
    
    // Note(uriel): mostly irrelevant since sa time is displayed in the UI, 
    // only useful for debug purposes
    @Override
    public String toString()
    {
        return getDayString() + " " + timeFrom.getHourString() 
                              + ":" + timeFrom.getMinuteString()
                              + "-" + timeTo.getHourString()   
                              + ":" + timeTo.getMinuteString(); 
    }
}
