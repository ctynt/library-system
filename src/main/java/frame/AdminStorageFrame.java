package frame;

/**
 * @Author ctynt
 * @Date 2023/5/22
 * @Description
 */

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import storage.PutinStorage;

public class AdminStorageFrame extends JFrame {

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
    JTextField idText, titleText, authorText;
    CheckboxGroup cg;
    ButtonGroup bg;
    JPanel panel, panelSouth;
    JButton button;
    String[] str = null;
    JPanel[] panelLeft, panelRight;

    private String database = "library-system";
    private String tablesName;

    public AdminStorageFrame(String title) {
        this.setBounds(300, 200, 600, 450);
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

        if (title.equals("图书信息管理")) {
            rowData = PutinStorage.getRows("books");
            columnNames = PutinStorage.getHead("books");
        } else {
            rowData = PutinStorage.getRows("lendInfo");
            columnNames = PutinStorage.getHead("lendInfo");
        }

        // 新建表格
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(22);

        JScrollPane s = new JScrollPane(table);

        this.add(panelUP, BorderLayout.NORTH);
        this.add(s);

        MyEvent();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 添加单元格的内部类
    class AddFrame extends JFrame {


        public AddFrame() {
            this.setBounds(300, 200, 500, 350);
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
            titleText = new JTextField(10);
            authorText = new JTextField(10);
            String[] types = {"计算机", "英语", "电气", "机械", "材料"};

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
            panelRight[1].add(titleText);
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
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void MyEvent() {
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub

                    String str1 = idText.getText();
                    String str2 = titleText.getText();
                    String str3 = authorText.getText();
                    String str4 = typeBox.getSelectedItem().toString();
                    String str5 = null;
                    String first = null, second = null;
                    String cam;
                    if (stateRadio1.isSelected()) {
                        str5 = stateRadio1.getText();
                    } else if (stateRadio2.isSelected()) {
                        str5 = stateRadio2.getText();
                    }

                    if (first == null && second != null) {
                        cam = second;
                    } else if (first != null && second == null) {
                        cam = first;
                    } else {
                        cam = first + "," + second;
                    }

                    String[] str = {str1, str2, str3, str4,
                            str5, cam};

                    vector = new Vector();
                    vector.add(str1);
                    vector.add(str2);
                    vector.add(str3);
                    vector.add(str4);
                    vector.add(str5);
                    vector.add(cam);


                    int rowNum = table.getSelectedRow();

                    if (rowNum == -1) {
                        String aa1 = str1.substring(0, 1);
                        String aa = str1.substring(1, str1.length());
                        long bb = Long.parseLong(aa) + 1;

                        String cc = aa1 + String.valueOf(bb);

                        tableModel.addRow(vector);

                        //加入表格后清除源数据
                        idText.setText(cc);
                        titleText.setText("");
                        authorText.setText("");
                    }

                    if (rowNum != -1) {
                        String aa = table.getValueAt(rowNum, 0).toString();
                        String aa1 = aa.substring(0, 1);
                        tableModel.insertRow(rowNum + 1, vector);

                        for (int i = rowNum + 2; i < table.getRowCount(); i++) {
                            if (table.getValueAt(i, 0).toString().startsWith(aa1)) {
                                String ee = table.getValueAt(i, 0).toString();
                                String ee1 = aa1 + String.valueOf(Long.parseLong(ee.substring(1, ee.length())) + 1);
                                table.setValueAt(ee1, i, 0);
                            }
                        }
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
        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PutinStorage().saveData(table);
            }

        });

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

    public static void main(String[] args) {
        new AdminStorageFrame("图书信息管理");
    }
}