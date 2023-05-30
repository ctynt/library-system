package dao;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */



import domain.Book;
import domain.Borrow;

public interface BookDao {
    /**
     * 增加图书信息
     */

    int addBook(Book book);

    int changeBook(Book book);

    int delBook(int bookId,String bookName);
    Object[][] getBookInfo();

    Book findBook(int bookId);

    int changeBookBorrow(Borrow borrow);

    }

