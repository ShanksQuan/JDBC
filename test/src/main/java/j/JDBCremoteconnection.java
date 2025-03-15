package j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCremoteconnection {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接的对象
        String url = "jdbc:mysql://192.168.1.107:3306/scraping";
        String user = "root";
        String password = "junjun123";
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.执行sql语句的对象
        Statement statement = connection.createStatement();
         //4.编写sql语句
        String sql = "select * from pages";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            String empName = resultSet.getString("title");
            System.out.println(empName);

        }
        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}

