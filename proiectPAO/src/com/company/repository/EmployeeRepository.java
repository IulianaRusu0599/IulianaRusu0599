package com.company.repository;

import com.company.connection.DatabaseConnection;
import com.company.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private static EmployeeRepository instance;

    private EmployeeRepository() {
    }

    public static EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
        }

        return instance;
    }


    public void findEmployee(Integer idEmployee) {
        try (PreparedStatement st = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            st.setInt(1, idEmployee);
            Employee e = null;

            try (ResultSet result = st.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find employee: Employee was not found!");
//                    return null;
                }

                System.out.println("Employee was found!" + result.getString("type"));

                switch(result.getString("type")){
                    case "Developer":
                        e = new Developer(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), null);
                        break;
                    case "Manager":
                        e = new Manager(result.getInt("id"), result.getString("firstName"), result.getString("lastName"));
                        break;
                    case "DataBaseAdmin":
                        e = new DataBaseAdmin(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), null);
                        break;
                    case "NetworkAdmin":
                        e = new NetworkAdmin(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), null);
                        break;
                    case "Tester":
                        e = new Tester(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), null);
                        break;
                    case "WebDesigner":
                        e = new WebDesigner(result.getInt("id"), result.getString("firstName"), result.getString("lastName"), null);
                        break;
                    default:
                }
                System.out.println("idEmployee = " + result.getInt("id") + "; lastName = " + result.getString("lastName") + "; firstName = " + result.getString("firstName") + "; type = " + result.getString("type") + ";");
//                return e;
            }
        } catch (SQLException exp) {
            System.out.println("Something went wrong when trying to select a new employee: " + exp.getMessage());
//            return null ;
        }
    }

    public Employee updateEmployee(Employee employee) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("UPDATE employees SET lastName = ? WHERE id = ?")) {
            statement.setString(1, employee.getLastName());
            statement.setInt(2, employee.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee was updated successfully!");
                return employee;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update employee: " + e.getMessage());
            return null;
        }

        System.out.println("Something went wrong when trying to update user: Employee was not found!");
        return null;
    }


    public List<Employee> findAll() {

        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM employees")) {

            try (ResultSet result = statement.executeQuery()) {
                Employee e = null;
                while (result.next()) {
                    switch (result.getString("type")) {
                        case "Developer":
                            Integer id;
                            e = new Developer(result.getInt("id"), result.getString("lastName"), result.getString("firstName"),null);
                            employees.add(e);
                            break;
                        case "Manager":
                            e = new Manager(result.getInt("id"), result.getString("lastName"), result.getString("firstName"));
                            employees.add(e);
                            break;
                        case "DataBaseAdmin":
                            e = new DataBaseAdmin(result.getInt("id"), result.getString("lastName"), result.getString("firstName"),null);
                            employees.add(e);
                            break;
                        case "NetworkAdmin":
                            e = new NetworkAdmin(result.getInt("id"), result.getString("lastName"), result.getString("firstName"),null);
                            employees.add(e);
                            break;
                        case "Tester":
                            e = new Tester(result.getInt("id"), result.getString("lastName"), result.getString("firstName"),null);
                            employees.add(e);
                            break;
                        case "WebDesigner":
                            e = new WebDesigner(result.getInt("id"), result.getString("lastName"), result.getString("firstName"),null);
                            employees.add(e);
                            break;
                        default:
                    }
                }
            }
        } catch (SQLException exp) {
            System.out.println("Something went wrong when trying to find user: " + exp.getMessage());
        }
        System.out.println("number emp: "+ employees.size());
        return employees;
    }


    public Employee insertOrUpdate(Employee employee) {
        //update or save
        boolean update = false;
        try (PreparedStatement st = DatabaseConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            st.setInt(1, employee.getId());
            try (ResultSet result = st.executeQuery()) {
                if (result.next()) {
                    //update
                    update = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new employee: " + e.getMessage());
            return null;
        }
        if (update) {
            System.out.println("Updating employee " + employee.getId());
            updateEmployee(employee);
        } else {
            System.out.println("Inserting new employee " + employee.getId());
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("INSERT INTO employees(id, lastName, firstName, type ) VALUES (?,?,?,?)")) {
                statement.setInt(1, employee.getId());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getJobName());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new employee was inserted successfully!");
                }
            } catch (SQLException e) {
                System.out.println("Something went wrong when trying to insert a new employee: " + e.getMessage());
                return null;
            }
        }
        return employee;
    }


    public boolean deleteEmployee(Integer idEmployee) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("DELETE FROM employees WHERE id=?")) {
            statement.setString(1, String.valueOf(idEmployee));

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete employee: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete employee: Employee was not found!");
        return false;
    }


}
