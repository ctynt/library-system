package frame;

/**
 * @Author ctynt
 * @Date 2023/5/22
 * @Description
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.Book;

import static java.lang.Integer.parseInt;



public class AdminStorageFrame extends JFrame {



    BookDao bookDao = new BookDaoImpl();

    DefaultTableModel tableModel;
    Vector vector;
    JMenuBar menuBar;
    JButton add, del, exit, find, change;
    JTable table;
    /**
     * 增加信息的面板
     */
    JPanel panelUP, panelDown;
    /* *
     *内部类中的变量*/
    JLabel[] label;
    //    JLabel idLabel,titleLabel,authorLabel,typeLable,stateLabel;
    JComboBox typeBox;
    JRadioButton stateRadio1, stateRadio2;
    JTextField idText, nameText, authorText;
    CheckboxGroup cg;
    ButtonGroup bg;
    JPanel panel, panelSouth;
    JButton button;
    String[] str = null;
    JPanel[] panelLeft, panelRight;

    Object[] header = {"图书编号","书名","作者","类型","状态"};

    Object[][] data;

    BookDaoImpl bookDaoImpl = new BookDaoImpl();

    public AdminStorageFrame(String title) {


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

        data = bookDaoImpl.getBookInfo();
        tableModel = new DefaultTableModel(data,header);
        table = new JTable(tableModel);
//        // 创建表格
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
//         垂直滚动条
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new AdminMainFrame().setVisible(true);
            }
        });
        System.out.println(h);
        JScrollPane jsp = new JScrollPane(table, v, h);
        jsp.setBounds(0, 36, 850, 400);
        this.add(jsp);
//         新建表格
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(22);

        JScrollPane s = new JScrollPane(table);

        this.add(panelUP, BorderLayout.NORTH);
        this.add(s);

        MyEvent();
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();//子窗口销毁
        setVisible(true);//父窗口变可见
    }

    // 添加单元格的内部类
    class AddFrame extends JFrame {


        public AddFrame() {
            this.setTitle("添加图书");
            this.setLocationRelativeTo(null);
            this.setBounds(300, 200, 500, 350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8, 2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("图书编号：");
            label[1] = new JLabel("图书名称：");
            label[2] = new JLabel("图书作者：");
            label[3] = new JLabel("图书类型：");
            label[4] = new JLabel("借阅状态：");

            idText = new JTextField(10);
            nameText = new JTextField(10);
            authorText = new JTextField(10);
            String[] types = {"外国文学", "哲学", "历史", "中国文学"};

            typeBox = new JComboBox(types);
            cg = new CheckboxGroup();
            stateRadio1 = new JRadioButton("借阅");
            stateRadio2 = new JRadioButton("在馆");
            bg = new ButtonGroup();
            bg.add(stateRadio1);
            bg.add(stateRadio2);

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
            panelRight[2].add(authorText);
            panelRight[3].add(typeBox);
            panelRight[4].add(stateRadio1);
            panelRight[4].add(stateRadio2);

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




        public void MyEvent() {
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
//
//                    String str1 = idText.getText();
//                    String str2 = nameText.getText();
//                    String str3 = authorText.getText();
//                    String str4 = typeBox.getSelectedItem().toString();
//                    String str5 = null;
//                    String first = null, second = null;
//                    String cam;
//                    if (stateRadio1.isSelected()) {
//                        str5 = stateRadio1.getText();
//                    } else if (stateRadio2.isSelected()) {
//                        str5 = stateRadio2.getText();
//                    }
//
//                    if (first == null && second != null) {
//                        cam = second;
//                    } else if (first != null && second == null) {
//                        cam = first;
//                    } else {
//                        cam = first + "," + second;
//                    }
//
//                    String[] str = {str1, str2, str3, str4,
//                            str5, cam};
//
//                    vector = new Vector();
//                    vector.add(str1);
//                    vector.add(str2);
//                    vector.add(str3);
//                    vector.add(str4);
//                    vector.add(str5);
//                    vector.add(cam);
//
//
//                    int rowNum = table.getSelectedRow();
//
//                    if (rowNum == -1) {
//                        String aa1 = str1.substring(0, 1);
//                        String aa = str1.substring(1, str1.length());
//                        long bb = Long.parseLong(aa) + 1;
//
//                        String cc = aa1 + String.valueOf(bb);
//
//                        tableModel.addRow(vector);
//
//                        //加入表格后清除源数据
//                        idText.setText(cc);
//                        nameText.setText("");
//                        authorText.setText("");
//                    }
//
//                    if (rowNum != -1) {
//                        String aa = table.getValueAt(rowNum, 0).toString();
//                        String aa1 = aa.substring(0, 1);
//                        tableModel.insertRow(rowNum + 1, vector);
//
//                        for (int i = rowNum + 2; i < table.getRowCount(); i++) {
//                            if (table.getValueAt(i, 0).toString().startsWith(aa1)) {
//                                String ee = table.getValueAt(i, 0).toString();
//                                String ee1 = aa1 + String.valueOf(Long.parseLong(ee.substring(1, ee.length())) + 1);
//                                table.setValueAt(ee1, i, 0);
//                            }
//                        }
//                    }


                    int bookId = parseInt(idText.getText());
                    String bookName = nameText.getText();
                    String author = authorText.getText();
                    String category = (String) typeBox.getSelectedItem();
                    String state =null;
                    if (stateRadio1.isSelected()) {
                        state = stateRadio1.getText();
                    } else if (stateRadio2.isSelected()) {
                        state= stateRadio2.getText();
                    }

                    Book book  = new Book(bookId,bookName,author, category, state);

                    int i = bookDao.addBook(book);
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

    public void MyEvent() {
        // 增加
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // 增加一行空白区域
                tableModel.addRow(new Vector());
                new AddFrame();

                int rowNum = table.getSelectedRow();

                if (rowNum != -1) {
                    String aa = table.getValueAt(rowNum, 0).toString();
                    String aa1 = aa.substring(0, 1);
                    String aa2 = aa.substring(1, aa.length());

                    long bb = Long.parseLong(aa2) + 1;

                    String cc = aa1 + String.valueOf(bb);
                    idText.setText(cc);
                }




            }

        });

        // 删除
        del.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                // 按照从下到上逐行删除（需添加鼠标事件）
                int rowcount = table.getSelectedRow();
//				System.out.println(rowcount);
                if (rowcount >= 0) {
                    tableModel.removeRow(rowcount);
                    String aa = table.getValueAt(rowcount, 0).toString().substring(0, 1);
                    for (int i = rowcount; i < table.getRowCount(); i++) {
                        if (table.getValueAt(i, 0).toString().startsWith(aa)) {
                            String ee = table.getValueAt(i, 0).toString();
                            String ee1 = aa + String.valueOf(Long.parseLong(ee.substring(1, ee.length())) - 1);
                            table.setValueAt(ee1, i, 0);
                        }
                    }
                }
//                table.revalidate();
            }

        });

        // 修改


        // 查找
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
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

    public void refresh() {
        data = bookDaoImpl.getBookInfo();
        tableModel.setDataVector(data, header);

    }

    public static void main(String[] args) {
        new AdminStorageFrame("图书信息管理");
    }
}