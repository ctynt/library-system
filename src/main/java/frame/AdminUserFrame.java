package frame;

import dao.ReaderDao;
import dao.impl.ReaderDaoImpl;
import domain.Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class AdminUserFrame extends JFrame {
    ReaderDao readerDao = new ReaderDaoImpl();
    DefaultTableModel tableModel;
    Vector vector;
    JMenuBar menuBar;
    JButton add, del, exit, find, change;
    JTable table;
    /*增加信息的面板*/
    JPanel panelUP, panelDown;
    /*内部类中的变量*/
    JLabel[] label;
    JTextField idText, nameText, passwordText, lendText;
    JPanel panel, panelSouth;
    JButton button;
    String[] str = null;
    JPanel[] panelLeft, panelRight;

    Object[] header = {"读者编号", "读者姓名",  "用户密码", "已借图书编号"};

    Object[][] data;

    ReaderDaoImpl readerDaoImpl = new ReaderDaoImpl();

    public AdminUserFrame(String title) {
        this.setBounds(300, 200, 850, 450);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setLayout(new BorderLayout());

        add = new JButton("增加");
        del = new JButton("删除");
        change = new JButton("修改");
        find = new JButton("查找");
        exit = new JButton("退出");

        panelUP = new JPanel();
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelUP.add(add);
        panelUP.add(del);
        panelUP.add(change);
        panelUP.add(find);
        panelUP.add(exit);

        Vector rowData = null;
        Vector columnNames = null;

        data = readerDaoImpl.getReaderInfo();
        tableModel = new DefaultTableModel(data, header);
        table = new JTable(tableModel);

        // 创建表格
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.black);
        // 设置表头名称字体颜色
        // jTable.getTableHeader().setResizingAllowed(false);
        // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);
        // 设置表头不允许拖动
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //单元格居中
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        // 水平滚动条
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        // 垂直滚动条
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminMainFrame().setVisible(true);
            }
        });
        JScrollPane jsp = new JScrollPane(table, v, h);
        jsp.setBounds(0, 36, 850, 500);
        this.add(jsp);
//         新建表格
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(22);

        JScrollPane s = new JScrollPane(table);

        this.add(panelUP, BorderLayout.NORTH);
        this.add(s);

        MyEvent();

        this.dispose();//子窗口销毁
        setVisible(true);//父窗口变可见

        //窗口大小不可变
        setResizable(false);
    }

    /*增加读者信息*/
    class AddFrame extends JFrame {


        public AddFrame() {
            this.setTitle("添加读者");
            this.setBounds(300, 200, 500, 350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8, 2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("读者编号：");
            label[1] = new JLabel("读者姓名：");

            label[3] = new JLabel("用户密码：");
            label[4] = new JLabel("已借图书编号：");

            idText = new JTextField(10);
            nameText = new JTextField(10);

            passwordText = new JTextField(10);
            lendText = new JTextField(10);

            panelRight = new JPanel[5];
            panelLeft = new JPanel[5];
            for (int i = 0; i < panelRight.length; i++) {
                panelRight[i] = new JPanel();
                panelRight[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            }

            for (int i = 0; i < panelLeft.length; i++) {
                panelLeft[i] = new JPanel();
                panelLeft[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            }

            for (int i = 0; i < panelLeft.length; i++) {
                for (int j = i; j < label.length; j++) {
                    panelLeft[i].add(label[j]);
                }
            }

            panelRight[0].add(idText);
            panelRight[1].add(nameText);

            panelRight[3].add(passwordText);
            panelRight[4].add(lendText);

            panel.add(panelLeft[0]);
            panel.add(panelRight[0]);
            panel.add(panelLeft[1]);
            panel.add(panelRight[1]);
            panel.add(panelLeft[2]);
            panel.add(panelRight[2]);
            panel.add(panelLeft[3]);
            panel.add(panelRight[3]);
            panel.add(panelLeft[4]);
            panel.add(panelRight[4]);
            this.add(panelSouth, BorderLayout.SOUTH);
            this.add(panel);
            MyEvent();
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }


        public void refresh() {
            Object[][] data = readerDaoImpl.getReaderInfo();
            tableModel.setDataVector(data, header);
        }

        public void MyEvent() {
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int readerId = parseInt(idText.getText());
                    String readerName = nameText.getText();

                    String readerPassword = passwordText.getText();
                    int readerLend = parseInt(lendText.getText());
                    Reader reader = new Reader(readerId, readerName,  readerPassword, readerLend);

                    int i = readerDao.addReader(reader);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！");
                        refresh();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！");
                    }
                }

            });
        }
    }

    /*删除读者信息*/
    class DelFrame extends JFrame {


        public DelFrame() {
            setLayout(null);
            this.setTitle("删除读者信息");
            this.setLocationRelativeTo(null);
            this.setBounds(300, 200, 500, 350);

            label = new JLabel[2];
            label[0] = new JLabel("读者编号：");
            label[1] = new JLabel("读者名称：");
            label[0].setBounds(new Rectangle(140, 30, 70, 30));
            label[1].setBounds(new Rectangle(140, 60, 70, 30));

            idText = new JTextField();
            nameText = new JTextField();

            idText.setBounds(new Rectangle(210, 35, 140, 20));
            nameText.setBounds(new Rectangle(210, 65, 140, 20));

            button = new JButton("OK");
            button.setBounds(new Rectangle(210, 120, 100, 20));

            this.add(label[0]);
            this.add(label[1]);

            this.add(idText);
            this.add(button);
            this.add(nameText);

            MyEvent();
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }

        public void refresh() {
            Object[][] data = readerDaoImpl.getReaderInfo();
            tableModel.setDataVector(data, header);
        }

        public void MyEvent() {
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int readerId = parseInt(idText.getText());
                    String readerName = nameText.getText();
                    int i = readerDao.delReader(readerId, readerName);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        refresh();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "该读者不存在，删除失败！");
                    }
                }

            });
        }
    }

    /*修改读者信息*/
    class ChangeFrame extends JFrame {


        public ChangeFrame() {
            this.setTitle("修改读者信息");
            this.setBounds(300, 200, 500, 350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8, 2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("读者编号：");
            label[1] = new JLabel("读者姓名：");

            label[3] = new JLabel("用户密码：");
            label[4] = new JLabel("已借图书编号：");

            idText = new JTextField(10);
            nameText = new JTextField(10);

            passwordText = new JTextField(10);
            lendText = new JTextField(10);

            panelRight = new JPanel[5];
            panelLeft = new JPanel[5];
            for (int i = 0; i < panelRight.length; i++) {
                panelRight[i] = new JPanel();
                panelRight[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            }

            for (int i = 0; i < panelLeft.length; i++) {
                panelLeft[i] = new JPanel();
                panelLeft[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            }

            for (int i = 0; i < panelLeft.length; i++) {
                for (int j = i; j < label.length; j++) {
                    panelLeft[i].add(label[j]);
                }
            }

            panelRight[0].add(idText);
            panelRight[1].add(nameText);

            panelRight[3].add(passwordText);
            panelRight[4].add(lendText);

            panel.add(panelLeft[0]);
            panel.add(panelRight[0]);
            panel.add(panelLeft[1]);
            panel.add(panelRight[1]);
            panel.add(panelLeft[2]);
            panel.add(panelRight[2]);
            panel.add(panelLeft[3]);
            panel.add(panelRight[3]);
            panel.add(panelLeft[4]);
            panel.add(panelRight[4]);
            this.add(panelSouth, BorderLayout.SOUTH);
            this.add(panel);
            MyEvent();
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }


        public void refresh() {
            Object[][] data = readerDaoImpl.getReaderInfo();
            tableModel.setDataVector(data, header);
        }

        public void MyEvent() {
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int readerId = parseInt(idText.getText());
                    String readerName = nameText.getText();

                    String readerPassword = passwordText.getText();
                    int readerLend = parseInt(lendText.getText());
                    Reader reader = new Reader(readerId, readerName, readerPassword, readerLend);

                    int i = readerDao.changeReader(reader);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "修改成功！");
                        refresh();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败！");
                    }
                }

            });
        }
    }


    /*查找读者信息*/
    class FindFrame extends JFrame {


        public FindFrame() {
            this.setTitle("查询读者信息");
            this.setBounds(300, 200, 500, 350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8, 2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("读者编号：");
            label[1] = new JLabel("读者姓名：");

            label[3] = new JLabel("用户密码：");
            label[4] = new JLabel("已借图书编号：");

            idText = new JTextField(10);
            nameText = new JTextField(10);

            passwordText = new JTextField(10);
            lendText = new JTextField(10);

            panelRight = new JPanel[5];
            panelLeft = new JPanel[5];
            for (int i = 0; i < panelRight.length; i++) {
                panelRight[i] = new JPanel();
                panelRight[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            }

            for (int i = 0; i < panelLeft.length; i++) {
                panelLeft[i] = new JPanel();
                panelLeft[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            }

            for (int i = 0; i < panelLeft.length; i++) {
                for (int j = i; j < label.length; j++) {
                    panelLeft[i].add(label[j]);
                }
            }

            panelRight[0].add(idText);
            panelRight[1].add(nameText);

            panelRight[3].add(passwordText);
            panelRight[4].add(lendText);

            panel.add(panelLeft[0]);
            panel.add(panelRight[0]);
            panel.add(panelLeft[1]);
            panel.add(panelRight[1]);
            panel.add(panelLeft[2]);
            panel.add(panelRight[2]);
            panel.add(panelLeft[3]);
            panel.add(panelRight[3]);
            panel.add(panelLeft[4]);
            panel.add(panelRight[4]);
            this.add(panelSouth, BorderLayout.SOUTH);
            this.add(panel);
            MyEvent();
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }


        public void refresh() {
            Object[][] data = readerDaoImpl.getReaderInfo();
            tableModel.setDataVector(data, header);
        }

        public void MyEvent() {
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int readerId = parseInt(idText.getText());
                    String readerName = nameText.getText();

                    String readerPassword = passwordText.getText();
                    int readerLend = parseInt(lendText.getText());
                    Reader reader = new Reader(readerId, readerName, readerPassword, readerLend);

                    Reader i = readerDao.findReader(readerId);
//                    if (i > 0) {
//                        JOptionPane.showMessageDialog(null, "修改成功！");
//                        refresh();
//                        dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "修改失败！");
//                    }
                }

            });
        }
    }


    public void MyEvent() {
        // 增加
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new AdminUserFrame.AddFrame();
            }
        });

        // 删除
        del.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new AdminUserFrame.DelFrame();
            }
        });

        // 修改
        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                new AdminUserFrame.ChangeFrame();
            }
        });

        // 查找
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 增加一行空白区域
                new AdminUserFrame.FindFrame();
            }
        });

        // 退出
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        new AdminUserFrame("读者信息管理");
    }
}
