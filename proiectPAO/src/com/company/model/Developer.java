package com.company.model;

public class Developer extends Employee {

    private Manager manager;
    private Salary salary;

    public Developer(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
        this.manager = manager;
    }



    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
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
                " lastName = " + getLastName() + " role = Developer" + " manager = " + getManager() + "}\n";


//        return "Developer {" +
//                "id = " + this.getId() + "; " +
//                "manager=" + manager +
//                '}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}