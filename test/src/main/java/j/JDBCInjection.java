package j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInjection {
    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //ע����һ�����п���
        //2.��ȡ���ӵĶ���
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.ִ��sql���Ķ���
        Statement statement = connection.createStatement();
        System.out.println("������Ҫ��ѯ������");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //4.��дsql���
        String sql = "select * from t_emp where emp_name = '" + input + "'";
        //�ᵼ��ע�빥�������ֲ�ⵥ���ŵķ�ʽ���ܻ�ʹ������ԶΪ��
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);

        }
        //6.�ͷ���Դ
        resultSet.close();
        statement.close();
        connection.close();
    }
}
