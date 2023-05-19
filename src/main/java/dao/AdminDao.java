package dao;

import domain.Admin;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public interface AdminDao {
    boolean checkLogin(Admin user);
}
