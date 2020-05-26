package com.company.model;

public class DataBaseAdmin extends Employee{
    private Salary salary;

    public DataBaseAdmin(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
    }


    @Override
    public String getJobName() {
        return "DataBaseAdmin";
    }



    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() +"--> DataBaseAdmin <--{" +
                "salary=" + salary +
                '}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}
