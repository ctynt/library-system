package dao.impl;

import dao.AdminDao;
import domain.Admin;
import domain.Admin;
import domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDaoImplTest {
    AdminDao adminDao=new AdminDaoImpl();
    @Test
    void addAdmin() {
        Admin admin =new Admin();
        admin.setAdminId(4);
        admin.setAdminName("sb");
        admin.setAdminPassword("00");

        int i = adminDao.addAdmin(admin);
        assertEquals(1,i);
    }

    @Test
    void checkLogin() {
    }

    @Test
    void delAdmin() {
        int i = adminDao.delAdmin(4);
        assertEquals(1,i);
    }

    @Test
    void changeAdmin() {
        Admin admin =new Admin();
        admin.setAdminId(4);
        admin.setAdminName("st");
        admin.setAdminPassword("01");
        int i = adminDao.changeAdmin(admin);
    }
}