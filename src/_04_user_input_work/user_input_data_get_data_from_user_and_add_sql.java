package _04_user_input_work;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class user_input_data_get_data_from_user_and_add_sql {
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

            // BUFFER LIBRARY TO TAKE INPUT LINE BY LINE
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // TO GET THE INPUT FROM USER LINE BY LINE
                                                                                      // and store it in database
//            System.out.println("Enter your ID: ");The table is created with auto-increment Id,
//                                                   therefor you didn't need to ask the ID
//            String id = br.readLine();

            System.out.println("Enter your Name: ");
            String name = br.readLine();

            System.out.println("Enter your Email: ");
            String email = br.readLine();

            System.out.println("Enter your Country: ");
            String country = br.readLine();

            //pstment.setInt(1,5); // here 5 is the ID column and 1 means the first column
            pstment.setNString(1,name);
            pstment.setNString(2,email);
            pstment.setNString(3,country);

            pstment.executeUpdate();

            System.out.println("Successfully inserted into DB");
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

