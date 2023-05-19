package domain;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class Admin {
    public static String adminName;
    private int adminId;
//    private String adminName;
    private String adminPassword;

    public Admin() {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        Admin.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
