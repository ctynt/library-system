package frame;


import dao.ReaderDao;
import dao.impl.ReaderDaoImpl;
import domain.Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Integer.parseInt;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/29 8:08
 * @Description
 */
public class ReaderUserFrame extends JFrame {
    JButton update, exit;
    private JPanel contentPane;
    private JLabel nameLabel = new JLabel();
    private JLabel passwordLabel = new JLabel();
    private JLabel userCategoryLabel = new JLabel();
    private JTextField nameTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton checkBtn = new JButton();
    private JButton exitBtn = new JButton();
    DefaultTableModel tableModel;
    JTextField idText, nameText, passwordText, lendText;
    JButton button;


    private JComboBox<String> stateComboBox = new JComboBox<String>();
    // 声明数据库操作类

    private ReaderDao readerDao = new ReaderDaoImpl();

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

        update = new JButton("修改密码");
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
            // 取得窗口面板
            contentPane = (JPanel) this.getContentPane();
            // 定义窗口面板的布局
            contentPane.setLayout(null);
            // 定义窗口的大小和标题
            this.setSize(new Dimension(400, 330));
            this.setTitle("修改密码");
            // 定义标签的标题、字符大小和位置
            nameLabel.setText("读者编号：");
            nameLabel.setFont(new Font("Dialog", 0, 15));
            nameLabel.setBounds(new Rectangle(65, 67, 81, 16));
            passwordLabel.setText("新密码：");
            passwordLabel.setFont(new Font("Dialog", 0, 15));
            passwordLabel.setBounds(new Rectangle(65, 112, 79, 16));
            // 定义编辑框的位置
            nameTextField.setBounds(new Rectangle(194, 67, 118, 22));
            passwordField.setBounds(new Rectangle(194, 112, 118, 22));
            // 定义按钮的标题、动作字符串、字符大小、位置和加入动作接收器
            checkBtn.setText("确定");
            checkBtn.setActionCommand("login");
            checkBtn.setFont(new Font("Dialog", 0, 15));
            checkBtn.setBounds(new Rectangle(65, 204, 109, 25));
            exitBtn.setText("取消");
            exitBtn.setActionCommand("exit");
            exitBtn.setFont(new Font("Dialog", 0, 15));
            exitBtn.setBounds(new Rectangle(203, 204, 109, 25));
            // 为面板加入各个控件
            contentPane.add(nameLabel, null);
            contentPane.add(nameTextField, null);
            contentPane.add(passwordLabel, null);
            contentPane.add(passwordField, null);
            contentPane.add(userCategoryLabel, null);
            contentPane.add(stateComboBox, null);
            contentPane.add(checkBtn, null);
            contentPane.add(exitBtn, null);
            setVisible(true);
            setResizable(false); // JFrame不可以改变大小
            setLocationRelativeTo(getOwner()); // JFrame打开后居中
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

        public void MyEvent() {
            checkBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int readerId = parseInt(idText.getText());
                    String readerPassword = passwordText.getText();
                    Reader reader = new Reader(readerId, readerPassword);
                    int i = readerDao.changeReader(reader);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "修改成功！");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败！");
                    }
                }
            });
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

            // 确认修改
            checkBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {

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

            // 取消修改
            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    new ReaderUserFrame(exitBtn.getText());
                    dispose();
                }

            });
        }


        public static void main(String[] args) {
            new ReaderUserFrame("个人信息管理");
        }
    }
