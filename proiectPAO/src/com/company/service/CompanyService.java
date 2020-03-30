package com.company.service;

import com.company.model.Company;

public class CompanyService {
    private Company company;

    public void printCompany(Company company)
    {
        System.out.println(company.toString());
    }
}
