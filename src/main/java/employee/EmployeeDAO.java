package employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {


    public void addEmployee(Employee emp) throws SQLException {

        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getEmployeeName());
            ps.setString(2, emp.getEmployeeDept());
            ps.setDouble(3, emp.getSalary());

            ps.executeUpdate();
        }
    }

    public Employee getEmployeeById(int id) throws SQLException {

        String sql = "SELECT * FROM employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
            }
        }
        return null;
    }

    public void getAllEmployees() throws SQLException {

        String sql = "SELECT * FROM employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n-- Employee List --");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("name") +
                                ", Dept: " + rs.getString("department") +
                                ", Salary: " + rs.getDouble("salary")
                );
            }
        }
    }


    public boolean updateEmployeeName(int id, String newName) throws SQLException {

        String sql = "UPDATE employee SET name = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        }
    }


    public boolean updateEmployeeSalary(int id, double newSalary) throws SQLException {

        String sql = "UPDATE employee SET salary = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newSalary);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        }
    }
    public boolean updateEmployeeNameAndSalary(int id, String newName, double newSalary)
            throws SQLException {

        String sql = "UPDATE employee SET name = ?, salary = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setDouble(2, newSalary);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteEmployeeById(int id) throws SQLException {

        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
