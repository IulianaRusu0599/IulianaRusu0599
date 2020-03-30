package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.model.Employee;
import com.company.model.Salary;
import com.company.model.Company;

// expune functii pentru Main
public class MainService {

    private EmployeeService employeeService;
    // salary service
    private SalaryService salaryService;

    public MainService() {
        employeeService = new EmployeeService();
        salaryService = new SalaryService();
    }

    public List<Employee> addEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            employeeService.add(e);
        }
        return employees;
    }

    //order by last name
    public List<Employee> sortEmployeesByName(Boolean ordered) {
        List<Employee> employees = employeeService.getAllNames(ordered);
        return  employees;
    }



    // print ordered by last name
    public void printAllEmployeesOrdByName(Boolean ordered) {
        List<Employee> employees = employeeService.getAllNames(ordered);
        for (Employee e : employees) {
            System.out.println(e.toString());
        }

    }
    public void printEmployees(List<Employee> employeesList)
    {
        for(Employee emp: employeesList)
            System.out.println(emp.toString());
    }


    //pun in list doar salariile actuale ale angajatilor
    public List<Salary> addSalaries(List<Salary> salaries) {
        for (Salary salary : salaries) {
                salaryService.add(salary);
        }
        return salaries;
    }
    public void printSalary(List<Salary> salaryList)
    {
        for(Salary s: salaryList)
            System.out.println(s.toString());
    }
    public void printAllSalary(Boolean ordered) {
        List<Salary> salaries = salaryService.getAllSalary(ordered);

        for (Salary salary : salaries) {
            if(salary.getStatus()==true)
                System.out.println(salary.toString());
        }

    }

    public void printListMap(List<Map<String, Double>> listEmplSalary)
    {
        for(Map<String, Double> map : listEmplSalary) {
            System.out.println(map.keySet()+ "are salariul de" + map.values());
        }
    }

    public void printCompany(Company company) {
        System.out.println(company.toString());
    }
}