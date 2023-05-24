package dao.impl;

import dao.BorrowDao;
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


}
