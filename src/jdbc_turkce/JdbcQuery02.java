package jdbc_turkce;

import java.sql.*;

public class JdbcQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1) Ilgili driveri yuklemeliyiz.
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2) Baglanti olusturmaliyiz
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_tech","root","Nazenin11");

        //3) SQL komutlari icin bir Statement nesnesi olustur
        Statement statement=connection.createStatement();

        //Students tablosundaki tum kayitlari listeleyen bir sorgu yaziniz


        ResultSet resultSet=statement.executeQuery("Select * from students");

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +
                    resultSet.getString(4));
        }
    }
}
