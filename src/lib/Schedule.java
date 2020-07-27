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
    
    /** Note: Because doctors will be listed by sorted schedule, one way is to create
     *        a new array of Schedules, put all the schedules of all the doctors
     *        in, and then sort the array. But to show the doctor info, we need a 
     *        reference to the actual Doctor object who has this schedule.
    **/
    private Doctor associatedDoctor;
    
    /**
     * @param weekDay   The day of the week (0 - MON, 7 - SUN)
     * @param from      Time in hours   (0 - 23)
     * @param to        Time in minutes (0 - 59)
     * @param isByAppt  If this schedule is by appointment
     */
    public Schedule(int weekDay, TimePoint from, TimePoint to) 
    {
        this.dayOfWeek = weekDay;
        this.timeFrom  = from;
        this.timeTo    = to;
    }
    
    /**
     * 
     * @param inputString Input string to parse (assumes WeekDay TFH:TFM-TTH:TTM)
     *                    where TF - time from, TT - time to, H - hour, M - minute
     */
    public Schedule(String inputString)
    {
        String[] dayAndTime  = inputString.split(" ");
        String[] times       = dayAndTime[1].split("-");
        String[] timeFromStr = times[0].split(":");
        String[] timeToStr   = times[1].split(":");
        
        this.dayOfWeek = Integer.parseInt(dayAndTime[0]);
        this.timeFrom  = new TimePoint(Integer.parseInt(timeFromStr[0]), 
                                       Integer.parseInt(timeFromStr[1]));
        
        this.timeTo    = new TimePoint(Integer.parseInt(timeToStr[0]), 
                                       Integer.parseInt(timeToStr[1]));
    }
    
    /**
     * 
     * @param weekDay   The day of the week (0 - MON, 7 - SUN)
     * @param hhFrom    The hour of the start of the schedule   (0 - 23)
     * @param mmFrom    The minute of the start of the schedule (0 - 59)
     * @param hhTo      The hour of the end of the schedule     (0 - 23)
     * @param mmTo      The minute of the end of the schedule   (0 - 59)
     */
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
    
    public int getDay() {
        return dayOfWeek;
    }
    
    public void setAssociatedDoctor(Doctor doctor) {
        associatedDoctor = doctor;
    }
    
    public Doctor getAssociatedDoctor() {
        return associatedDoctor;
    }
    
    public boolean equals(Schedule other) {
        return dayOfWeek == other.dayOfWeek    &&
               timeFrom.equals(other.timeFrom) &&
               timeTo.equals(other.timeTo);
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
