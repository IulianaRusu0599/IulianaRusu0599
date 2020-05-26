package com.company.service;

import java.io.File;
import java.util.*;

import com.company.model.*;
import com.company.repository.EmployeeRepository;
import com.company.utils.CSVUtils;

public class EmployeeService {

    private static EmployeeService INSTANCE;
    private List<Employee> employees;

    final static String filePath = System.getProperty("user.dir") + "/data/employees.csv";

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public static EmployeeService getInstance() {

        if (INSTANCE == null)
            INSTANCE = new EmployeeService();
        return INSTANCE;
    }

    public void add(Employee e) {
        if (employees.size() == 0) {
            employees.add(e);
        } else {
            int ok = 1;
            for (int i = 0; i < employees.size(); i++) {
                if (e.getId() == employees.get(i).getId())
                    ok = 0;
            }
            if (ok == 1) {
                employees.add(e);
            }
        }

    }

    public Employee get(Integer id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }


    public List<Employee> getAllNames(Boolean ordered) {

        ArrayList<Employee> employeesCopy = new ArrayList<>(CSVUtils.readEmployeesFromCsv(filePath));

        if (ordered) {
            Collections.sort(employeesCopy, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    int i = o1.getLastName().compareTo(o2.getLastName());
                    if (i == 0) {
                        int i1 = o1.getFirstName().compareTo(o2.getFirstName());
                        return i1;
                    }

                    return i;
                }
            });

            return employeesCopy;
        }

        return employeesCopy;
    }


    public void addToCsv(Employee e) {

        CSVUtils.writeLines(filePath, Arrays.asList(e));
    }

    public List<Employee> readAll() {

        return CSVUtils.readEmployeesFromCsv(filePath);

    }


    public void removeFromCSV(String idToRemove) {
        CSVUtils.removeLineFromFile(idToRemove, new File(filePath));
    }


    //database


    private final static EmployeeRepository EMPLOYEE_REPOSITORY = EmployeeRepository.getInstance();

    private final static SalaryService SALARY_SERVICE = SalaryService.getInstance();

    public List<Employee> selectAllEmployees() {
        List<Employee> all = EMPLOYEE_REPOSITORY.findAll();

        // trebuie query la baza de date sa pun salariul

        for (Employee e: all) {
            Integer id = e.getId();
            Salary salary = SALARY_SERVICE.findSalary(id);

            e.setSalary(salary);
        }

        return all;
    }

    public void findEmployee(Integer idEmployee) {
        EMPLOYEE_REPOSITORY.findEmployee(idEmployee);
    }

    public Employee saveEmployee(Integer id, String lastName, String firstName, String type) {
        Employee e = null;
        switch (type) {
            case "Developer":
                e = new Developer(id, firstName, lastName, null);
                break;
            case "Manager":
                e = new Manager(id, firstName, lastName);
                break;
            case "DataBaseAdmin":
                e = new DataBaseAdmin(id, firstName, lastName, null);
                break;
            case "NetworkAdmin":
                e = new NetworkAdmin(id, firstName, lastName, null);
                break;
            case "Tester":
                e = new Tester(id, firstName, lastName, null);
                break;
            case "WebDesigner":
                e = new WebDesigner(id, firstName, lastName, null);
                break;
            default:
        }

        return EMPLOYEE_REPOSITORY.insertOrUpdate(e);

    }


    public boolean deleteEmployee(Integer idEmployee) {
        return EMPLOYEE_REPOSITORY.deleteEmployee(idEmployee);
    }


}