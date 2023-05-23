package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author ctynt
 * @Date 2023/5/22
 * @Description
 */

public class AdminMainFrame extends JFrame {
    JPanel panel;
    JButton storage, lendInfo, userInfo;

    public AdminMainFrame() {
//        this.setTitle("管理员");
//        this.setBounds(500,200,400,400);
//        this.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//        storage = new JButton("图书信息管理");
//        lendInfo = new JButton("借阅信息管理");
//        user = new JButton("用户信息管理");
//
//
//        this.add(user);
//        this.add(storage);
//        this.add(lendInfo);
//
//        MyEvent();
//
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super("图书管理系统--管理员后台");
        setFont(new Font("宋体", Font.PLAIN, 35));
        this.setBounds(0, 0, 760, 400);
        this.setLocationRelativeTo(null);
        // 让窗口在屏幕中间显示
        this.setResizable(false);
        this.setLayout(null);
        // 用户单击窗口的关闭按钮时程序执行的操作

        JLabel lblxxx = new JLabel("你好,管理员,欢迎使用图书管理系统！");
        lblxxx.setFont(new Font("宋体", Font.PLAIN, 35));
        lblxxx.setBounds(44, 35, 726, 91);
        this.add(lblxxx);

        storage = new JButton("图书信息管理");
        storage.setFont(new Font("宋体", Font.BOLD, 20));
        storage.setBounds(80, 192, 166, 59);

        this.add(storage);

        lendInfo = new JButton("借阅信息管理");
        lendInfo.setFont(new Font("宋体", Font.BOLD, 20));
        lendInfo.setBounds(292, 192, 166, 59);

        this.add(lendInfo);

        userInfo = new JButton("用户信息管理");
        userInfo.setFont(new Font("宋体", Font.BOLD, 20));
        userInfo.setBounds(515, 192, 166, 59);
        this.add(userInfo);

        MyEvent();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void MyEvent() {
        // 图书信息管理
        storage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new AdminStorageFrame(storage.getText());
                dispose();
            }

        });

        // 借阅信息管理
        lendInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new AdminLendFrame(lendInfo.getText());
                dispose();
            }

        });
//用户信息管理
        userInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new AdminUserFrame(userInfo.getText());
                dispose();
            }

        });
    }

    public static void main(String[] args) {
        new AdminMainFrame().setVisible(true);
    }
}

