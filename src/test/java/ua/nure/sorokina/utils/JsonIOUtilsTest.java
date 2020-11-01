package ua.nure.sorokina.utils;

import org.junit.jupiter.api.Test;
import ua.nure.sorokina.Developer;
import ua.nure.sorokina.Employee;
import ua.nure.sorokina.Manager;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonIOUtilsTest {

    List<Employee> employees = new LinkedList<>();
    Employee developer = new Developer(1457, "John", "Smith", 700, 160);
    Employee manager = new Manager(1458, "Sam", "Black", 1000, 192);

    {
        employees.add(developer);
        employees.add(manager);
    }

    @Test
    void writeToFile() throws IOException {
        String expectedFileOutput = "[\n{\"type\":\"ua.nure.sorokina.Developer\","
                + "\"data\":{\"id\":1457,\"firstName\":\"John\",\"lastName\":"
                + "\"Smith\",\"rate\":700,\"actualHoursOfWork\":160}},\n"
                + "{\"type\":\"ua.nure.sorokina.Manager\",\"data\":{\"id\":1458,"
                + "\"firstName\":\"Sam\",\"lastName\":\"Black\",\"rate\":1000,"
                + "\"actualHoursOfWork\":192}}\n]";

        JsonIOUtils.writeToFile(employees);

        File file = new File("employees.json");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String str = new String(data, "UTF-8");
        assertEquals(expectedFileOutput, str);

    }

    @Test
    void readFromFile() {
        List<Employee> employeeList = JsonIOUtils.readFromFile();

        assertEquals(employeeList.get(0), employees.get(0));
        assertEquals(employeeList.get(1), employees.get(1));
    }
}