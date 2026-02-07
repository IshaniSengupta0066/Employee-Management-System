package employee;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeDept;
    private double salary;

    public Employee(int employeeId, String employeeName, String employeeDept, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDept = employeeDept;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeDept() {
        return employeeDept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void displayEmployee() {
        System.out.println("Employee ID   : " + employeeId);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Department    : " + employeeDept);
        System.out.println("Salary        : " + salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeDept='" + employeeDept + '\'' +
                ", salary=" + salary +
                '}';
    }
}
