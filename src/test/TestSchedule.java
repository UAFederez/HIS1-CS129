package test;

import java.util.ArrayList;
import java.util.Collections;
import lib.Schedule;
import lib.TimePoint;

public class TestSchedule {
  public static void main(String[] args) {
      System.out.println("Calculating differences between minutes:\n" +
                         "---------------------------------------");
      {
        Schedule s1 = new Schedule(1, new TimePoint(0, 15), new TimePoint(0, 30), false);
        Schedule s2 = new Schedule(1, new TimePoint(0, 30), new TimePoint(1, 45), false);
        int result  = s1.compareTo(s2);
        
        System.out.printf("%-70s [%s] (%d)\n" ,s1.toString() + " comes before " + s2.toString(),
                                               (result < 0 ? "PASSED" : "FAILED"), result);
      }
      
      System.out.println("Calculating differences between hours:\n" +
                         "---------------------------------------");
      {
        Schedule s1 = new Schedule(1, new TimePoint(0, 15), new TimePoint(0, 30), false);
        Schedule s2 = new Schedule(1, new TimePoint(1, 15), new TimePoint(1, 30), false);
        int result  = s1.compareTo(s2);
        
        System.out.printf("%-70s [%s] (%d)\n" ,s1.toString() + " comes before " + s2.toString(),
                                               (result < 0 ? "PASSED" : "FAILED"), result);
      }
      
      System.out.println("Calculating differences between days:\n" +
                         "---------------------------------------");
      {
        Schedule s1 = new Schedule(1, new TimePoint(0, 15), new TimePoint(0, 30), false);
        Schedule s2 = new Schedule(2, new TimePoint(0, 15), new TimePoint(0, 30), false);
        int result  = s1.compareTo(s2);
        
        System.out.printf("%-70s [%s] (%d)\n" ,s1.toString() + " comes before " + s2.toString(),
                                               (result < 0 ? "PASSED" : "FAILED"), result);
      }
      
      {
        Schedule s1 = new Schedule(1, new TimePoint(0, 15), new TimePoint(0, 30), false);
        Schedule s2 = new Schedule(1, new TimePoint(0, 15), new TimePoint(0, 30), false);
        int result  = s1.compareTo(s2);
        
        System.out.printf("%-65s [%s] (%d)\n" , s1.toString() + " and " + s2.toString() + " have a difference of zero ", 
                                              (result == 0 ? "PASSED" : "FAILED"), result);
      }
      
      System.out.println("\nSchedules:");
      
      ArrayList<Schedule> schedules = new ArrayList<>();
      schedules.add(new Schedule(2, 9, 0,  12, 0, false));
      schedules.add(new Schedule(4, 9, 0,  12, 0, false));
      
      schedules.add(new Schedule(0, 16, 0, 18, 0, false));
      
      schedules.add(new Schedule(0, 14, 0, 15, 0, false));
      schedules.add(new Schedule(1, 14, 0, 15, 0, false));
      schedules.add(new Schedule(3, 14, 0, 15, 0, false));
      schedules.add(new Schedule(4, 14, 0, 15, 0, false));
      
      Collections.sort(schedules);
      
      for(Schedule s : schedules)
          System.out.println(s.toString());
  }
}