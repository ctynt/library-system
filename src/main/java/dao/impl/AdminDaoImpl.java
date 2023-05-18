package dao.impl;

import dao.AdminDao;
<<<<<<< HEAD
import domain.Admin;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
=======
>>>>>>> d1facc06bd46a5154b97cfb5e9e5124dbf67cb9d

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class AdminDaoImpl implements AdminDao {
<<<<<<< HEAD
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil db = new JDBCUtil();

    @Override
    public boolean checkLogin(Admin user) {
        boolean message = false;
        try {
            // 取得数据库连接
            conn = db.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "select * from admin where adminId = ? and adminPassword = ? ";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的2个参数
            ps.setInt(1, user.getAdminId());
            ps.setString(2, user.getAdminPassword());
            // 执行查询操作
            resultSet = ps.executeQuery();
            if (resultSet.next())
                message = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return message;
    }

}
=======
}
>>>>>>> d1facc06bd46a5154b97cfb5e9e5124dbf67cb9d
