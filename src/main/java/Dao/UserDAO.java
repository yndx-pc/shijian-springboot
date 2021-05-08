package Dao;

import myBean.JDBC;
import myBean.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private static String username;

    private static String pass;

    public static User getData(String userName) {
        Connection conn = JDBC.getConnect();
        try {
            Statement statement = null;
            try {
                statement = conn.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String sql = "select * from student where userName = '"+userName+"'";
//            String sql = "select * from student";
            ResultSet rs = null;
            try {
                rs = statement.executeQuery(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                rs.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            username = rs.getString("userName");
            pass = rs.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = new User(username, pass);
        System.out.println(pass);
        return user;
    }
}
