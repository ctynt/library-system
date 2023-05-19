package dao.impl;

import domain.Admin;
import domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDaoImplTest {

    @Test
    void addAdmin() {
        Admin admin =new Admin();
        admin.setAdminId(4);
        admin.setAdminName("sb");
        admin.setAdminPassword("00");
    }

}