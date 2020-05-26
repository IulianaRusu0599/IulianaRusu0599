package com.company.model;

import java.util.Arrays;
import java.util.List;

public class CompanyLocation implements Entity{
    private Integer idCompany;
    private String companyName;
    private String companyAddress;
    private  Integer idEmployee;

    public CompanyLocation() {

    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    // pt db
    public CompanyLocation(Integer idCompany, String companyName, String companyAddress, Integer idEmployee) {
        this.idCompany = idCompany;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.idEmployee = idEmployee;
    }

    // pt csv
    public CompanyLocation(Integer idCompany, String companyName, String companyAddress) {
        this.idCompany = idCompany;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "CompanyLocation{" +
                "idCompany=" + idCompany +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", idEmployee=" + idEmployee +
                '}';
    }

    @Override
    public void printObjectContent() {
        System.out.println(this.toString());
    }

    public Integer getId() {
        return idCompany;
    }

}
