package ua.nure.sorokina;

import java.lang.reflect.Type;

public class Developer extends Employee {
    public Developer() {
        super();
    }

    public Developer(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Developer(long id, String firstName, String lastName, int rate, int actualHoursOfWork) {
        super(id, firstName, lastName, rate, actualHoursOfWork);
    }

    @Override
    public double getSalary(int month, int year) {
        int initialSalary = getRate();
        double workPercentage = getActualWorkTimePercentage(month, year);
        return Math.round(workPercentage * initialSalary * 100.) / 100.;
    }
}
