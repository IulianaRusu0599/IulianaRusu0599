package com.company.model;

public class NetworkAdmin extends Employee{
    private Manager manager;
    private Salary salary;

    public NetworkAdmin(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
        this.manager = manager;
    }

    @Override
    public String getJobName() {
        return "NetworkAdmin";
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{id = " + getId() + " firstName = " + getFirstName() +
                " lastName = " + getLastName() + " role = NetworkAdmin" + " manager = " + getManager() + "}\n";
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}
