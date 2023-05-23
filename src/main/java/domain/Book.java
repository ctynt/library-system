package domain;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String category;
    private String state;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Book(int bookId, String bookName, String author, String category, String state) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.state = state;
    }
    public Book(){

    }
}
