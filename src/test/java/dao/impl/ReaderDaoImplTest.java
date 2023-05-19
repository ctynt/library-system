package dao.impl;

import domain.Reader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/19 10:41
 * @Description
 */
class ReaderDaoImplTest {

    @Test
    void addReader() {
        Reader reader = new Reader();
        reader.setReaderId(6);
        reader.setReaderName("血小板");
        reader.setReaderLimit(2);
        reader.setReaderLend(1);
        reader.setReaderPassword("888");

    }
}