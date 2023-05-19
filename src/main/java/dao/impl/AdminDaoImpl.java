
package dao.impl;

import dao.AdminDao;
import domain.Admin;
import domain.Book;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */


public class AdminDaoImpl implements AdminDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil db = new JDBCUtil();

    @Override
    public boolean checkLogin(Admin user) {
        boolean message = false;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "select * from admin where adminId = ? and adminPassword = ? ";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的2个参数
            ps.setInt(1, user.getAdminId());
            ps.setString(2, user.getAdminPassword());
            // 执行查询操作
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                message = true;
            }
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

    /**
     * 添加管理员信息
     */
    @Override
    public void addAdmin(Admin admin){
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "insert into admin"
                    // 管理员编号、管理员姓名、管理员密码
                    /*
                     * 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
                     * 这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                     */
                    + "(?,?,?,?,?);";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(1, admin.getAdminId());
            ps.setString(2, admin.getAdminName());
            ps.setString(3, admin.getAdminPassword());
            // 执行查询操作
            resultSet = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }


    /**
     * 删除管理员信息
     */
    @Override
    public void delAdmin(int adminId) {

        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "" +
                    "DELETE FROM admin "+
                    // 参数用?表示，相当于占位符
                    "WHERE adminId = ?";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的1个参数
            ps.setInt(1, adminId);
            // 执行查询操作
            resultSet = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }


    /**
     * 更新管理员信息
     */
    @Override
    public void changeAdmin(Admin admin){

        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql="update admin "
                    + " adminName = ?, adminPassword = ?"
                    // 参数用?表示，相当于占位符
                    + "where adminId = ?";
            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(1, admin.getAdminId());
            ps.setString(2, admin.getAdminName());
            ps.setString(3, admin.getAdminPassword());
            // 执行查询操作
            resultSet = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

}
