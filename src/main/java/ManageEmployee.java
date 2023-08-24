import java.util.InputMismatchException;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ManageEmployee {
    private static SessionFactory factory;
    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageEmployee ME = new ManageEmployee();

        ME.selectMenu();


        /* List down all the employees */
//        ME.listEmployees();

        /* Update employee's records */
//        ME.updateEmployee(empID1, 5000);

        /* Delete an employee from the database */
//        ME.deleteEmployee(empID2);

        /* List down new list of the employees */
//        ME.listEmployees();
    }

    /* Method to CREATE an employee in the database */

    public Integer addEmployee(String fname, String lname, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public void listEmployees( ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setSalary( salary );
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void selectMenu() throws InputMismatchException {
        int selectedMenu;
        Scanner aScanner = new Scanner(System.in);

        do {
            System.out.println("===========ManageEmployee=============");
            System.out.println("1. List all");
            System.out.println("2. Add employee ");
            System.out.println("3. Update employee ");
            System.out.println("4. Delete employee ");
            System.out.println("0. Exit");
            System.out.println("Your choose: ");

            selectedMenu = aScanner.nextInt();

            switch (selectedMenu) {
                case 1 :
                    this.listEmployees();
                    break;
                case 2 :
                    System.out.println("Input first name: ");
                    String fname = aScanner.next();
                    System.out.println("Input last name: ");
                    String lname = aScanner.next();
                    System.out.println("Input salary: ");
                    int salary = aScanner.nextInt();
                    this.addEmployee(fname, lname, salary);
                case 0 :
                    System.out.println("Exit ...");
                    break;
                default:
                    System.out.println("Incorrect number");
                    break;
            }

        } while(selectedMenu != 0);
    }

}