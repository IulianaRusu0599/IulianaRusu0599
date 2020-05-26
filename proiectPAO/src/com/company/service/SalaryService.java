package com.company.service;

import com.company.model.Salary;
import com.company.model.Employee;
import com.company.repository.SalaryRepository;
import com.company.utils.CSVUtils;

import java.io.File;
import java.util.*;


public class SalaryService {
    private static SalaryService INSTANCE;
    private List<Salary> salaryList;

    final static String filePath = System.getProperty("user.dir") + "/data/salaries.csv";

    public SalaryService() {
        salaryList = new ArrayList<>();
    }

    public static SalaryService getInstance() {

        if (INSTANCE == null)
            INSTANCE = new SalaryService();
        return INSTANCE;

    }

    public void add(Salary s) {
        if (salaryList.size() == 0) {
            salaryList.add(s);
        } else {
            int ok = 1;
            for (int i = 0; i < salaryList.size(); i++) {
                if (s.getIdSalary() == salaryList.get(i).getIdSalary())
                    ok = 0;
            }
            if (ok == 1) {
                salaryList.add(s);
            }
        }
    }

    public Salary get(Integer id) {
        for (Salary s : salaryList) {
            if (s.getIdSalary().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Salary> getAllSalary(Boolean ordered) {
        // copie
        ArrayList<Salary> salariesCopy = new ArrayList<>(this.salaryList);

        if (ordered) {
            Collections.sort(salariesCopy, new Comparator<Salary>() {
                @Override
                public int compare(Salary s1, Salary s2) {
                    int i = s1.getSalary().compareTo(s2.getSalary());

                    return i;
                }
            });
            Collections.reverse(salariesCopy);
            return salariesCopy;
        }

        return salariesCopy;
    }


    public void addToCsv(Salary s) {

        CSVUtils.writeSalaries(filePath, Arrays.asList(s));
    }


    public void removeFromCSV(String idToRemove) {
        CSVUtils.removeLineFromFile(idToRemove, new File(filePath));
    }


    //metode pt database

    private final SalaryRepository salaryRepository = SalaryRepository.getInstance();

    public Salary findSalary(Integer idEmployee) {
        return salaryRepository.findSalary(idEmployee);
    }

    public Salary saveSalary(Integer idSalary, Integer idEmployee, Double salary) {
        Salary s = new Salary();
        s.setIdSalary(idSalary);
        s.setIdEmployee(idEmployee);
        s.setSalary(salary);

        return salaryRepository.saveSalary(s);
    }


    public boolean deleteSalary(Integer idEmployee) {
        return salaryRepository.deleteSalary(idEmployee);
    }

    public List<Salary> selectAllSalaries() {
        return salaryRepository.findAll();
    }

}