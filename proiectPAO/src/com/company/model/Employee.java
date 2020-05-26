package com.company.model;

public abstract class Employee implements Entity {
    private Integer id;
    private String firstName;
    private String lastName;
    private Manager manager;

    public Employee(Integer id, String firstName, String lastName, Manager manager) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.manager = manager;
    }

    public abstract String getJobName();

    public abstract void setSalary(Salary salary);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
//

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", manager=" + manager +
                '}';
    }
}