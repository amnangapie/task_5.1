package ua.nure.sorokina;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Set<Employee> employees = new HashSet<>();
    Employee developer = new Developer(1457, "John", "Smith", 700, 160);
    Employee manager = new Manager(1458, "Sam", "Black", 1000, 192);

    {
        employees.add(developer);
        employees.add(manager);
    }

    @Test
    void toJson() {
        String developerJson = "{\"type\":\"ua.nure.sorokina.Developer\","
                + "\"data\":{\"id\":1457,\"firstName\":\"John\",\""
                + "lastName\":\"Smith\",\"rate\":700,\"actualHoursOfWork\":160}}";
        assertEquals(developerJson, developer.toJson());

        String managerJson = "{\"type\":\"ua.nure.sorokina.Manager\","
                + "\"data\":{\"id\":1458,\"firstName\":\"Sam\",\"lastName\":"
                + "\"Black\",\"rate\":1000,\"actualHoursOfWork\":192}}";
        assertEquals(managerJson, manager.toJson());
    }

    @Test
    void fromJson() {
        String json = "{\"type\":\"ua.nure.sorokina.Developer\","
                + "\"data\":{\"id\":1457,\"firstName\":\"John\",\"lastName\":"
                + "\"Smith\",\"rate\":700,\"actualHoursOfWork\":160}}";
        Employee developerFromJson = Employee.fromJson(json);

        assertEquals(Developer.class.getSimpleName(),
                developerFromJson.getClass().getSimpleName());
    }
}