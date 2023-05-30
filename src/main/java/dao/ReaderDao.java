package dao;
import domain.Reader;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public interface ReaderDao {
//    boolean checkLogin(Reader user);

    boolean checkLogin(Reader user);

    int addReader(Reader reader);
    int changeReader(Reader reader);

    int delReader(int readerId,String readerName);
    Object[][] getReaderInfo();

    Reader findReader(int readerId);
    
    int changeReaderPassword(int readerId, String readerPassword);

}
