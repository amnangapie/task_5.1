package ua.nure.sorokina.utils;

import ua.nure.sorokina.Employee;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JsonIOUtils {

    public static final String FILE_NAME = "output/employees.json";

    /* file will be overwritten every time */
    public static synchronized void writeToFile(List<Employee> employees) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.append("[\n");
            Iterator<Employee> employee = employees.iterator();
            while (employee.hasNext()) {
                writer.append(employee.next().toJson());
                if (employee.hasNext()) {
                    writer.append(",\n");
                }
            }
            writer.append("\n]");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to write in file");
            e.printStackTrace();
        }
    }

    public static List<Employee> readFromFile() {
        List<Employee> employees = new LinkedList<>();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String json = scanner.nextLine();
                if (!json.equals("[") && !json.equals("]")) {
                    if (json.lastIndexOf(',') == json.length() - 1) {
                        json = json.substring(0, json.lastIndexOf(','));
                    }
                    employees.add(Employee.fromJson(json));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to write in file");
            e.printStackTrace();
        }
        return employees;
    }
}
