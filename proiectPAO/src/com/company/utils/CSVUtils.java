package com.company.utils;

import com.company.model.*;
import com.company.service.AuditService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {
    private static final char COMMA_SEPARATOR = ',';
    private static final String DOUBLE_QUOTE = "\"";

    public static void removeLineFromFile(String idToRemove, File f) {
        String actiune = "Remove employees from CSV file";
        AuditService auditService = new AuditService();
        auditService.addToAudit(actiune);
        try {
            //Reading File Content and storing it to a StringBuilder variable ( skips lineToRemove)
            StringBuilder newFileContent = new StringBuilder();

            try (Scanner sc = new Scanner(f)) {
                String currentLine;

                while (sc.hasNext()) {
                    currentLine = sc.nextLine();
                    // caut id ul ca si string de la 0 la prima virgula
                    // elimin " de la inceput
                    String id = currentLine.split(String.valueOf(COMMA_SEPARATOR))[0].replace("\"", "");

                    if (id.equals(idToRemove)) {
                        continue; //skips id
                    }

                    newFileContent.append(currentLine).append("\n");
                }
            }

            //Delete File Content
            PrintWriter pw = new PrintWriter(f);
            pw.close();


            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            writer.append(newFileContent.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //returnez obiectul dupa id
    public static Employee readManager(String filePath, Integer idEmp) {


        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {

                String[] employee1 = line.split(String.valueOf(COMMA_SEPARATOR));
                String[] emp = new String[employee1.length];

                for (int i = 0; i < employee1.length; i++) {
                    emp[i] = employee1[i].replace("\"", "");
                }

                int id = Integer.parseInt(emp[0]);
                if (id == idEmp) {
//                        if (emp[3].equals("Manager"))
//                        { System.out.println("da");
//                            return new Manager(Integer.parseInt(emp[0]), emp[1], emp[2]);}
//                        if (emp[3].equals("Developer"))
//                            return new Developer(Integer.parseInt(emp[0]), emp[1], emp[2], null);
//                        if (emp[3].equals("Tester"))
//                            return new Tester(Integer.parseInt(emp[0]), emp[1], emp[2], null);
//                        if (emp[3].equals("WebDesigner"))
//                            return new WebDesigner(Integer.parseInt(emp[0]), emp[1], emp[2], null);
//                        if (emp[3].equals("NetworkAdmin"))
//                            return new NetworkAdmin(Integer.parseInt(emp[0]), emp[1], emp[2], null);
//                        if (emp[3].equals("DataBaseAdmin"))
//                            return new DataBaseAdmin(Integer.parseInt(emp[0]), emp[1], emp[2], null);
                    return new Manager(Integer.parseInt(emp[0]), emp[1], emp[2]);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //returnez toti angajatii din fisier
    public static List<Employee> readEmployeesFromCsv(String filePath) {
        String actiune = "Get_Employees_From_CSV_File";
        AuditService auditService = new AuditService();
        auditService.addToAudit(actiune);
        List<Employee> employees = new ArrayList<>();
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {

                String[] employee1 = line.split(String.valueOf(COMMA_SEPARATOR));
                String[] employee = new String[employee1.length];
                //elimin "" din string-ul de cuvinte
                for (int i = 0; i < employee1.length; i++) {
                    employee[i] = employee1[i].replace("\"", "");
                }

                Integer id = Integer.parseInt(employee[0]);
                Integer manager = Integer.parseInt(employee[4]);
                if (employee[3].equals("Manager")) {
                    Employee employeeFromCsv = new Manager(id, employee[1], employee[2]);
                    employees.add(employeeFromCsv);
                }
                if (employee[3].equals("Developer")) {
                    //caut managerul
                    Employee manager1 = readManager(filePath, manager);
                    Employee employeeFromCsv = new Developer(id, employee[1], employee[2], (Manager) manager1);
                    employees.add(employeeFromCsv);
                }
                if (employee[3].equals("DataBaseAdmin")) {
                    Employee manager1 = readManager(filePath, manager);
                    Employee employeeFromCsv = new DataBaseAdmin(id, employee[1], employee[2], (Manager) manager1);
                    employees.add(employeeFromCsv);
                }
                if (employee[3].equals("NetworkAdmin")) {
                    Employee manager1 = readManager(filePath, manager);
                    Employee employeeFromCsv = new NetworkAdmin(id, employee[1], employee[2], (Manager) manager1);
                    employees.add(employeeFromCsv);
                }
                if (employee[3].equals("Tester")) {
                    Employee manager1 = readManager(filePath, manager);
                    Employee employeeFromCsv = new Tester(id, employee[1], employee[2], (Manager) manager1);
                    employees.add(employeeFromCsv);
                }
                if (employee[3].equals("WebDesigner")) {
                    Employee manager1 = readManager(filePath, manager);
                    Employee employeeFromCsv = new WebDesigner(id, employee[1], employee[2], (Manager) manager1);
                    employees.add(employeeFromCsv);
                }
            }

            return employees;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    // wordsOfRow - campurile unui obiect separate in string uri
    public static void writeLine(FileWriter writer, List<String> wordsOfRow) {
        try {
            boolean isFirst = true;

            StringBuilder sb = new StringBuilder();

            for (String value : wordsOfRow) {
                if (!isFirst) {
                    sb.append(COMMA_SEPARATOR);
                }

                sb.append(DOUBLE_QUOTE).append(value).append(DOUBLE_QUOTE);

                isFirst = false;
            }

            sb.append("\n");

            writer.append(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeLines(String csvFile, List<Employee> employees) {
        FileWriter fileWriter;
        boolean appendDataAtTheEnd = true;
        try {
            String actiune = "Add employees in CSV file";
            AuditService auditService = new AuditService();
            auditService.addToAudit(actiune);
            fileWriter = new FileWriter(csvFile, appendDataAtTheEnd);

            for (Employee employee : employees) {
                List<String> employeeAsString = null;
                if (employee instanceof Manager) {
                    Manager manager = (Manager) employee;
                    employeeAsString = Arrays.asList(manager.getId().toString(), manager.getFirstName(),
                            manager.getLastName(), manager.getJobName(), "-1"); // -1 pentru ca nu are MANAGER
                }
                if (employee instanceof Developer) {
                    Developer developer = (Developer) employee;
                    employeeAsString = Arrays.asList(developer.getId().toString(), developer.getFirstName(),
                            developer.getLastName(), developer.getJobName(), developer.getManager().getId().toString());
                }

                if (employee instanceof Tester) {
                    Tester tester = (Tester) employee;
                    employeeAsString = Arrays.asList(tester.getId().toString(), tester.getFirstName(),
                            tester.getLastName(), tester.getJobName(), tester.getManager().getId().toString()); // -1 pentru ca nu are MANAGER
                }
                if (employee instanceof WebDesigner) {
                    WebDesigner webDesigner = (WebDesigner) employee;
                    employeeAsString = Arrays.asList(webDesigner.getId().toString(), webDesigner.getFirstName(),
                            webDesigner.getLastName(), webDesigner.getJobName(), webDesigner.getManager().getId().toString()); // -1 pentru ca nu are MANAGER
                }
                if (employee instanceof DataBaseAdmin) {
                    DataBaseAdmin dataBaseAdmin = (DataBaseAdmin) employee;
                    employeeAsString = Arrays.asList(dataBaseAdmin.getId().toString(), dataBaseAdmin.getFirstName(),
                            dataBaseAdmin.getLastName(), dataBaseAdmin.getJobName(), dataBaseAdmin.getManager().getId().toString()); // -1 pentru ca nu are MANAGER
                }
                if (employee instanceof NetworkAdmin) {
                    NetworkAdmin networkAdmin = (NetworkAdmin) employee;
                    employeeAsString = Arrays.asList(networkAdmin.getId().toString(), networkAdmin.getFirstName(),
                            networkAdmin.getLastName(), networkAdmin.getJobName(), networkAdmin.getManager().getId().toString()); // -1 pentru ca nu are MANAGER
                }

                //fiecarui angajat care este deja in fisier, ii modific datele, daca este cazul
                //iar daca nu este in fisier, il adaug
                removeLineFromFile(employee.getId().toString(), new File(csvFile));
                CSVUtils.writeLine(fileWriter, employeeAsString);
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //metode pt companie
    public static void writeCompanies(String csvFile, List<CompanyLocation> companyLocations) {
        FileWriter fileW;
        boolean appendDataAtTheEnd = true;
        try {
            String actiune = "Add_Company_Locations_In_CSV_File";
            AuditService auditService = new AuditService();
            auditService.addToAudit(actiune);
            fileW = new FileWriter(csvFile, appendDataAtTheEnd);

            for (CompanyLocation companyLocation : companyLocations) {
                List<String> companyAsString = null;

                companyAsString = Arrays.asList(companyLocation.getIdCompany().toString(), companyLocation.getCompanyName(), companyLocation.getCompanyAddress());
                removeLineFromFile(companyLocation.getIdCompany().toString(), new File(csvFile));
                CSVUtils.writeLine(fileW, companyAsString);
            }

            fileW.flush();
            fileW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static List<CompanyLocation> readCompanyLocationsFromCsv(String filePath) {
        String actiune = "Get_Locations_From_CSV_File";
        AuditService auditService = new AuditService();
        auditService.addToAudit(actiune);
        List<CompanyLocation> companies = new ArrayList<>();
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {

                String[] locations1 = line.split(String.valueOf(COMMA_SEPARATOR));
                String[] locations = new String[locations1.length];

//                elimin "" din string-ul de cuvinte
                for (int i = 0; i < locations1.length; i++) {
                    locations[i] = locations1[i].replace("\"", "");
                }

                Integer id = Integer.parseInt(locations[0]);

                CompanyLocation compFromCSV = new CompanyLocation(id, locations[1], locations[2]);
                companies.add(compFromCSV);

            }

            return companies;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

}


}