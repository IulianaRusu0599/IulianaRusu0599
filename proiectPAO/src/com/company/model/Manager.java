package com.company.model;

public class Manager extends Employee {

    private Salary salary;
    public Manager(Integer id, String firstName, String lastName) {
        super(id, firstName, lastName, null);
    }

    @Override
    public String getJobName() {
        return "Manger";
    }

    @Override
    public String toString() {
        return "{id = " + this.getId() + " firstName = "
                + this.getFirstName() + " lastName = " + this.getLastName() + " role = Manager}\n";
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}