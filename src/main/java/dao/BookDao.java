package dao;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import utils.JDBCUtil;

/**
 * 数据库图书表信息数据访问对象类，包含增加图书信息、删除图书信息
 * 、更新图书信息、查询图书信息、查询借阅信息和归还图书
 *
 * @author 1651200111 陈彦志
 */
public class BookDao {


    /**
     * 增加图书信息
     */
    public void addBook(Book book) throws Exception{
        // 首先拿到数据库的连接
        Connection con = JDBCUtil.getConnection();
        String sql="insert into book"
                // 编号、书名、作者、分类、借阅状态
                + "(bookID, bookName, author, category, state,"
                /*
                 * 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
                 * 这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                 */
                + "?,?,?,?,?)";
        /*
         * prepareStatement这个方法会将SQL语句加载到驱动程序conn集成程序中，
         * 但是并不直接执行,而是当它调用execute()方法的时候才真正执行；
         */
        PreparedStatement psmt = con.prepareStatement(sql);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, book.getBookId());
        psmt.setString(2, book.getBookName());
        psmt.setString(3, book.getAuthor());
        psmt.setString(4, book.getCategory());
        psmt.setString(5, book.getState());

        //执行SQL语句
        psmt.execute();

    }


    /**
     * 删除图书信息
     */
    public void delBook(int ID) throws SQLException{
        // 首先拿到数据库的连接
        Connection con=JDBCUtil.getConnection();
        String sql="" +
                "DELETE FROM tb_books "+
                // 参数用?表示，相当于占位符
                "WHERE ID = ?";
        // 预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, ID);
        // 执行SQL语句
        psmt.execute();
    }


    /**
     * 更新图书信息
     */
    public void changeBook(Book book) throws SQLException{
        // 首先拿到数据库的连接
        Connection con=JDBCUtil.getConnection();
        String sql="update tb_books "
                + " bookName = ?, bookAuthor = ?, category = ?"
                + ",state = ? "
                // 参数用?表示，相当于占位符
                + "where bookId = ?";
        // 预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        // 先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, book.getBookId());
        psmt.setString(2, book.getBookName());
        psmt.setString(3, book.getAuthor());
        psmt.setString(4, book.getCategory());
        psmt.setString(5, book.getState());

        // 执行SQL语句
        psmt.execute();
    }

