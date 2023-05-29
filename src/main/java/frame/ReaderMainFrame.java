package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zhuhuiyu
 */
public class ReaderMainFrame extends JFrame {
    JButton storage, borrowInfo, userInfo;

    public ReaderMainFrame() {
        super("图书借阅系统--读者服务");
        setFont(new Font("宋体", Font.PLAIN, 35));
        this.setBounds(0, 0, 760, 400);
        this.setLocationRelativeTo(null);
        // 让窗口在屏幕中间显示
        this.setResizable(false);
        this.setLayout(null);
        // 用户单击窗口的关闭按钮时程序执行的操作

        JLabel lblxxx = new JLabel("你好,读者,欢迎使用图书借阅系统！");
        lblxxx.setFont(new Font("宋体", Font.PLAIN, 35));
        lblxxx.setBounds(44, 35, 726, 91);
        this.add(lblxxx);

        storage = new JButton("图书信息浏览");
        storage.setFont(new Font("宋体", Font.BOLD, 20));
        storage.setBounds(80, 192, 166, 59);

        this.add(storage);

        borrowInfo = new JButton("图书借阅服务");
        borrowInfo.setFont(new Font("宋体", Font.BOLD, 20));
        borrowInfo.setBounds(292, 192, 166, 59);

        this.add(borrowInfo);

        userInfo = new JButton("个人信息管理");
        userInfo.setFont(new Font("宋体", Font.BOLD, 20));
        userInfo.setBounds(515, 192, 166, 59);
        this.add(userInfo);

        MyEvent();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void MyEvent() {
        // 图书信息浏览
        storage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new ReaderStorageFrame(storage.getText());
                dispose();
            }

        });

        // 图书借阅服务
        borrowInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new ReaderBorrowFrame(borrowInfo.getText());
                dispose();
            }

        });
//个人信息管理
        userInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new ReaderUserFrame(userInfo.getText());
                dispose();
            }

        });
    }

    public static void main(String[] args) {
        new ReaderMainFrame().setVisible(true);
    }
}
