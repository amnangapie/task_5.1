package ua.nure.sorokina;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Accountant {

    public static Map<Employee, Double> calculateSalaries(Set<Employee> employees,
                                                          int month, int year) {
        HashMap<Employee, Double> salaries = new HashMap<>();
        employees.forEach(employee ->
                salaries.put(employee, employee.getSalary(month, year)));

        return salaries;
    }
}
