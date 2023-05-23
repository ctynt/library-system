package dao;

import domain.Book;
import domain.Reader;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public interface ReaderDao {
    boolean checkLogin(Reader user);

    int addReader(Reader reader);
    int changeReader(Reader reader);

    int delReader(int readerId);
    Object[][] getReaderInfo();
}
