package dao.impl;

import dao.ReaderDao;
import domain.Book;
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
ReaderDao readerDao = new ReaderDaoImpl();

    @Test
    void addReader() {
        Reader reader = new Reader();
        reader.setReaderId(6);
        reader.setReaderName("血小板");
        reader.setReaderLend(1);
        reader.setReaderPassword("888");

        int i = readerDao.addReader(reader);
        assertEquals(1,i);

    }

    @Test
    void delReader() {
        int i = readerDao.delReader(6,"王大炮");
//        assertEquals(1,i);

    }
    @Test
    void changeReader(){
        Reader reader = new Reader();
        reader.setReaderId(6);
        reader.setReaderName("血小板");
        reader.setReaderLend(1);
        reader.setReaderPassword("777");
        int i = readerDao.changeReader(reader);
    }

    @Test
    void findReader() {
        readerDao.findReader(1);
    }
}