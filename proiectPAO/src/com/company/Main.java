package com.company;

import java.io.FileReader;
import java.util.*;

import com.company.model.*;
import com.company.service.MainService;

public class Main {

    public static void main(String[] args) {
        Employee e0 = new Manager(14, "Mihai", "Luca");
        Employee e1 = new Developer(19, "Radu", "Pop", (Manager) e0);
        Employee e2 = new DataBaseAdmin(23, "Adrian", "Dascalescu", (Manager) e0);
        Employee e3 = new Tester(34, "Liviu", "Petrescu", (Manager) e0);


        CompanyLocation c0 = new CompanyLocation(129,"MyCompany", "Strada Straduintei nr. 9");
        CompanyLocation c1 = new CompanyLocation(324,"MyCompany", "Strada Occidentului");
        CompanyLocation c2 = new CompanyLocation(645,"MyCompany", "Strada Berzei");
        CompanyLocation c3 = new CompanyLocation(829,"MyCompany", "Strada Stirbei Voda");
        CompanyLocation c4 = new CompanyLocation(132,"MyCompany", "Strada Nucsoara");

        Salary s0 = new Salary(14,0, (double) 2500);
        Salary s1 = new Salary(23,1, (double) 1200);
        Salary s2 = new Salary(19,2, (double) 2000);
        Salary s3 = new Salary(34,3, (double) 1500);
        //apelez metoda statica din MainService
        MainService ms = MainService.getInstance();


        while (true) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
//        addEmp, removeEmp, addComp, printEmp
            System.out.println("Ai ales actiunea: " + s);
            switch (s) {
                case "addEmp":
                    ms.addEmployees(Arrays.asList(e0, e1, e2, e3));
                    break;
                case "printEmp":
                    ms.printAllEmployees();
                    break;
                case "printEmpOrdered":
                    ms.printEmployeesOrdered(true);
                    break;
                case "removeEmp":
                    System.out.println("IdToRemove:");
                    String idToRemove = in.next();
                    ms.removeEmployeeFromCSV(idToRemove);
                    break;
                case "addComp":
                    ms.addCompanyLocations(Arrays.asList(c0,c1,c2,c3,c4));
                    break;
                case "printComp":
                    ms.printAllCompanyLocations();
                    break;
                case "removeComp":
                    System.out.println("IdToRemove:");
                    String idComp = in.next();
                    ms.removeCompanyFromCSV(idComp);
                    break;
                case "addSal":
                    System.out.println("Add Salary");
                    ms.addSalaries(Arrays.asList(s0,s1,s2,s3));
                    break;
                case "removeSal":
                    System.out.println("IdEmployeeOfSalaryToRemove:");
                    String idEmplSalary = in.next();
                    ms.removeSalaryFromCSV(idEmplSalary);
                    break;

                default:
                    System.out.println("NU EXISTA");

            }

        }
    }
}





