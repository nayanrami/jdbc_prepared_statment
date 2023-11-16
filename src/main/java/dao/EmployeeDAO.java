package dao;

import in.ac.adit.practice.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    public boolean saveEmployee(Employee employee);
    public Employee getEmployee(long id);
    public Employee updateEmployee(Employee oldObj, Employee newObj);
    public boolean removeEmployee(long id);
    public List<Employee> getAllEmployee();
}
