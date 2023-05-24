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

    int delBorrow(int borrowId);

    int changeBorrow(Borrow borrow);
}
