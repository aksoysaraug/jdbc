package jdbc_again;

import java.sql.*;
import java.util.Scanner;

public class MainAgain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            int choice = 0;

            //Calling the student class and making the object.
            Student std = new Student();


            do{
                System.out.println("Press 1 for new student record" +
                        "\n Press 2 for getting the student data" +
                        "\n Press 3 for updating the student data" +
                        "\n Press 4 for deleting the student data" +
                        "\n Press 5 for exit the menu");
                Scanner choose= new Scanner(System.in);
                choice=choose.nextInt();
                switch(choice){
                    case 1:
                        std.getStudentDetail();
                        std.insertStudentData();
                        break;
                    case 2:
                        //put show function
                        std.searchStudent();
                        break;
                    case 3:
                        // put update function
                        std.updateStudent();
                        break;
                    case 4:
                        // put delete function
                        std.deleteStudent();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Please enter correct choice");

                }

            }while(choice!=5);{
                System.out.println("Thanks for using the Application!");
            }

        } catch(Exception e){

        }
    }
}

//Making the student class that contains every functionality
class Student{
    private String name;
    private String email;
    private String country;
    private int phone;

    //Function that takes input from user
    public void getStudentDetail(){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter your name");
        name=input.nextLine();
        System.out.println("Enter your email");
        email=input.nextLine();
        System.out.println("Enter your country");
        country=input.nextLine();
        System.out.println("Enter your phone number");
        phone=input.nextInt();
    }

    //Making a function that connects the user input and pass it to the database table columns
    public void insertStudentData() throws SQLException, ClassNotFoundException {
        //First we need to call the database connection
        DbmsConnection dbmsConnection=new DbmsConnection("jdbc:mysql://localhost:3306/jdbc_tech?SSL=false","root","Nazenin11");
        //I am calling the function/method that is present in my DbmsConnection class
        Connection con=dbmsConnection.getConnection();

        //Making the MySQL query
        String sql="Insert into students(name,email,country,phone) values(?,?,?,?);";
        //Preparing my above statement
        // PrepareStatement are used for taking input from user and sending that input into the database for doing CRUD operations.
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,email);
        stmt.setString(3,country);
        stmt.setInt(4,phone);

        //Finally execute my preparedStatements
        int i = stmt.executeUpdate();
        System.out.println("The record has been saved successfully");
    }

    //Function for showing the specific student record
    public void searchStudent() throws SQLException, ClassNotFoundException {
        DbmsConnection dbmsConnection=new DbmsConnection("jdbc:mysql://localhost:3306/jdbc_tech?SSL=false","root","Nazenin11");
        Connection connection=dbmsConnection.getConnection();

        Scanner input= new Scanner(System.in);
        System.out.println("Enter student email to get all record");
        String emailInput=input.nextLine();

        //Making the SQL query for fetching the student data
        String sql="Select * from students where email=?";

        //Preparing my above statement
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,emailInput);

        //Getting the result set
        ResultSet resultSet=stmt.executeQuery();

        //Showing the error if the email is not present into the database
        if(resultSet.next()==false){
            System.out.println("No email found into the database record");
        }else{
            System.out.println(resultSet.getInt(1)+" "+
                    resultSet.getString(2)+" "+
                    resultSet.getString(3)+" "+
                    resultSet.getString(4));
        }
    }

    //Function for deleting the student record
    public void deleteStudent() throws SQLException, ClassNotFoundException {
        DbmsConnection dbmsConnection=new DbmsConnection("jdbc:mysql://localhost:3306/jdbc_tech?SSL=false","root","Nazenin11");
        Connection connection=dbmsConnection.getConnection();

        Scanner input= new Scanner(System.in);
        System.out.println("Enter student email to delete her/his record");
        String emailInput=input.nextLine();

        String sql="Delete from students where email=? ";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,emailInput);
        int i=stmt.executeUpdate();

        //If student record deleted successfully
        if(i>0){
            System.out.println("Student record has been deleted successfully");
        } else{
            System.out.println("No email found into the database record");
        }
    }

    public void updateStudent() throws SQLException, ClassNotFoundException {
        DbmsConnection dbmsConnection=new DbmsConnection("jdbc:mysql://localhost:3306/jdbc_tech?SSL=false","root","Nazenin11");
        Connection connection=dbmsConnection.getConnection();

        Scanner input= new Scanner(System.in);
        System.out.println("Enter student email to update his/her name");
        String emailInput=input.nextLine();
        System.out.println("Enter students new name");
        String newName=input.nextLine();

        String sql="Update students set name=? where email=?";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,newName);
        stmt.setString(2,emailInput);

        int i= stmt.executeUpdate();
        if(i>0){
            System.out.println("Student name changed successfully");
        } else{
            System.out.println("No email found into the database");
        }

    }


}

// We are making JDBC class in which we have just MySQL connection
// In every function we need the connection again and again
class DbmsConnection{
    String url;
    String username;
    String password;

    //I am going to make a setter for MySQL connection
    public DbmsConnection(String url,String username,String password){
        this.url=url;
        this.username=username;
        this.password=password;
    }


    //Make a function that contain core code of JDBC connection
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection con= null;

        //Calling the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,username,password);

        //Print this if our connection got successful
        System.out.println("Your connection has been established successfully!");
        return con;
    }


}

