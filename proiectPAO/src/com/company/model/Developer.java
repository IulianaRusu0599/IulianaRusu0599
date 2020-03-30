package com.company.model;

public class Developer extends Employee {

    private Manager manager;

    public Developer(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName);
        this.manager = manager;
    }

    @Override
    public String getJobName() {
        return "Developer";
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "{id = " + getId() + " firstName = " + getFirstName() +
                " lastName = " + getLastName() + " role = DEVELOPER" + " manager = " + getManager() + "}\n";


//        return "Developer {" +
//                "id = " + this.getId() + "; " +
//                "manager=" + manager +
//                '}';
    }
}