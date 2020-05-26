package com.company.model;

public class NetworkAdmin extends Employee{
    private Salary salary;

    public NetworkAdmin(Integer id, String firstName, String lastName, Manager manager) {
        super(id, firstName, lastName, manager);
    }

    @Override
    public String getJobName() {
        return "NetworkAdmin";
    }



    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "NetworkAdmin{" +
                "salary=" + salary +
                '}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }
}
