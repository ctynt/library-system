package domain;

/**
 * @Author ctynt
 * @Date 2023/5/24
 * @Description
 */

public class Borrow {

    private int borrowId;
    private int bookId;
    private String bookName;
    private int readerId;
    private String readerName;

    public Borrow(int borrowId, int bookId, String bookName, int readerId, String readerName) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.readerId = readerId;
        this.readerName = readerName;
    }

    public Borrow(){}

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
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

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
}
