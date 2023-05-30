package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/30 10:57
 * @Description  读者--图书借阅系统--图书借阅服务-归还图书
 */
public class ReturnBookFrame extends JFrame {
    public ReturnBookFrame(String title) {
        super("图书借阅服务-归还图书");
        this.setBounds(300, 200, 850, 450);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new ReaderBorrowFrame("");
                setVisible(true);
            }
        });

        //子窗口销毁
        this.dispose();
        //父窗口变可见
        setVisible(true);

        //窗口大小不可变
        setResizable(false);
    }

    public static void main(String[] args) {
        new ReturnBookFrame("图书借阅服务-归还图书");
    }

}
