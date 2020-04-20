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
                    break;
                case "removeSal":
                    break;

                default:
                    System.out.println("NU EXISTA");

            }

        }
    }
}





