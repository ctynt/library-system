package dao;

import domain.Book;
import domain.Borrow;

/**
 * @Author ctynt
 * @Date 2023/5/24
 * @Description
 */

public interface BorrowDao {
    int addBorrow(Borrow borrow);

    int delBorrow(int bookId,int readerId );

    int changeBorrow(Borrow borrow);

    Object[][] getBorrowInfo();

    Borrow findBorrow(int borrowId);

    boolean checkBorrowState(int bookId);


}
