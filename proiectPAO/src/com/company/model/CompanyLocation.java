package com.company.model;

import java.util.Arrays;
import java.util.List;

public class CompanyLocation implements Entity{
    private Integer idCompany;
    private String companyName;
    private String companyAddress;


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
        return "Company{" +
                "idCompany=" + idCompany +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='"+companyAddress +'\'' +
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
