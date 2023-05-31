package frame;

import com.mysql.cj.protocol.x.XMessage;
import dao.BookDao;
import dao.BorrowDao;
import dao.ReaderDao;
import dao.impl.BookDaoImpl;
import dao.impl.BorrowDaoImpl;
import dao.impl.ReaderDaoImpl;
import domain.Admin;
import domain.Borrow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/30 10:57
 * @Description 读者--图书借阅系统--图书借阅服务--借阅图书
 */
public class BorrowBookFrame extends JFrame {
    JLabel[] label;
    JTextField bookIdText, readerIdText,bookNameText,readerNameText,lendIdText;
    JButton button;

    BorrowDao borrowDao = new BorrowDaoImpl();
    BookDao bookDao = new BookDaoImpl();
    ReaderDao readerDao = new ReaderDaoImpl();
    public BorrowBookFrame() {
        setLayout(null);
        this.setTitle("借阅图书");
        this.setLocationRelativeTo(null);
        this.setBounds(500, 230, 500, 350);

        label = new JLabel[5];
        label[0] = new JLabel("图书编号：");
        label[0].setBounds(new Rectangle(140, 30, 70, 30));
        label[1] = new JLabel("图书姓名：");
        label[1].setBounds(new Rectangle(140, 60, 70, 30));
        label[2] = new JLabel("读者编号：");
        label[2].setBounds(new Rectangle(140, 90, 70, 30));
        label[3] = new JLabel("读者姓名：");
        label[3].setBounds(new Rectangle(140, 120, 70, 30));
        label[4] = new JLabel("借阅编号：");
        label[4].setBounds(new Rectangle(140, 150, 70, 30));

        bookIdText = new JTextField(10);
        bookIdText .setBounds(new Rectangle(210, 35, 140, 20));
        bookNameText = new JTextField(10);
        bookNameText.setBounds(new Rectangle(210, 65, 140, 20));
        readerIdText = new JTextField(10);
        readerIdText .setBounds(new Rectangle(210, 95, 140, 20));
        readerNameText = new JTextField(10);
        readerNameText .setBounds(new Rectangle(210, 125, 140, 20));
        lendIdText = new JTextField(10);
        lendIdText .setBounds(new Rectangle(210, 155, 140, 20));

        button = new JButton("OK");
        button.setBounds(new Rectangle(230, 185, 100, 20));

        this.add(label[0]);
        this.add(label[1]);
        this.add(label[2]);
        this.add(label[3]);
        this.add(label[4]);

        this.add(bookIdText );
        this.add(button);
        this.add(bookNameText);
        this.add(readerIdText );
        this.add(readerNameText);
        this.add(lendIdText);

        MyEvent();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new ReaderBorrowFrame("");
                setVisible(true);
            }
        });
        this.dispose();//子窗口销毁
        setVisible(true);//父窗口变可见
        setResizable(false);

    }

    public void MyEvent() {
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int bookId = parseInt(bookIdText.getText());
                String bookName = bookNameText.getText();
                int readerId = parseInt(readerIdText.getText());
                String readerName = readerNameText.getText();
                int lendId = parseInt(lendIdText.getText());
                boolean message = borrowDao.checkBorrowState(bookId);
                boolean message1 = readerDao.checkReaderBorrow(readerId);
                if (message && message1) {
                    Borrow borrow = new Borrow(lendId,bookId,bookName,readerId,readerName);
                    borrowDao.addBorrow(borrow);
                    String state = "借阅";
                    bookDao.changeBookBorrow(state,bookId);
                    readerDao.changeReaderLend(bookId,readerId);
                    JOptionPane.showMessageDialog(null, "借阅成功！");
                    dispose();
                } else  {
                    JOptionPane.showMessageDialog(null, "该图书已被借阅或您正在借阅的书籍未归还，借阅失败！");
                }
            }

        });
    }



    public static void main(String[] args) {
        new BorrowBookFrame();
    }
}
