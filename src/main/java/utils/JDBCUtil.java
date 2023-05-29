package utils;

import domain.Reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCUtil {
    static String url = "jdbc:mysql://localhost:3306/db_library?useSSL=false&serverTimezone=UTC";
    static String uname = "root";
    static String password = "password";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static JDBCUtil jdbcUtil = null;

    public static synchronized JDBCUtil getInitJDBCUtil() {
        if (jdbcUtil == null) {
            jdbcUtil = new JDBCUtil();
        }
        return jdbcUtil;
    }

    public JDBCUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, uname, password);
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * insert delete update
     *
     * @param sql
     * @param
     * @return int
     */
    public int executeUpdate(String sql) {
        int affectedline = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            affectedline = preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return affectedline;
    }

    /**
     *  insert delete update
     * @param sql
     * @param params
     * @return int
     */
    public int executeUpdate(String sql, Object[] params) {
        int affectedline = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            affectedline = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedline;
    }

    /**
     *  query
     * @param sql
     * @param params
     * @return ResultSet
     */
    private ResultSet executeQueryRS(String sql, Object[] params) {

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     *  query
     * @param sql
     * @param params
     * @return ArrayList<HashMap>
     */
    public ArrayList<HashMap> executeQuery(String sql, Object[] params) {
        ResultSet rs = executeQueryRS(sql, params);
        ResultSetMetaData rsmd = null;
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap> list = new ArrayList<HashMap>();
        try {
            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}


