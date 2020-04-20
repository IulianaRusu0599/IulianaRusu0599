package com.company.model;

public class Salary implements Entity{
    private Integer idSalary;
    private Integer idEmployee;
    private Double salary;

    public Salary(Integer idSalary, Integer idEmployee, Double salary)
    {
        this.idSalary = idSalary;
        this.idEmployee =idEmployee;
        this.salary = salary;
    }

    public Integer getIdSalary() {
        return idSalary;
    }

    public void setIdSalary(Integer idSalary) {
        this.idSalary = idSalary;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Salary{" +
                "idSalary = " + idSalary +
                ", idEmployee = " + idEmployee +
                ", salary = " + salary +
                "}" +"\n" ;
    }


    @Override
    public void printObjectContent() {

    }
}
