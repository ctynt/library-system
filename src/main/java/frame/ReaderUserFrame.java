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
        this.setTitle(title);
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

    class UpdatePasswordFrame extends JFrame {

        public UpdatePasswordFrame(String text) {

            setTitle("更改密码");// 设置窗体标题
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭方式
            setSize(400, 400);// 设置窗体大小
            new JPanel();// 创建内容面板
            setLayout(new BorderLayout(0, 0));
//            setContentPane();// 设置内容面板
            JLabel label = new JLabel("宽度：250，高度：250");// 创建标签控件
            add(label, BorderLayout.CENTER);// 添加标签控件到窗体
            this.setLocationRelativeTo(null);//窗口在屏幕中间显示
            setVisible(true);
        }

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
            new ReaderUserFrame("个人信息管理");
        }
    }
