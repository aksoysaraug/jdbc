package _02_jdbc_user_input;

import java.sql.Connection;
import java.sql.DriverManager;

public class student_connect_sql_02 {
    public static void main(String[] args) {
        try{
            //loading the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a database connection
            String url="jdbc:mysql://localhost:3306/jdbc_tech";
            String username="root";
            String password="Nazenin11";

            //call a connection driver
            Connection connection= DriverManager.getConnection(url,username,password);

            //if we fail in building connection
            //check the connection is successfully build or not
            if(connection.isClosed()){
                System.out.println("Connection is failed or closed!");
            }else{
                System.out.println("Connection has been established!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
