package com.company.model;

import java.util.List;

public class Company implements Entity{
    private Integer idCompany;
    private String companyName;
    private List<Employee> employees;
    private List<Salary> salaries;
    public Company(Integer idCompany, String companyName, List<Employee> employees, List<Salary> salaries)
    {
        this.idCompany = idCompany;
        this.companyName = companyName;
        this.employees = employees;
        this.salaries =  salaries;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "Company{" +"\n"+
                "idCompany = " + idCompany + ",\n" +
                "companyName = '" + companyName + '\'' +",\n"+
                "employees = " + employees + ",\n" +
                "salaries = " + salaries + ",\n}";
    }
}
