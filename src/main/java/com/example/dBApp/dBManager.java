package com.example.dBApp;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class dBManager {

    public Connection connection ;

    public  dBManager() throws SQLException {
        getConnection();
        createTable();
    }

    public Connection getConnection() throws SQLException {
        if(connection == null){

            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/studentdb", "root", "root");
        }
        return connection;
    }

    public void createTable() throws SQLException {
        String sql = "create table if not exists student_info(id INT primary key auto_increment,age INT,name VARCHAR(30))";
        Statement st = connection.createStatement();
        st.execute(sql);
    }
    public void insert_info(Student s) throws SQLException {
        String sql = "insert into student_info(age,name)  values("+s.getAge()+",'"+s.getName()+"')";
        Statement st = connection.createStatement();
        int rows = st.executeUpdate(sql);
        System.out.println("Number of rows affected = " +rows);
    }
    public void getAllStudents() throws SQLException {
        String sql = "select * from student_info";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            String name = rs.getString("name");
            String id = rs.getString("id");
            String age = rs.getString("age");

            System.out.println(name+" "+id+" "+age);

        }
    }
}
