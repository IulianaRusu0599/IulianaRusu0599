package com.company.model;

public class Salary implements Entity{
    private Integer idSalary;
    private Integer idEmployee;
    private Double salary;
    private Boolean status; // activ sau nu, un employee isi poate schimba functia in ierarhia organizatiei

    public Salary(Integer idSalary, Integer idEmployee, Double salary, Boolean status)
    {
        this.idSalary = idSalary;
        this.idEmployee =idEmployee;
        this.salary = salary;
        this.status =status;
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

    public Boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "idSalary = " + idSalary +
                ", idEmployee = " + idEmployee +
                ", salary = " + salary +
                ", status = " + status +
                "}" +"\n" ;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
