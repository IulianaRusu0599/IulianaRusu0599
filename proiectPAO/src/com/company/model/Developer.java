package com.company.model;

public class Developer extends Employee {

    private Salary salary;

    public CompanyLocation getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(CompanyLocation companyLocation) {
        this.companyLocation = companyLocation;
    }

    private CompanyLocation companyLocation;

    public Developer(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
    }



    public Salary getSalary() {
        return salary;
    }

    @Override
    public void setSalary(Salary salary) {
        this.salary = salary;
    }


    @Override
    public String getJobName() {
        return "Developer";
    }

    @Override
    public String toString() {
        return super.toString() + "--> Developer <--{" +
                "salary=" + salary +'}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}