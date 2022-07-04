package pro.sky.course_2_lesson_7.service;

import org.springframework.stereotype.Service;
import pro.sky.course_2_lesson_7.exception.EmployeeAlreadyAddedException;
import pro.sky.course_2_lesson_7.exception.EmployeeNotFoundException;
import pro.sky.course_2_lesson_7.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(getKey(employee), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            return employees.remove(getKey(employee));
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }
}
