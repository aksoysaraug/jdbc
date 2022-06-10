package _01_com.company;

import java.sql.*;

public class Main {

    // First we need to write mysql query and store it into any variable

    private static final String QUERY= "select * from students";

    public static void main(String[] args) throws SQLException {

        //Step 1. Establish the connection of Mysql database
        try(
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_tech?useSSL=false","root","Nazenin11");

            // step 2. create a statement using that above connection object
            Statement stmt= connection.createStatement();

            //step 3. execute the query, there are 2 methods for executing the query. 1)execute() 2)executeQuery()
            ResultSet rs= stmt.executeQuery(QUERY)){

            //step 4: process the result and execute it
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getNString("name");
                String email=rs.getNString("email");
                String country=rs.getNString("country");

                //finally printing my result
                System.out.println(id+" , "+name+" , "+email+" , "+ country);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
