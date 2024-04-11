package org.example;

import lombok.var;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    static String url = "jdbc:mysql://localhost:3306/jdbcDemo";
    public static void statementTableCreation() {
        String tableSql = "CREATE TABLE IF NOT EXISTS employees"
                + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
                + "position varchar(30), salary double)";
        try(Connection connection = DriverManager.getConnection(url, "root", "password");
            Statement statement = connection.createStatement();
        ) {
            statement.execute(tableSql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public static void insertUsingStatement() {
        String insertSql1 = "INSERT INTO employees(name, position, salary)"
                + " VALUES('Ansh', 'developer', 200000)";
        String insertSql2 = "INSERT INTO employees(name, position, salary)"
                + " VALUES('Shivali', 'tester', 300000)";
        String insertSql3 = "INSERT INTO employees(name, position, salary)"
                + " VALUES('Pratik', 'manager', 500000)";
        try(Connection connection = DriverManager.getConnection(url, "root", "password");
            Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(insertSql1);
            statement.executeUpdate(insertSql2);
            statement.executeUpdate(insertSql3);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void selectUsingStatement() {
        String selectSql = "SELECT * FROM employees";
        try(Connection connection = DriverManager.getConnection(url, "root", "password");
            Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                List<Employee> employees = new ArrayList<>();
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("emp_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setPosition(resultSet.getString("position"));
                    employee.setSalary(resultSet.getDouble("salary"));
                    employees.add(employee);
                }
                for(var employee:employees) {
                    System.out.println(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateUsingPreparedStatement() {
        String updatePositionSql = "UPDATE employees SET position=? WHERE emp_id=?";
        try (Connection connection = DriverManager.getConnection(url, "root", "password");
             PreparedStatement preparedStatement = connection.prepareStatement(updatePositionSql);) {
            preparedStatement.setString(1,"HR");
            preparedStatement.setInt(2,25);

            preparedStatement.execute();

            preparedStatement.setString(1,"CTO");
            preparedStatement.setInt(2,24);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main( String[] args )
    {
        statementTableCreation();
        insertUsingStatement();
        selectUsingStatement();
        updateUsingPreparedStatement();
        selectUsingStatement();
    }
}
