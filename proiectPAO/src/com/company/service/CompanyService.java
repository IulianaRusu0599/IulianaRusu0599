package com.company.service;

import com.company.model.CompanyLocation;
import com.company.model.Salary;
import com.company.repository.CompanyLocationsRepository;
import com.company.utils.CSVUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyService {
    private static CompanyService INSTANCE;
    private List<CompanyLocation> companies;

    final static String filePath = System.getProperty("user.dir") + "/data/companies.csv";

    public CompanyService() {
        companies = new ArrayList<>();
    }


    public static CompanyService getInstance() {

        if(INSTANCE == null)
            INSTANCE = new CompanyService();
        return INSTANCE;

    }



    public void addToCsv(CompanyLocation c) {

        CSVUtils.writeCompanies(filePath, Arrays.asList(c));
    }

    public List<CompanyLocation> readAll() {

        return CSVUtils.readCompanyLocationsFromCsv(filePath);

    }

    public void removeFromCSV(String idToRemove)
    {
        CSVUtils.removeLineFromFile(idToRemove, new File(filePath));
    }

    public CompanyLocation get(Integer id) {
        for (CompanyLocation c : companies) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
    public void printCompany(CompanyLocation company)
    {
        System.out.println(company.toString());
    }




    //database
    private static final CompanyLocationsRepository COMPANY_LOCATIONS_REPOSITORY = CompanyLocationsRepository.getInstance();

    public void addLocation(Integer id, Integer idEmp, Integer idComp, String adresa){
        COMPANY_LOCATIONS_REPOSITORY.saveLocation(id, idEmp, idComp, adresa);
    }


    public List<CompanyLocation> selectAllCompanyLocations() {
        return CompanyLocationsRepository.findAll();
    }



}
