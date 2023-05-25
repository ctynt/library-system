package dao.impl;

import dao.BorrowDao;
import domain.Book;
import domain.Borrow;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author ctynt
 * @Date 2023/5/24
 * @Description
 */

public class BorrowDaoImpl implements BorrowDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil db = new JDBCUtil();
    @Override
    public int addBorrow(Borrow borrow){

        int result = 0;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "insert into book values "
                    // 编号、书名、作者、分类、借阅状态

                    /*
                     * 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
                     * 这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                     */
                    + "(?,?,?,?,?);";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(1, borrow.getBorrowId());
            ps.setInt(2, borrow.getBookId());
            ps.setString(3,borrow.getBookName());
            ps.setInt(4, borrow.getReaderId());
            ps.setString(5, borrow.getReaderName());
            // 执行查询操作
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * 删除图书信息
     */
    @Override
    public int delBorrow(int borrowId) {
        int result = 0;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "" +
                    "DELETE FROM borrow " +
                    // 参数用?表示，相当于占位符
                    "WHERE borrowId = ?";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的1个参数
            ps.setInt(1, borrowId);
            // 执行查询操作
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return result;

    }



@Override
    public int changeBorrow(Borrow borrow){
        int result = 0;

        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql="UPDATE borrow SET"
                    + " bookId = ?, bookName = ?, readerId = ?,readerName = ?"
                    // 参数用?表示，相当于占位符
                    + "WHERE borrowId = ?";
            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
//            ps.setInt(5, borrow.getBorrowId());
            ps.setInt(1, borrow.getBookId());
            ps.setString(2,borrow.getBookName());
            ps.setInt(3, borrow.getReaderId());
            ps.setString(4, borrow.getReaderName());
            ps.setInt(5, borrow.getBorrowId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return result;

    }

}