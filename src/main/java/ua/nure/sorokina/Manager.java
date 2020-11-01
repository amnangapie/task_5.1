package ua.nure.sorokina;

public class Manager extends Employee {
    public Manager() {
        super();
    }

    public Manager(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Manager(long id, String firstName, String lastName, int rate, int actualHoursOfWork) {
        super(id, firstName, lastName, rate, actualHoursOfWork);
    }

    @Override
    public double getSalary(int month, int year) {
        int salary = getRate();
        double workPercentage = getActualWorkTimePercentage(month, year);
        if (workPercentage < 1) {
            salary *= workPercentage;
        }
        return Math.round(salary * 100.) / 100.;
    }
}
