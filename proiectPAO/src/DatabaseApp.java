import com.company.model.CompanyLocation;
import com.company.model.Employee;
import com.company.model.Salary;
import com.company.service.CompanyService;
import com.company.service.EmployeeService;
import com.company.service.SalaryService;

import java.util.List;
import java.util.Scanner;

public class DatabaseApp {
    private static final SalaryService salaryService = SalaryService.getInstance();
    private static final EmployeeService employeeService = EmployeeService.getInstance();
    private static final CompanyService companyService = CompanyService.getInstance();

    public static void main(String[] args) throws InterruptedException {
        boolean isOpen = true;
        String options = "S = salary options; E = employee options; C = company options";

        SalaryService salaryService = SalaryService.getInstance();

        while (isOpen) {
            System.out.println("Alege una dintre optiunile: " + options);

            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            System.out.println("Ai ales actiunea: " + s);
            switch (s) {
                case "S":
                    System.out.println("salary");
                    String opt = "c = citire(afisare); f = cautare; s = scriere(adaugare); del = stergere;";
                    System.out.println("Alege instructiunile:" + opt);
                    String input = in.nextLine();
                    switch (input) {
                        case "c":
                            System.out.println("salary citire");
                            List<Salary> salaries = salaryService.selectAllSalaries();
                            for (Salary salary : salaries) {
                                System.out.println(salary);
                            }
                            break;
                        case "f":
                            System.out.println("select salary");
                            System.out.println("Introduceti idEmployee: ");
                            String r = in.nextLine();
                            Salary sal = salaryService.findSalary(Integer.parseInt(r));
                            System.out.println(sal);
                            break;
                        case "s":
                            System.out.println("salary scriere");
                            System.out.println("Introduceti: idSalary, idEmployee, salary;  Exemplu: 0,0,1000");
                            String read = in.nextLine();
                            String[] str = read.split(",");
                            salaryService.saveSalary(Integer.parseInt(str[0].trim()),
                                    Integer.parseInt(str[1].trim()),
                                    Double.valueOf(str[2].trim()));
                            break;
                        case "del":
                            System.out.println("salary stergere");
                            System.out.println("Introduceti idEmployee de sters: ");
                            String id = in.nextLine();
                            salaryService.deleteSalary(Integer.parseInt(id));
                            break;
                        default:
                            System.out.println("INCORECT!");
                    }
                    break;
                case "E":
                    System.out.println("employee");
                    String option = "c = citire; f = select; s = scriere; del = stergere;";
                    System.out.println("Alege instructiunile:" + option);
                    String inp = in.nextLine();
                    switch (inp) {
                        case "c":
                            System.out.println("citire employees");
                            List<Employee> employees = employeeService.selectAllEmployees();
                            for (Employee employee : employees) {
                                System.out.println(employee);
                            }
                            break;
                        case "s":
                            System.out.println("Introduceti id, lastName, firstName, type");
                            String read = in.nextLine();
                            String[] str = read.split(",");
                            employeeService.saveEmployee(Integer.parseInt(str[0]), str[1], str[2], str[3]);
                            break;
                        case "f":
                            System.out.println("select employee");
                            System.out.println("Introduceti idEmployee: ");
                            String r = in.nextLine();
                            employeeService.findEmployee(Integer.parseInt(r));
                            break;
                        case "del":
                            System.out.println("stergere employee");
                            System.out.println("Introduceti idEmployee de sters: ");
                            String id = in.nextLine();
                            // cand sterg employee sterg si salary
                            employeeService.deleteEmployee(Integer.parseInt(id));
                            break;
                        default:
                    }
                    break;
                case "C":
                    String opt2 = "c = citire; s = scriere; f = select; del = stergere;";
                    System.out.println("Alege instructiunile:" + opt2);
                    String inp1 = in.nextLine();
                    switch (inp1) {
                        case "c":
                            System.out.println("citire companyLocations");
                            List<CompanyLocation> locations = companyService.selectAllCompanyLocations();
                            for (CompanyLocation location : locations) {
                                System.out.println(location);
                            }
                            break;
                        case "s":
                            System.out.println("Introduceti id, idEmp, idComp, adress");
                            String read = in.nextLine();
                            String[] str = read.split(",");
                            companyService.addLocation(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
                                    Integer.parseInt(str[2]), str[3]);
                            break;
                        case "f":
                            System.out.println("select companyLocation");
                            System.out.println("Introduceti idCompanyLocation: ");
                            String r = in.nextLine();
                            companyService.findCompanyLocation(Integer.parseInt(r));
                            break;
                        case "del":
                            System.out.println("stergere location");
                            System.out.println("Introduceti idEmployee de sters: ");
                            String idEmp = in.nextLine();
                            companyService.deleteCompanyLocation(Integer.parseInt(idEmp));
                            break;
                        default:
                    }
                    break;
                case "X":
                    isOpen = false;
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("INCORECT!");
            }

        }
    }
}
