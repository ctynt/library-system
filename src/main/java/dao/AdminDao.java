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
    int addAdmin(Admin admin);

    int changeAdmin(Admin admin);

    int delAdmin(int AdminId);
}
