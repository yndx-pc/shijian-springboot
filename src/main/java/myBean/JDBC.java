package myBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static String sqlUser = "sa";
    private static String sqlPassword = "123456";
    private static String mySqlUser = "root";
    private static String mySqlPassword = "Wuhongtu";
//    private static String ipAddress = "39.101.196.222";
    private static String ipAddress = "127.0.0.1";

    public static Connection getConnect(){
        Connection conn = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;DatabaseName=studentim";
            conn = DriverManager.getConnection(url,sqlUser,sqlPassword);
        }catch (SQLException | ClassNotFoundException e){
        }
        return conn;
    }
    public static Connection getCon(){
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+ipAddress+":3306/food_frugal?useSSL=false&serverTimezone=UTC";
            conn = DriverManager.getConnection(url,mySqlUser,mySqlPassword);
        }catch (SQLException | ClassNotFoundException e){
        }
        return conn;
    }
}
