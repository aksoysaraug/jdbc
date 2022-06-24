package jdbc_turkce;

import java.sql.*;

public class JdbcQuery01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // 1) Ilgili driveri yuklemeliyiz.
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2) Baglanti olusturmaliyiz
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_tech","root","Nazenin11");

        //3) SQL komutlari icin bir Statement nesnesi olustur
        Statement statement=connection.createStatement();

        //4) SQL ifadeleri yazdirabilir ve calistirabiliriz
        ResultSet resultSet=statement.executeQuery("Select name, email FROM students");

        //5) Sonuclari aldik ve isledik
        while(resultSet.next()){
            System.out.println("Name: "+resultSet.getString("name")+" Email: "+resultSet.getString("email"));
            System.out.println("Name with column index: "+resultSet.getString(1)+" Email with column index: "+resultSet.getString(2));
        }                           // 1 ==> "Select name, email FROM students" icerisindeki ilk sutunu(name) temsil eder.
    }
}
