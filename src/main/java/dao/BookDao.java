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
public interface BookDao {


    /**
     * 增加图书信息
     */

    int addBook(Book book);

    int changeBook(Book book);

    int delBook(int bookId);
    Object[][] getBookInfo();
    }

