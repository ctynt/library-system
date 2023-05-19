package dao;

import domain.Admin;
import domain.Book;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public interface AdminDao {
    boolean checkLogin(Admin user);
    void addAdmin(Admin admin);

    void changeAdmin(Admin admin);

    void delAdmin(int AdminId);
}
