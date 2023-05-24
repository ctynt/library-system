package domain;

/**
 * @Author ctynt
 * @Date 2023/5/24
 * @Description
 */

public class Borrow {
    private int bookId;
    private String bookName;
    private int userId;
    private String userName;

    public Borrow(int bookId, String bookName, int userId, String userName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.userId = userId;
        this.userName = userName;
    }

    public Borrow(){

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
