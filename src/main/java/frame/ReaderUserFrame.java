package frame;


import dao.ReaderDao;
import dao.impl.ReaderDaoImpl;
import domain.Reader;

import javax.swing.*;
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
 * @Description 读者--图书借阅系统--读者个人信息管理
 */
public class ReaderUserFrame extends JFrame {
    JButton update, exit;
    private JPanel contentPane;
    private JLabel idLabel = new JLabel();
    private JLabel oldPasswordLabel = new JLabel();
    private JLabel newPasswordLabel = new JLabel();
    private JLabel userCategoryLabel = new JLabel();
    private JTextField idTextField = new JTextField();
    private JPasswordField oldPasswordField = new JPasswordField();
    private JPasswordField newPasswordField = new JPasswordField();
    private JButton checkBtn = new JButton();
    private JButton exitBtn = new JButton();


    private JComboBox<String> stateComboBox = new JComboBox<String>();
    // 声明数据库操作类

    private ReaderDao readerDao = new ReaderDaoImpl();

    public ReaderUserFrame(String title) {
        this.setTitle(title);
        setFont(new Font("宋体", Font.PLAIN, 35));
        this.setBounds(0, 0, 760, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

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

        public UpdatePasswordFrame() {

            // 取得窗口面板
            contentPane = (JPanel) this.getContentPane();
            // 定义窗口面板的布局
            contentPane.setLayout(null);
            // 定义窗口的大小和标题
            this.setSize(new Dimension(400, 330));
            this.setTitle("修改密码");
            // 定义标签的标题、字符大小和位置
            idLabel.setText("读者编号：");
            idLabel.setBounds(new Rectangle(65, 67, 81, 16));

            oldPasswordLabel.setText("原密码：");
            oldPasswordLabel.setBounds(new Rectangle(65, 112, 81, 16));

            newPasswordLabel.setText("新密码：");
            newPasswordLabel.setBounds(new Rectangle(65,157,81,16));


            // 定义编辑框的位置
            idTextField.setBounds(new Rectangle(194, 67, 118, 22));
            oldPasswordField.setBounds(new Rectangle(194, 112, 118, 22));
            newPasswordField.setBounds(new Rectangle(194,157,118,22));
            // 定义按钮的标题、动作字符串、字符大小、位置和加入动作接收器
            checkBtn.setText("确定");
            checkBtn.setFont(new Font("Dialog", 0, 15));
            checkBtn.setBounds(new Rectangle(65, 204, 109, 25));
            exitBtn.setText("取消");
            exitBtn.setActionCommand("exit");
            exitBtn.setFont(new Font("Dialog", 0, 15));
            exitBtn.setBounds(new Rectangle(203, 204, 109, 25));
            // 为面板加入各个控件
            contentPane.add(idLabel, null);
            contentPane.add(oldPasswordLabel, null);
            contentPane.add(newPasswordLabel, null);

            contentPane.add(idTextField, null);
            contentPane.add(oldPasswordField,null);
            contentPane.add(newPasswordField,null);
            contentPane.add(stateComboBox, null);
            contentPane.add(checkBtn, null);
            contentPane.add(exitBtn, null);
            MyEvent();
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
                    int readerId = parseInt(idTextField.getText());
                    String readerPassword = newPasswordField.getText();
                    String readerPassword1 = oldPasswordField.getText();
                    Reader user = new Reader(readerId,readerPassword1);
                    boolean message = readerDao.checkLogin(user);
                    if(message){
                        int i = readerDao.changeReaderPassword(readerId, readerPassword);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(null, "修改成功！");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "修改失败！该用户可能不存在");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null,"修改失败，原密码错误，请重新输入！");
                    }

                }
            });

            // 取消修改
            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    new ReaderUserFrame(getTitle());
                    dispose();
                }
            });
        }

    }

    public void MyEvent() {
        // 更改密码
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new UpdatePasswordFrame();
            }
        });



        // 退出登录
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                new LoginFrame(getTitle());
            }

        });


    }


    public static void main(String[] args) {
        new ReaderUserFrame("图书借阅系统--读者个人信息管理");
    }
}

