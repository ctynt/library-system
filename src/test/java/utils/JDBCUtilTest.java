package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

class JDBCUtilTest {
    @Test
    public void dbTest() {
        JDBCUtil dbConnection = new JDBCUtil();
        dbConnection.setupConn();
    }

}