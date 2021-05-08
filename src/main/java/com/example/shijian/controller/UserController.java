package com.example.shijian.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import myBean.Food;
import myBean.JDBC;
import myBean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController extends HttpServlet {
    @ResponseBody
    @RequestMapping(value = "userLogin",method = RequestMethod.POST)
    @CrossOrigin
    public String login( @RequestParam(name = "userName") String username,@RequestParam(name = "password") String password) throws SQLException {
//        Connection conn = JDBC.getCon();
//        Statement statement = conn.createStatement();
//        String sql = "select * from food_frugal_worker where W_name = '"+username+"'";
//        ResultSet rs = statement.executeQuery(sql);
//        rs.next();
//        String pass = rs.getString("W_password");
//        if(conn != null) {
//            try {
//                conn.close();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//            conn = null;
//        }
//        if(pass.equals(password) && !username.equals(""))
//            return "200";
        return "100";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "userRegister",method = RequestMethod.POST)
    public String insert1( @RequestParam(name = "username") String username,@RequestParam(name = "password") String password) throws SQLException {
//        Connection conn = JDBC.getCon();
//        Statement statement = conn.createStatement();
//        String sql1 = "select * from food_frugal_worker where W_name = '"+username+"'";
//        ResultSet rs1 = statement.executeQuery(sql1);
//        if (rs1.next())
//            return "100";
//        String sql2 = "select * from food_frugal_worker where id >= all (select id from food_frugal_worker)";
//        ResultSet rs2 = statement.executeQuery(sql2);
//        int id = rs2.next()?rs2.getInt("id") + 1:1;
//        String sql = "insert into food_frugal_worker(id,W_name,W_password,Did_id,Dname_id) values (" +
//                "'"+id+"','"+username+"','"+password+"','"+1+"','"+2+"')";
//        try {
//            boolean rs = statement.execute(sql);
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }
//        if(conn != null) {
//            try {
//                conn.close();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//            conn = null;
//        }
        return "200";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "WebFoodUpload",method = RequestMethod.POST)
    public String insert( @RequestParam(name = "dishname") String names,@RequestParam(name = "price") String prices) throws SQLException {
//        Connection conn = JDBC.getCon();
//        Statement statement = conn.createStatement();
//        String sql1 = "select * from food_frugal_foods where F_name = '"+names+"'";
//        ResultSet rs1 = statement.executeQuery(sql1);
//        if (rs1.next())
//            return "100";
//        String sql2 = "select * from food_frugal_foods where id >= all (select id from food_frugal_foods)";
//        ResultSet rs2 = statement.executeQuery(sql2);
//        int id = rs2.next()?rs2.getInt("id") + 1:1;
//        String sql = "insert into food_frugal_foods(id,F_image,F_name,F_material1,F_material2,F_material3,D_name) values (" +
//                "'"+id+"','"+1+"','"+names+"','"+1+"','"+2+"','"+3+"','"+4+"')";
//        try {
//            boolean rs = statement.execute(sql);
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }
//        if(conn != null) {
//            try {
//                conn.close();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//            conn = null;
//        }
        return "200";
    }

//    上传菜品
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "WechatFoodUpload",method = RequestMethod.GET)
    public String WechatUp(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        Connection conn = JDBC.getCon();
        Statement statement = conn.createStatement();

        Writer out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取微信小程序get的参数值并打印
        String name = request.getParameter("name");

//        System.out.println("姓名   =  "+name);
        String sql1 = "select * from food_frugal_foods where F_name = '"+name+"'";
        ResultSet rs1 = statement.executeQuery(sql1);
        if (rs1.next()) {
            try {
                out.write("100");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                conn = null;
            }
            return "100";
        }
        String sql2 = "select * from food_frugal_foods where id >= all (select id from food_frugal_foods)";
        ResultSet rs2 = statement.executeQuery(sql2);
        int id = rs2.next()?rs2.getInt("id") + 1:1;

        String sql = "insert into food_frugal_foods(id,F_image,F_name,F_material1,F_proportion1,F_material2,F_material3,votes,D_name) values (" +
                "'"+id+"','"+1+"','"+name+"','"+1+"','"+"20g"+"','"+2+"','"+3+"','"+0+"','"+4+"')";
        try {
            boolean rs = statement.execute(sql);
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        if(conn != null) {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }

        //返回值给微信小程序
        try {

            out.write("200");
//            不知道有什么用，会影响return
//            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "200";
    }

//    查看菜品
    @RequestMapping(value = "WechatFoodLook",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public void WechatLook(HttpServletResponse response) throws JsonProcessingException, SQLException {
        Connection conn = JDBC.getCon();
        ObjectMapper mapper=new ObjectMapper();
        List<String> list = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "select F_name name from food_frugal_foods where F_name != 'null'";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            list.add(rs.getString("name"));
        }
//        User user = UserDAO.getData("zhangsan");
        String str = mapper.writeValueAsString(list);
        if(conn != null) {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }

        response.setContentType("application/json");

        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printWriter.write(new ObjectMapper().writeValueAsString(list));
//        return new ObjectMapper().writeValueAsString(list);
    }

    @RequestMapping(value = "sqlReturnTest")
    @ResponseBody
    @CrossOrigin
    public String test2() throws JsonProcessingException, SQLException {
        User user = null;
        Connection conn = JDBC.getConnect();
        ObjectMapper mapper=new ObjectMapper();
        List<User> list = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "select * from student";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            user = new User(rs.getString(1),rs.getString(2));
            list.add(user);
        }
        String str = mapper.writeValueAsString(list);
        if(conn != null) {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }
        return new ObjectMapper().writeValueAsString(list);
    }

    @RequestMapping(value = "insert")
    @ResponseBody
    @CrossOrigin
    public String insert3() throws JsonProcessingException, SQLException {
        Connection conn = JDBC.getConnect();
        String us = "ergou";
        String sql1 = "select * from student where userName = '"+us+"'";
        Statement statement = null;
        ResultSet rs1 = statement.executeQuery(sql1);
        if(rs1.next())
            System.out.println(rs1.getString(1));
        else
            System.out.println(rs1.getString(2));
        String sql = "set nocount on; insert into student(userName,password) values ('ergou',88)";
        try {
            ResultSet rs = statement.executeQuery(sql);
        } catch (SQLException ex){
            ex.printStackTrace();
        }

//        User user = UserDAO.getData("zhangsan");
        return "3";
    }

    @RequestMapping(value = "vote",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public void test3(HttpServletResponse response) throws JsonProcessingException, SQLException {
        Food food = null;
        int i = 1;
        Connection conn = JDBC.getCon();
        ObjectMapper mapper=new ObjectMapper();
        List<Food> list = new ArrayList<>();
        Statement statement = conn.createStatement();
//        String sql = "select * from food order by 'vote' desc";
//        String sql = "select vF_name name,count(vF_name) vote from food_frugal_voate group by name order by vote desc";
        String sql = "select F_name name,votes vote " +
                "from food_frugal_foods " +
                "where votes > 0 " +
                "order by vote desc";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            food = new Food(rs.getString("name"), i, rs.getInt("vote"));
            list.add(food);
            i++;
        }
//        User user = UserDAO.getData("zhangsan");
        String str = mapper.writeValueAsString(list);
        if(conn != null) {
            try {
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }

        response.setContentType("application/json");

        response.setCharacterEncoding("utf-8");

        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printWriter.write(new ObjectMapper().writeValueAsString(list));
//        return new ObjectMapper().writeValueAsString(list);
    }

    @RequestMapping(value = "tell")
    @ResponseBody
    public String test4() throws JsonProcessingException {
        System.out.println("成功");
        ObjectMapper mapper=new ObjectMapper();
        List<User> list = new ArrayList<>();
        User user1 = new User("zhangsan","1");
        User user2 = new User("zhangsan","1");
        User user3 = new User("zhangsan","1");
        list.add(user1);
        list.add(user2);
        list.add(user3);
//        User user = UserDAO.getData("zhangsan");
        String str = mapper.writeValueAsString(list);
        return new ObjectMapper().writeValueAsString(list);
    }


    @RequestMapping(value = "test1")
    @ResponseBody
    @CrossOrigin
    public String test1(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        System.out.println(name);
        return name;
    }

    @RequestMapping(value = "test2")
    @ResponseBody
    @CrossOrigin
    public String test2(HttpServletResponse response){
        System.out.println("test2");
        return "test2";
    }
}
