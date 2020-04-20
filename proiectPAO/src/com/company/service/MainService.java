package com.company.service;

import java.util.List;

import com.company.model.Employee;
import com.company.model.Salary;
import com.company.model.CompanyLocation;

// expune functii pentru Main
// clasa singleton
public final class MainService {
    private static MainService INSTANCE;

    private EmployeeService employeeService;
    private SalaryService salaryService;
    private CompanyService companyService;
    private AuditService auditService;
    private MainService() {
        employeeService = EmployeeService.getInstance();
        salaryService = SalaryService.getInstance();
        companyService = CompanyService.getInstance();
        auditService = AuditService.getInstance();
    }
    public static MainService getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new MainService();
        return INSTANCE;
    }

    public List<Employee> addEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            employeeService.addToCsv(e);
        }
        return employees;
    }

    //order by last name
    public List<Employee> sortEmployeesByName(Boolean ordered) {
        List<Employee> employees = employeeService.getAllNames(ordered);
        return  employees;
    }


    public void printAllEmployees() {
        List<Employee> employees = employeeService.readAll();
        System.out.println("Afisare employees: ");
        System.out.println(employees.size());
        for(Employee employee: employees)
        {
            System.out.println(employee.toString());
        }
    }


    // print ordered by last name
    public void printEmployeesOrdered(Boolean ordered) {
        List<Employee> employees = employeeService.getAllNames(ordered);
        System.out.println("Afisare angajati in ordine alfabetica: ");
        for (Employee e : employees) {
            System.out.println(e.toString());
        }

    }

    public void removeEmployeeFromCSV(String idToRemove)
    {
        employeeService.removeFromCSV(idToRemove);
    }

    public List<CompanyLocation> addCompanyLocations(List<CompanyLocation> companyLocations) {
        for (CompanyLocation comp : companyLocations) {
            companyService.addToCsv(comp);
        }
        return companyLocations;
    }

    public void printAllCompanyLocations() {
        List<CompanyLocation> companies = companyService.readAll();
        System.out.println("Afisare locatii companie: ");

        for(CompanyLocation companyLocation: companies)
        {
            System.out.println(companyLocation.toString());
        }
    }


    public void removeCompanyFromCSV(String idToRemove)
    {
        companyService.removeFromCSV(idToRemove);
    }



    //salary
    public List<Salary> addSalaries(List<Salary> salaries) {
        for (Salary salary : salaries) {
                salaryService.add(salary);
        }
        return salaries;
    }

    public void printAllSalary(Boolean ordered) {
        List<Salary> salaries = salaryService.getAllSalary(ordered);

        for (Salary salary : salaries) {
                System.out.println(salary.toString());
        }


    }

//    public void printListMap(List<Map<String, Double>> listEmplSalary)
//    {
//        for(Map<String, Double> map : listEmplSalary) {
//            System.out.println(map.keySet()+ "are salariul de" + map.values());
//        }
//    }

    public void printCompany(CompanyLocation company) {
        System.out.println(company.toString());
    }


}