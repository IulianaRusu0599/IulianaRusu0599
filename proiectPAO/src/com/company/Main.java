package com.company;

import java.io.FileReader;
import java.util.*;

import com.company.model.*;
import com.company.service.MainService;

public class Main {

    public static void main(String[] args) {
        Employee e0 = new Manager(14,"Mihai","Luca");
        Employee e1 = new Developer(19, "Radu", "Pop", (Manager) e0);
        Employee e2 = new DataBaseAdmin(23, "Adrian", "Dascalescu", (Manager)e0);
        Employee e3 = new Tester(34, "Liviu", "Petrescu", (Manager)e0);


        //aici apelez metoda statica din MainService
        MainService ms = MainService.getInstance();
        ms.addEmployees(Arrays.asList(e0,e1,e2,e3));

        ms.printAllEmployees();

        ms.printEmployeesOrdered(true);


    }
}





