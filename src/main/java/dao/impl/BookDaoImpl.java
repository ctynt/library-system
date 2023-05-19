package dao.impl;

import dao.BookDao;
import domain.Book;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class BookDaoImpl implements BookDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil db = new JDBCUtil();

    @Override
    public int addBook(Book book) {
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
            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setString(5, book.getState());
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

        return 0;
    }


    /**
     * 删除图书信息
     */
    @Override
    public void delBook(int bookId) {

        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "" +
                    "DELETE FROM book " +
                    // 参数用?表示，相当于占位符
                    "WHERE bookId = ?";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的1个参数
            ps.setInt(1, bookId);
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
     * 更新图书信息
     */
    @Override
    public void changeBook(Book book) {

        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "update book "
                    + " bookName = ?, Author = ?, category = ?"
                    + ",state = ? "
                    // 参数用?表示，相当于占位符
                    + "where bookId = ?";
            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setString(5, book.getState());
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
