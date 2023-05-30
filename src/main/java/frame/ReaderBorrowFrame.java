package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/29 8:09
 * @Description 读者--图书借阅系统--图书借阅服务
 */
public class ReaderBorrowFrame extends JFrame {
    JButton storage, borrowInfo, returnInfo;

    public ReaderBorrowFrame(String title) {
        super("图书借阅系统--图书借阅服务");
        setFont(new Font("宋体", Font.PLAIN, 35));
        this.setBounds(0, 0, 760, 400);
        this.setLocationRelativeTo(null);
        // 让窗口在屏幕中间显示
        this.setResizable(false);
        this.setLayout(null);
        // 用户单击窗口的关闭按钮时程序执行的操作
        JLabel lblxxx = new JLabel("你好,读者,欢迎使用图书借阅服务！");
        lblxxx.setFont(new Font("宋体", Font.PLAIN, 35));
        lblxxx.setBounds(100, 35, 726, 91);
        this.add(lblxxx);

        storage = new JButton("借阅信息");
        storage.setFont(new Font("宋体", Font.BOLD, 20));
        storage.setBounds(80, 192, 166, 59);
        this.add(storage);

        borrowInfo = new JButton("借阅图书");
        borrowInfo.setFont(new Font("宋体", Font.BOLD, 20));
        borrowInfo.setBounds(292, 192, 166, 59);
        this.add(borrowInfo);

        returnInfo = new JButton("归还图书");
        returnInfo.setFont(new Font("宋体", Font.BOLD, 20));
        returnInfo.setBounds(515, 192, 166, 59);
        this.add(returnInfo);

        MyEvent();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new ReaderMainFrame().setVisible(true);
            }
        });
        //子窗口销毁
        this.dispose();
        //父窗口变可见
        setVisible(true);
        //窗口大小不可变
        setResizable(false);
    }

    public void MyEvent() {
        // 借阅信息
        storage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new BookStorageFrame(storage.getText());
                dispose();
            }

        });

        // 借阅图书
        borrowInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new BorrowBookFrame();
                dispose();
            }
        });
        //归还图书
        returnInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new ReturnBookFrame();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new ReaderBorrowFrame("图书借阅系统--图书借阅服务");
    }
}
