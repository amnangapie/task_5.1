package ua.nure.sorokina;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.nure.sorokina.utils.InterfaceAdapter;
import ua.nure.sorokina.utils.TimeUtils;

import java.io.Serializable;
import java.util.Objects;

public abstract class Employee {
    private long id;
    private String firstName;
    private String lastName;

    /* monthly salary with 100-percent hours worked */
    private int rate;
    /* actual hours of work per month */
    private int actualHoursOfWork;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(long id, String firstName, String lastName, int rate, int actualHoursOfWork) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rate = rate;
        this.actualHoursOfWork = actualHoursOfWork;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) throws IllegalArgumentException {
        if (rate < 0) {
            throw new IllegalArgumentException("Invalid value: salary must be positive!");
        }
        this.rate = rate;
    }

    public int getActualHoursOfWork() {
        return actualHoursOfWork;
    }

    public void setActualHoursOfWork(int actualHoursOfWork)
            throws IllegalArgumentException {
        if (actualHoursOfWork < 0) {
            throw new IllegalArgumentException("Invalid value: hours of work must be positive!");
        }
        this.actualHoursOfWork = actualHoursOfWork;
    }

    public double getActualWorkTimePercentage(int month, int year) {
        int workHours = TimeUtils.getWorkHoursPerMonth(month, year);
        double percentage = (double) actualHoursOfWork / workHours;

        return Math.round(percentage * 100.) / 100.;
    }

    public abstract double getSalary(int month, int year);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public String toJson() {
        Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Employee.class,
                        new InterfaceAdapter<Employee>())
                .create();

        return gson.toJson(this);
    }

    public static Employee fromJson(String json) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Employee.class,
                new InterfaceAdapter<Employee>())
                .create();

        return gson.fromJson(json, Employee.class);
    }
}
