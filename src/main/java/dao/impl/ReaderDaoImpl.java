package dao.impl;

import dao.ReaderDao;
import domain.Reader;
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

public  class ReaderDaoImpl implements ReaderDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil jdbcUtil = new JDBCUtil();

    @Override
    public boolean checkLogin(Reader user) {

        boolean message = false;
        Connection conn = null;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "select * from reader where readerId = ? and readerPassword = ? ";
            // 创建数据集
            ResultSet rs;
            // 创建查询的PreparedStatement类
            PreparedStatement queryPs = conn.prepareStatement(sql);
            // 设置查询类的3个参数
            queryPs.setInt(1, user.getReaderId());
            queryPs.setString(2, user.getReaderPassword());
            // 执行查询操作
            rs = queryPs.executeQuery();
            if (rs.next()) {
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
     * 添加读者信息
     *
     * @return
     */
    @Override
    public int addReader(Reader user) {
        int result = 0;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "insert into reader values "
                    // 读者编号，读者姓名，借书限额，用户密码，已借读书编号

                    /*
                     * 参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，
                     * 这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                     */ + "(?,?,?,?,?);";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(1, user.getReaderId());
            ps.setString(2, user.getReaderName());
            ps.setInt(3, user.getReaderLimit());
            ps.setString(4, user.getReaderPassword());
            ps.setInt(5, user.getReaderLend());
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
     * 删除读者信息
     */
    @Override
    public int delReader(int readerId) {
        int result = 0;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "" + "DELETE FROM reader " +
                    // 参数用?表示，相当于占位符
                    "WHERE readerId = ?";

            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的1个参数
            ps.setInt(1, readerId);
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
    public Object[][] getReaderInfo() {

        ArrayList<Reader> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reader";
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Reader reader = new Reader(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getInt(5));
                list.add(reader);
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
            objects[i][0] = list.get(i).getReaderId();
            objects[i][1] = list.get(i).getReaderName();
            objects[i][2] = list.get(i).getReaderLimit();
            objects[i][3] = list.get(i).getReaderPassword();
            objects[i][4] = list.get(i).getReaderLend();
        }

        return objects;
    }

    /**
     * 更新读者信息
     */
    @Override
    public int changeReader(Reader user) {
        int result = 0;
        try {
            // 取得数据库连接
            conn = JDBCUtil.getConnection();
            // 创建数据表的查询SQL语句
            String sql = "update reader set" + " readerName = ?, readerLimit= ?,readerPassword = ?" + ",readerLend = ? "
                    // 参数用?表示，相当于占位符
                    + "where readerId = ?";
            // 创建查询的PreparedStatement类
            ps = conn.prepareStatement(sql);
            // 设置查询类的5个参数
            ps.setInt(5, user.getReaderId());
            ps.setString(1, user.getReaderName());
            ps.setInt(2, user.getReaderLimit());
            ps.setString(3, user.getReaderPassword());
            ps.setInt(4, user.getReaderLend());
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

    //    查询借阅
    public Reader findReader(int readerId) {
        Reader reader=new Reader();
        String sql = "select * from reader where readerId=?";
        Object[] params = {readerId};
        ArrayList<HashMap> list = jdbcUtil.executeQuery(sql, params);
        System.out.println(list.size());
        System.out.println(list);
        if(list.size()>0){
            HashMap map = list.get(0);
            Integer readerId0=Integer.parseInt(map.get("readerId").toString());
            String readerName=map.get("readerName").toString();
            Integer readerLimit=Integer.parseInt(map.get("readerLimit").toString());
            String readerPassword=map.get("readerPassword").toString();
            Integer readerLend=Integer.parseInt(map.get("readerLend").toString());
            reader.setReaderName(readerName);
            reader.setReaderLimit(readerLimit);
            reader.setReaderId(readerId0);
            reader.setReaderPassword(readerPassword);
            reader.setReaderLend(readerLend);
            System.out.println(reader);
        }

        return reader;

    }
}
