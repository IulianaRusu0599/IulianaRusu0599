package com.company.service;

import com.company.model.Salary;
import com.company.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class SalaryService {

    private List<Salary> salaryList;

    public SalaryService() {
        salaryList = new ArrayList<>();
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

            return salariesCopy;
        }

        return salariesCopy;
    }

}