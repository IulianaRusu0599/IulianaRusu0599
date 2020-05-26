package com.company.repository;

import com.company.connection.DatabaseConnection;
import com.company.model.CompanyLocation;
import com.company.model.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CompanyLocationsRepository {
    private static CompanyLocationsRepository instance;

    private CompanyLocationsRepository() {
    }

    public static CompanyLocationsRepository getInstance() {
        if (instance == null) {
            instance = new CompanyLocationsRepository();
        }

        return instance;
    }


    public  void findCompanyLocation(Integer idComp ) {
        CompanyLocation companyLocation = new CompanyLocation();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM companyLocations WHERE idComp = ?")) {
            statement.setInt(1, idComp);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find company location: Location was not found!");
                }

                System.out.println("Location was found!");
                companyLocation.setIdEmployee(result.getInt("idEmp"));
                companyLocation.setIdCompany(result.getInt("idComp"));
                companyLocation.setCompanyAddress(result.getString("adresa"));
                System.out.println("idComp = " + result.getInt("idComp") +"; "+ "idEmp = " + result.getInt("idEmp")  +"; "+  "adress = " + result.getString("adresa") +"; ");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find location: " + e.getMessage());
        }

    }
    public static List<CompanyLocation> findAll() {

        List<CompanyLocation> locations = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM companyLocations")) {

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    CompanyLocation companyLocation = new CompanyLocation();
                    companyLocation.setIdCompany(result.getInt("id"));
                    companyLocation.setIdEmployee(result.getInt("idEmp"));
                    companyLocation.setIdCompany(result.getInt("idComp"));
                    companyLocation.setCompanyAddress(result.getString("adresa"));
                    locations.add(companyLocation);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to location: " + e.getMessage());
        }
        return locations;

    }




    public void saveLocation(Integer id, Integer idEmp, Integer idComp, String adresa) {
        System.out.println("Inserting new location ");
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO companyLocations(id,idEmp, idComp, adresa ) VALUES (?,?,?,?)")) {
            statement.setInt(1, id);
            statement.setInt(2, idEmp);
            statement.setInt(3, idComp);
            statement.setString(4, adresa);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new company location was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new company location: " + e.getMessage());

        }
    }


    public boolean deleteCompanyLocation(Integer idEmp) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("DELETE FROM companyLocations WHERE idEmp=?")) {
            statement.setString(1, String.valueOf(idEmp));

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Location was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete location: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete location: company location was not found!");
        return false;
    }
}
