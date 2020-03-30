package com.company;

import java.util.*;

import com.company.model.*;
import com.company.service.MainService;

public class Main {

    public static void main(String[] args) {

        Employee e4 = new Manager(8, "Andrei", "Petre");

        Employee e0 = new Developer(1, "Ana", "Popescu", (Manager) e4);
        Employee e1 = new Developer(5, "Ion", "Axinte", (Manager) e4);
        Employee e2 = new Developer(3, "Dan", "Ilie", (Manager) e4);
        Employee e3 = new Developer(6, "Maria", "Sarbu", (Manager) e4);

        Employee e5 = new Developer(10, "Ioana", "Macin", (Manager) e4);

        MainService ms = new MainService();
        List<Employee> employeeList = ms.addEmployees(Arrays.asList(e0, e1, e2, e3, e4, e5));
        //ms.printEmployees(employeeList);
        //true = nu sunt ordonati
        List<Employee> lista = ms.sortEmployeesByName(true);
        System.out.println("\n");
        ms.printEmployees(lista);
//      ms.printAllEmployeesOrdByName(true);

        System.out.println("\n");

        //id-ul unui angajat nu se schimba
        //se poate schimba id-ul salariului
        Salary s0 = new Salary(9,  8, (double) 1400, false);
        Salary s1 = new Salary(43, 1,   2910.50, true);
        Salary s2 = new Salary(15, 6,   3700.5, true);
        Salary s3 = new Salary(17, 3,  (double) 2200, true);
        Salary s4 = new Salary(13, 8,  (double) 5350, true);
        Salary s5 = new Salary(4,  5,  (double) 2000, true);
        Salary s6 = new Salary(23, 10, (double) 2550, true);

        List<Salary> listSalary = ms.addSalaries(Arrays.asList(s0, s1, s2, s3, s4, s5, s6));
        System.out.println("\nActive salaries in ascending order:\n");
        ms.printAllSalary(true);

        System.out.println("\nAll salaries in ascending order:\n");
        ms.printSalary(listSalary);

        //fac un map cu employee si salary

        List<Map<String,Double>> listEmployees = new ArrayList<Map<String, Double>>();

        //lucrez cu employeeList si listSalary
        for (Employee employee: lista){
            for (Salary salary: listSalary)
            {
                if (employee.getId() == salary.getIdEmployee() && salary.getStatus()== true) {
                    Map<String, Double> mapEmployees = new HashMap<String, Double>();
                    String numeComplet = employee.getLastName() + " " + employee.getFirstName() +  " ";
                    mapEmployees.put(numeComplet, salary.getSalary());
                    listEmployees.add(mapEmployees);
                }

            }
        }

          System.out.println("\nEmployees and salaries:\n");
          ms.printListMap(listEmployees);

        Company company = new Company(132, "FreshBooks", employeeList, listSalary);
        ms.printCompany(company);

    }
}