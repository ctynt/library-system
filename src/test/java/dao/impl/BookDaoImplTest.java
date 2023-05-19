package dao.impl;

import dao.BookDao;
import domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author ctynt
 * @Date 2023/5/19
 * @Description
 */

class BookDaoImplTest {
BookDao bookDao= new BookDaoImpl();
    @Test
    void addBook() {
        Book book =new Book();
        book.setBookId(6);
        book.setBookName("母猪的产后护理");
        book.setAuthor("二狗");
        book.setCategory("农业");
        book.setState("在馆");

        int i = bookDao.addBook(book);
        assertEquals(1,i);
    }
}