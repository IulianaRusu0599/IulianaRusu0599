package com.company.repository;

import com.company.connection.DatabaseConnection;
import com.company.model.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SalaryRepository {

    private static SalaryRepository instance;

    private static final String INSERT_STATEMENT = "INSERT INTO salaries (idSalary, idEmployee, salary) VALUES (?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM salaries WHERE idEmployee = ?";
    private static final String SELECT_ALL = "SELECT * FROM salaries";
    private static final String UPDATE_STATEMENT = "UPDATE salaries SET salary = ? WHERE idEmployee = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM salaries WHERE idEmployee=?";

    private SalaryRepository() {
    }

    public static SalaryRepository getInstance() {
        if (instance == null) {
            instance = new SalaryRepository();
        }

        return instance;
    }



    public Salary findSalary(Integer idEmployee) {
        Salary salary = new Salary();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, idEmployee);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find employee: User was not found!");
                    return salary;
                }

                System.out.println("Salary was found!");
                salary.setIdSalary(result.getInt("idSalary"));
                salary.setIdEmployee(result.getInt("idEmployee"));
                salary.setSalary(result.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return salary;
    }


    public Salary updateSalary(Salary salary) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                .prepareStatement(UPDATE_STATEMENT)) {
            statement.setDouble(1, salary.getSalary());
            statement.setInt(2, salary.getIdEmployee());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User was updated successfully!");
                return salary;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
            return new Salary();
        }

        System.out.println("Something went wrong when trying to update user: User was not found!");
        return new Salary();
    }

    public Salary saveSalary(Salary salary) {
        //update or save
        boolean update = false;
        try (PreparedStatement st = DatabaseConnection.getInstance().getConnection()
                .prepareStatement(SELECT_STATEMENT)) {
            st.setInt(1, salary.getIdEmployee());
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    //update
                    update = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new salary: " + e.getMessage());
            return new Salary();
        }
        if (update) {
            System.out.println("Updating salary of employee " + salary.getIdEmployee());
            updateSalary(salary);
        } else {
            System.out.println("Inserting new salary of employee " + salary.getIdEmployee());
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement(INSERT_STATEMENT)) {
                statement.setString(1, String.valueOf(salary.getIdSalary()));
                statement.setString(2, String.valueOf(salary.getIdEmployee()));
                statement.setString(3, String.valueOf(salary.getSalary()));

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new salary was inserted successfully!");
                }
            } catch (SQLException e) {
                System.out.println("Something went wrong when trying to insert a new salary: " + e.getMessage());
                return new Salary();
            }
        }
        return salary;
    }

    public boolean deleteSalary(Integer idEmployee) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setString(1, String.valueOf(idEmployee));

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete user: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete user: User was not found!");
        return false;
    }

    public List<Salary> findAll() {

        List<Salary> salaries = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_ALL)) {

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    Salary salary = new Salary();
                    salary.setSalary(result.getDouble("salary"));
                    salary.setIdSalary(result.getInt("idSalary"));
                    salary.setIdEmployee(result.getInt("idEmployee"));
                    salaries.add(salary);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return salaries;

    }
}
