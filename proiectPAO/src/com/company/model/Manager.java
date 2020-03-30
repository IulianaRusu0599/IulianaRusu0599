package com.company.model;

public class Manager extends Employee {

    public Manager(Integer id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    public String getJobName() {
        return "Manger";
    }

    @Override
    public String toString() {
        return "{id = " + this.getId() + " firstName = "
                + this.getFirstName() + " lastName = " + this.getLastName() + " role = MANAGER}\n";
    }
}