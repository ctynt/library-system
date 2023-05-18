package model;

/**
 * @Author ctynt
 * @Date 2023/5/18
 * @Description
 */

public class Reader {
    private int readerId;
    private String readerName;
    private int readerLimit;
    private String readerPassword;
    private int readerLend;

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

    public int getReaderLimit() {
        return readerLimit;
    }

    public void setReaderLimit(int readerLimit) {
        this.readerLimit = readerLimit;
    }

    public String getReaderPassword() {
        return readerPassword;
    }

    public void setReaderPassword(String readerPassword) {
        this.readerPassword = readerPassword;
    }

    public int getReaderLend() {
        return readerLend;
    }

    public void setReaderLend(int readerLend) {
        this.readerLend = readerLend;
    }
}
