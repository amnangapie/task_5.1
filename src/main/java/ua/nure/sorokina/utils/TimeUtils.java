package ua.nure.sorokina.utils;

import java.util.*;

public class TimeUtils {

    public static final int WORK_HOURS_PER_WEEK = 40;

    public static int getWorkdaysPerMonth(int month, int year) {
        Calendar date = new GregorianCalendar(year, month, 1);
        Set<Calendar> holidays = Holidays.getHolidays();
        int workdays = 0;

        Optional<Calendar> isHoliday = null;

        while (date.get(Calendar.MONTH) == month) {
            int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);

            isHoliday = holidays.stream()
                    .filter(holiday -> (holiday.get(Calendar.MONTH) == month
                            && holiday.get(Calendar.DAY_OF_MONTH) == dayOfMonth))
                    .findFirst();

            if (isHoliday.isEmpty() && dayOfWeek != Calendar.SATURDAY
                    && dayOfWeek != Calendar.SUNDAY) {
                workdays++;
            }
            date.add(Calendar.DATE, 1);
        }

        return workdays;
    }

    public static int getWorkHoursPerMonth(int month, int year) {
        int workdays = getWorkdaysPerMonth(month, year);
        return workdays * (WORK_HOURS_PER_WEEK / 5);
    }
}
