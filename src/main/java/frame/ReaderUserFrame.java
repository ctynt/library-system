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
 * @Date 2023/5/29 8:08
 * @Description
 */
public class ReaderUserFrame extends JFrame {
    JButton update, exit;

    public ReaderUserFrame(String title) {
        super("图书借阅系统--读者个人信息管理");
        setFont(new Font("宋体", Font.PLAIN, 35));
        this.setBounds(0, 0, 760, 400);
        this.setLocationRelativeTo(null);
        // 让窗口在屏幕中间显示
        this.setResizable(false);
        this.setLayout(null);
        // 用户单击窗口的关闭按钮时程序执行的操作

        JLabel lblxxx = new JLabel("你好,读者！");
        lblxxx.setFont(new Font("宋体", Font.PLAIN, 48));
        lblxxx.setBounds(245, 35, 726, 91);
        this.add(lblxxx);

        update = new JButton("更改密码");
        update.setFont(new Font("宋体", Font.BOLD, 20));
        update.setBounds(130, 192, 166, 59);

        this.add(update);

        exit = new JButton("退出登录");
        exit.setFont(new Font("宋体", Font.BOLD, 20));
        exit.setBounds(416, 192, 166, 59);

        this.add(exit);
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
        // 更改密码
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new UpdatePasswordFrame(update.getText());
                dispose();
            }

        });

        // 退出登录
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new LoginFrame(exit.getText());
                dispose();
            }

        });
    }

    public static void main(String[] args) {
        new ReaderUserFrame("图书借阅系统--读者个人信息管理");
    }
}
