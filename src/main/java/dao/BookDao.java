package dao;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */



import domain.Book;

public interface BookDao {
    /**
     * 增加图书信息
     */

    int addBook(Book book);

    int changeBook(Book book);

    int delBook(int bookId);
    Object[][] getBookInfo();

    }

