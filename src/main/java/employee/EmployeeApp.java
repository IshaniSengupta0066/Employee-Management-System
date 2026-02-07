package employee;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

class EmployeeNotFound extends Exception {
    EmployeeNotFound(String message) {
        super(message);
    }
}

public class EmployeeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {
            try {
                System.out.println("\nEmployee Management Menu");
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee by ID");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. View All Employees");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Enter Employee Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Employee Department: ");
                        String dept = sc.nextLine();

                        System.out.print("Enter Employee Salary: ");
                        double salary = sc.nextDouble();

                        Employee emp = new Employee(0, name, dept, salary);
                        dao.addEmployee(emp);

                        System.out.println("Employee added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Employee ID to search: ");
                        int searchId = sc.nextInt();

                        Employee found = dao.getEmployeeById(searchId);

                        if (found != null) {
                            found.displayEmployee();
                        } else {
                            throw new EmployeeNotFound("Employee not found");
                        }
                        break;

                    case 3:
                        System.out.println("Update Options:");
                        System.out.println("1. Update Name");
                        System.out.println("2. Update Salary");
                        System.out.println("3. Update Name & Salary");
                        System.out.print("Enter your choice: ");

                        int updateChoice = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Employee ID: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        boolean updated = false;

                        if (updateChoice == 1) {
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine();
                            updated = dao.updateEmployeeName(updateId, newName);

                        } else if (updateChoice == 2) {
                            System.out.print("Enter new salary: ");
                            double newSalary = sc.nextDouble();
                            updated = dao.updateEmployeeSalary(updateId, newSalary);

                        } else if (updateChoice == 3) {
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter new salary: ");
                            double newSalary = sc.nextDouble();
                            updated = dao.updateEmployeeNameAndSalary(updateId, newName, newSalary);

                        } else {
                            System.out.println("Invalid update choice.");
                            break;
                        }

                        if (updated) {
                            System.out.println("Employee updated successfully!");
                        } else {
                            throw new EmployeeNotFound("Employee not found");
                        }
                        break;


                    case 4:
                        System.out.print("Enter Employee ID to delete: ");
                        int deleteId = sc.nextInt();

                        if (dao.deleteEmployeeById(deleteId)) {
                            System.out.println("Employee deleted successfully!");
                        } else {
                            throw new EmployeeNotFound("Employee not found");
                        }
                        break;

                    case 5:
                        dao.getAllEmployees();
                        break;

                    case 6:
                        System.out.println("Exiting program");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }

            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.nextLine();
            }
            catch (EmployeeNotFound e) {
                System.out.println(e.getMessage());
            }
            catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Error is - " + e.getMessage());
            }
        }
    }
}
