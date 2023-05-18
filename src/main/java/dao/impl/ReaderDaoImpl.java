package dao.impl;

import dao.ReaderDao;
<<<<<<< HEAD
import domain.Reader;
import utils.JDBCUtil;
import utils.StuTableModel;

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

<<<<<<< HEAD
public class ReaderDaoImpl extends StuTableModel implements ReaderDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private JDBCUtil db = new JDBCUtil();
    @Override
    public boolean checkLogin(Reader user) {

        boolean message = false;
        Connection conn = null;
        try {
            // 取得数据库连接
            conn = db.getConnection();
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
            if (rs.next())
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
=======
public class ReaderDaoImpl implements ReaderDao {
>>>>>>> d1facc06bd46a5154b97cfb5e9e5124dbf67cb9d
}
