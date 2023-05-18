package factory;

import dao.AdminDao;
import dao.BookDao;
import dao.ReaderDao;
import dao.impl.AdminDaoImpl;
import dao.impl.BookDaoImpl;
import dao.impl.ReaderDaoImpl;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/18 14:17
 * @Description
 */
public class DaoFactory {
    public static AdminDao getAdminDaoInstance(){
        return new AdminDaoImpl();
    }
    public static BookDao getBookDaoInstance(){
        return new BookDaoImpl();
    }
    public static ReaderDao getReaderDaoInstance(){
        return new ReaderDaoImpl();
    }
}
