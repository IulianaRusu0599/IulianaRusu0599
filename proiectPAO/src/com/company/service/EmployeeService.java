package com.company.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.company.model.Employee;
import com.company.model.Salary;

public class EmployeeService {

    private List<Employee> employees;

    public EmployeeService() {
        employees = new ArrayList<>();
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
        // copie
        ArrayList<Employee> employeesCopy = new ArrayList<>(this.employees);

        if (ordered) {
            Collections.sort(employeesCopy, new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    int i = o1.getLastName().compareTo(o2.getLastName());
                    // daca lastName e la fel
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
}