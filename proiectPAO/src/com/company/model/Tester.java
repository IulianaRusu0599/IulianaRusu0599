package com.company.model;

public class Tester extends Employee{
    private Salary salary;

    public Tester(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
    }

    @Override
    public String getJobName() {
        return "Tester";
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Tester{" +
                "salary=" + salary +
                '}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}
