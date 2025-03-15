package j;

import java.sql.*;
import java.util.Scanner;

public class JDBCPrepare {
    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //ע����һ�����п���
        //2.��ȡ���ӵĶ���
        String url = "jdbc:mysql://localhost:3306/atguigu";
        String user = "root";
        String password = "hkx171901";
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("������Ҫ��ѯ������");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //3.ִ��sql���Ķ���ʹ��Ԥ����sql��䣬ʹ�ã�ռλ������ʾ��Ҫ����Ķ���

        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_emp where emp_name = ?");
        //String sql = "select * from t_emp where emp_name = '" + input + "'";
        //�ᵼ��ע�빥�������ֲ�ⵥ���ŵķ�ʽ���ܻ�ʹ������ԶΪ��

        //Ϊ��ռλ����ֵ
        preparedStatement.setString(1,input);
        //1�����һ������input��ʾҪ�滻��������
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);

        }
        //6.�ͷ���Դ
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
