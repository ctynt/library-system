package dao;

import domain.Reader;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public interface ReaderDao {
    boolean checkLogin(Reader user);

    int addReader(Reader reader);
}
