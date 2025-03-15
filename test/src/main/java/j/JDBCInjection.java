package j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInjection {
    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //注册这一步可有可无
        //2.获取连接的对象
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.执行sql语句的对象
        Statement statement = connection.createStatement();
        System.out.println("请输入要查询的名字");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //4.编写sql语句
        String sql = "select * from t_emp where emp_name = '" + input + "'";
        //会导致注入攻击，这种拆解单引号的方式可能回使输入永远为真
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);

        }
        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
