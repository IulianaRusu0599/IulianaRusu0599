package com.company.model;

public class WebDesigner extends Employee{
    private Salary salary;

    public WebDesigner(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
    }

    @Override
    public String getJobName() {
        return "WebDesigner";
    }



    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "WebDesigner{" +
                "salary=" + salary +
                '}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}
