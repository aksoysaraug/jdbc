package from_short_video;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {

        try(Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_tech?SSL=false","root","Nazenin11");){

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
