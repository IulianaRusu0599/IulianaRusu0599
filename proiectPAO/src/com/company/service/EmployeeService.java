package com.company.service;

import java.io.File;
import java.util.*;

import com.company.model.Employee;
import com.company.model.Salary;
import com.company.utils.CSVUtils;

public class EmployeeService {

    private static EmployeeService INSTANCE;
    private List<Employee> employees;

    final static String filePath = System.getProperty("user.dir") + "/data/employees.csv";

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public static EmployeeService getInstance() {

        if(INSTANCE == null)
            INSTANCE = new EmployeeService();
        return INSTANCE;
    }

    public void add(Employee e) {
        if (employees.size() == 0)
        {
            employees.add(e);
        }else{
            int ok = 1;
            for(int i = 0; i < employees.size(); i++) {
                if(e.getId() == employees.get(i).getId())
                    ok = 0;
            }
            if(ok == 1) {
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


    public void removeFromCSV(String idToRemove)
    {
        CSVUtils.removeLineFromFile(idToRemove, new File(filePath));
    }

}