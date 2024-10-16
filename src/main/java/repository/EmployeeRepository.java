package repository;

import model.Employee;

import java.util.List;

public interface EmployeeRepository {
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    void removeEmployee(String employeeId);
    Employee searchEmployee(String searchKey);
    List<Employee> getAllEmployees();
}
