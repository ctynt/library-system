package dao.impl;

import dao.BookDao;
import domain.Book;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class BookDaoImpl implements BookDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil jdbcUtil =JDBCUtil.getInitJDBCUtil();

    @Override

    public int addBook(Book book){
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
    public int delBook(int bookId,String bookName) {
        int result = 0;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "" +
                    "DELETE FROM book " +
                    // 参数用?表示，相当于占位符
                    "WHERE bookId = ? AND bookName = ?";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的1个参数
            ps.setInt(1, bookId);
            ps.setString(2,bookName);
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
     * 更新图书信息
     */
    @Override

    public int changeBook(Book book){
        int result = 0;

        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql="UPDATE book SET"
                    + " bookName = ?, author = ?, category = ?"
                    + ",state = ? "
                    // 参数用?表示，相当于占位符
                    + "WHERE bookId = ?";
            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(5, book.getBookId());
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getState());

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
    public Object[][] getBookInfo(){

        ArrayList<Book> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book";
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Book book= new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5));
                list.add(book);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Object[][] objects = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            objects[i][0] = list.get(i).getBookId();
            objects[i][1] = list.get(i).getBookName();
            objects[i][2] = list.get(i).getAuthor();
            objects[i][3] = list.get(i).getCategory();
            objects[i][4] = list.get(i).getState();
        }

        return objects;
    }

//    查询图书
    @Override
public Book findBook(int bookId) {
    Book book=new Book();
    String sql = "select * from book where bookId=?";
    Object[] params = {bookId};
    ArrayList<HashMap> list = jdbcUtil.executeQuery(sql, params);
    if(list.size()>0){
        HashMap map = list.get(0);
        Integer bookId0=Integer.parseInt(map.get("bookId").toString());
        String bookName=map.get("bookName").toString();
        String author=map.get("author").toString();
        String  category=map.get("category").toString();
        String state=map.get("state").toString();
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setCategory(category);
        book.setBookId(bookId0);
        book.setState(state);
        System.out.println(book);
    }

    return book;

}
}
