package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class JDBCUtil {
    public Connection setupConn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url,"root","password");
            System.out.println("数据库连接成功");
            return conn;
        } catch(Exception e){
            System.out.println("数据库连接失败！");
            e.printStackTrace();
            return null;

        }
    }
}
