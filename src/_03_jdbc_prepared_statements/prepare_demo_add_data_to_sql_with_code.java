package _03_jdbc_prepared_statements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class prepare_demo_add_data_to_sql_with_code {

    public static void main(String[] args) {
        try{
            //loading the driver
            Class.forName("com.mysql.jdbc.Driver");

            //create a database connection
            String url = "jdbc:mysql://localhost:3306/jdbc_tech";
            String username = "root";
            String password = "Nazenin11";

            //call a connection driver
            Connection connection = DriverManager.getConnection(url,username,password);

            String query = "insert into students(name,email,country) values(?,?,?)";

            //now we need to make a prepared statements
            PreparedStatement pstment = connection.prepareStatement(query);

            //Now enter the students data. You can enter the data as many as you want
//            pstment.setInt(1,3); // here 6 is the ID column and 1 means the first column
            pstment.setNString(1,"Omer");
            pstment.setNString(2,"omer@gmail.com");
            pstment.setNString(3,"Turkey");

            pstment.executeUpdate();

            System.out.println("Successfully inserted into DB");
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
