package frame;

import dao.AdminDao;
import dao.ReaderDao;
import dao.impl.AdminDaoImpl;
import dao.impl.ReaderDaoImpl;
import domain.Admin;
import domain.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class LoginFrame extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JLabel nameLabel = new JLabel();
	private JLabel passwordLabel = new JLabel();
	private JLabel userCategoryLabel = new JLabel();
	private JTextField nameTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginBtn = new JButton();
	private JButton exitBtn = new JButton();
	private JComboBox<String> stateComboBox = new JComboBox<String>();
	// 声明数据库操作类

	private ReaderDao readerDao = new ReaderDaoImpl();
	private AdminDao adminDAO = new AdminDaoImpl();

	public LoginFrame() {
		// 取得窗口面板
		contentPane = (JPanel) this.getContentPane();
		// 定义窗口面板的布局
		contentPane.setLayout(null);
		// 定义窗口的大小和标题
		this.setSize(new Dimension(400, 330));
		this.setTitle("用户登录");
		// 定义标签的标题、字符大小和位置
		nameLabel.setText("用户名：");
		nameLabel.setFont(new Font("Dialog", 0, 15));
		nameLabel.setBounds(new Rectangle(65, 67, 81, 16));
		passwordLabel.setText("密码：");
		passwordLabel.setFont(new Font("Dialog", 0, 15));
		passwordLabel.setBounds(new Rectangle(65, 112, 79, 16));
		userCategoryLabel.setText("用户类别：");
		userCategoryLabel.setFont(new Font("Dialog", 0, 15));
		userCategoryLabel.setBounds(new Rectangle(65, 156, 79, 16));
		// 定义编辑框的位置
		nameTextField.setBounds(new Rectangle(194, 67, 118, 22));
		passwordField.setBounds(new Rectangle(194, 112, 118, 22));
		// 定义按钮的标题、动作字符串、字符大小、位置和加入动作接收器
		loginBtn.setText("登录");
		loginBtn.setActionCommand("login");
		loginBtn.setFont(new Font("Dialog", 0, 15));
		loginBtn.setBounds(new Rectangle(65, 224, 109, 25));
		loginBtn.addActionListener(this);
		exitBtn.setText("退出");
		exitBtn.setActionCommand("exit");
		exitBtn.setFont(new Font("Dialog", 0, 15));
		exitBtn.setBounds(new Rectangle(203, 224, 109, 25));
		exitBtn.addActionListener(this);
		// 定义下拉列表框的内容、第1个显示项和位置
		stateComboBox.addItem("读者");
		stateComboBox.addItem("管理员");
		stateComboBox.setSelectedIndex(0);
		stateComboBox.setBounds(new Rectangle(194, 158, 118, 22));
		// 为面板加入各个控件
		contentPane.add(nameLabel, null);
		contentPane.add(nameTextField, null);
		contentPane.add(passwordLabel, null);
		contentPane.add(passwordField, null);
		contentPane.add(userCategoryLabel, null);
		contentPane.add(stateComboBox, null);
		contentPane.add(loginBtn, null);
		contentPane.add(exitBtn, null);
		setVisible(true);
		setResizable(false); // 设置JFrame是否可以改变大小
		setLocationRelativeTo(getOwner()); // JFrame打开后居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 按钮单击事件处理代码
	@Override
	public void actionPerformed(ActionEvent e) {
		// 创建返回的字符串
		boolean message = false;
		// 取得按钮的动作字符串
		String actionCommand = e.getActionCommand().trim();
		// 取得用户名
		String id = nameTextField.getText();
		// 取得密码
		String password = new String(passwordField.getPassword());
		// 取得用户类别
		int state = stateComboBox.getSelectedIndex();
		if (actionCommand.equals("login")) {
			if (state == 0) {
				Reader user = new Reader(parseInt(id),password);
				message = readerDao.checkLogin(user);
				if(message) {
					JOptionPane.showMessageDialog(null, "登录成功！");
					this.dispose();
//					new MainFrame(0, user.getReaderId());
				}
				else{
					JOptionPane.showMessageDialog(null, "登录失败！");
					this.dispose();
				}

			}  else {
				Admin user = new Admin(parseInt(id), password);
				message = adminDAO.checkLogin(user);
				if(message) {
					JOptionPane.showMessageDialog(null, "登录成功！");
					this.dispose();
//					new MainFrame(2, user.getName());
				}
				else{
					JOptionPane.showMessageDialog(null, "登录失败！");
					this.dispose();
				}
			}

		}

		else if (actionCommand.equals("exit")) {
			// 清空内存
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		new LoginFrame();

	}
}
