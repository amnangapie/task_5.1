package ua.nure.sorokina.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Holidays {
    private static final Set<Calendar> holidays = new HashSet<>();

    static {
        holidays.add(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.JANUARY, 1));
        holidays.add(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.JANUARY, 7));
        holidays.add(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.OCTOBER, 14));
        holidays.add(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.DECEMBER, 25));
        holidays.add(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.DECEMBER, 31));
    }

    public static Set<Calendar> getHolidays() {
        return holidays;
    }
}
