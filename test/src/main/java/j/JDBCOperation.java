package j;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCOperation {
    @Test
    public  void testQuerySingleRowAndCol() throws Exception {
      //注册驱动

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "hkx171901");
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from t_emp");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            System.out.println(anInt);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
    @Test
    public  void testQuerySingleRow() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "hkx171901");
        PreparedStatement preparedStatement = connection.prepareStatement("select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_id = 2");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+","+empName+","+empSalary+","+empAge);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
    @Test
    public  void testQuery() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "hkx171901");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_emp where emp_id > 2");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+":"+empName+","+empSalary+","+empAge);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();


    }
    @Test
    public  void testInsert() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "hkx171901");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into t_emp(emp_name,emp_salary,emp_age) values (?,?,?) ");
        preparedStatement.setString(1,"dsb");
        preparedStatement.setDouble(2,500.22);
        preparedStatement.setInt(3,27);
        int i = preparedStatement.executeUpdate();
        //进行增删改操作时没有返回结果集，要根据受影响的行数来判断
        if(i > 0){
            System.out.println("success");
        }
        else {
            System.out.println("fail");
        }
        preparedStatement.close();
        connection.close();

    }
    @Test
    public  void testDelete() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "hkx171901");
        PreparedStatement preparedStatement = connection.prepareStatement("delete from t_emp where emp_id > 6");
        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("success");
        }
        else {
            System.out.println("fail");
        }
        preparedStatement.close();
        connection.close();

    }
    @Test
    public  void testUpdate() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "hkx171901");
        PreparedStatement preparedStatement = connection.prepareStatement("update t_emp set emp_salary = ? where emp_salary < 10000");
        preparedStatement.setDouble(1,20000.50);
        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("success");
        }
        else {
            System.out.println("fail");
        }
        preparedStatement.close();
        connection.close();

    }

}
