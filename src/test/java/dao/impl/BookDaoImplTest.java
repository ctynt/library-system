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

    BookDao bookDao=new BookDaoImpl();
//@Test
//    void addBook() {
//        Book book =new Book();
//        book.setBookId(6);
//        book.setBookName("母猪的产后护理");
//        book.setAuthor("二狗");
//        book.setCategory("农业");
//        book.setState("在馆");
//
//        int i = bookDao.addBook(book);
//        assertEquals(1,i);
//    }

    @Test
    void delBook() {
<<<<<<< HEAD
        int i = bookDao.delBook(4);
        assertEquals(1,i);
=======
        int i = bookDao.delBook(6);
//        assertEquals(1,i);
>>>>>>> 171ccb0b8f0c518256dd3b2a38135430b3fd191d

    }

    @Test
    void changeBook(){
        Book book =new Book();
        book.setBookId(6);
        book.setBookName("母猪的产后护理");
        book.setAuthor("王二狗");
        book.setCategory("农业");
        book.setState("借阅");
        int i = bookDao.changeBook(book);

    }
}