package frame;

import dao.BorrowDao;
import dao.impl.BorrowDaoImpl;
import domain.Borrow;
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

public class AdminBorrowFrame extends JFrame {

    BorrowDao borrowDao = new BorrowDaoImpl();
    DefaultTableModel tableModel;
    Vector vector;
    JMenuBar menuBar;
    BorrowDaoImpl borrowDaoImpl = new BorrowDaoImpl();
    JButton add,del,exit,find,change;
    JTable table;
    /*增加信息的面板*/
    JPanel panelUP,panelDown;


    // 内部类中的变量
    JLabel[] label;
    JTextField borrowIdText,bookIdText,bookNameText,readerIdText,readerNameText;
    CheckboxGroup cg;
    ButtonGroup bg;

    JPanel panel,panelSouth;
    JButton button;
    String[] str=null;
    JPanel[] panelLeft,panelRight;

    Object[] header = {"借阅编号","图书编号","图书名称","读者编号","读者姓名"};

    Object[][] data;

    public AdminBorrowFrame(String title){
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

        data = borrowDaoImpl.getBorrowInfo();
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
        jsp.setBounds(0, 36, 590, 400);
        this.add(jsp);
        // 新建表格
        tableModel = new DefaultTableModel(rowData,columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(22);

        JScrollPane s = new JScrollPane(table);

        this.add(panelUP,BorderLayout.NORTH);
        this.add(s);

        MyEvent();
        //子窗口销毁
        this.dispose();
        //父窗口变可见
        setVisible(true);

        this.setLocationRelativeTo(null);//窗口在屏幕中间显示
    }

    /*增加借阅信息*/
    class AddFrame extends JFrame{


        public AddFrame(){
            this.setTitle("添加借阅信息");
            this.setLocationRelativeTo(null);
            this.setBounds(300,200,500,350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8,2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("借阅编号：");
            label[1] = new JLabel("图书编号：");
            label[2] = new JLabel("图书名称：");
            label[3] = new JLabel("读者编号：");
            label[4] = new JLabel("读者姓名：");

            borrowIdText = new JTextField(10);
            bookIdText = new JTextField(10);
            bookNameText = new JTextField(10);
            readerIdText = new JTextField(10);
            readerNameText = new JTextField(10);


            panelRight = new JPanel[5];
            panelLeft = new JPanel[5];
            for(int i = 0; i < panelRight.length; i++){
                panelRight[i] = new JPanel();
                panelRight[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            }

            for(int i = 0; i < panelLeft.length; i++){
                panelLeft[i] = new JPanel();
                panelLeft[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            }

            for(int i = 0; i < panelLeft.length; i++) {
                for(int j = i; j < label.length; j++) {
                    panelLeft[i].add(label[j]);
                }
            }

            panelRight[0].add(borrowIdText);
            panelRight[1].add(bookIdText);
            panelRight[2].add(bookNameText);
            panelRight[3].add(readerIdText);
            panelRight[4].add(readerNameText);

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
            this.add(panelSouth,BorderLayout.SOUTH);
            this.add(panel);
            MyEvent();
//			this.setVisible(true);
//			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }
        public void refresh() {
            Object[][] data = borrowDaoImpl.getBorrowInfo();
            tableModel.setDataVector(data, header);
        }
        public void MyEvent(){
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int borrowId = parseInt(borrowIdText.getText());
                    int bookId = parseInt(bookIdText.getText());
                    String bookName = bookNameText.getText();
                    int readerId = parseInt(readerIdText.getText());
                    String readerName = readerNameText.getText();
                    Borrow borrow = new Borrow(borrowId,bookId,bookName,readerId,readerName);
                    int i = borrowDao.addBorrow(borrow);
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

    class DelFrame extends JFrame{


        public DelFrame(){
            this.setTitle("删除借阅信息");
            this.setLocationRelativeTo(null);
            this.setBounds(300,200,500,350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8,2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("借阅编号：");
            label[1] = new JLabel("图书编号：");
            label[2] = new JLabel("图书名称：");
            label[3] = new JLabel("读者编号：");
            label[4] = new JLabel("读者姓名：");

            borrowIdText = new JTextField(10);
            bookIdText = new JTextField(10);
            bookNameText = new JTextField(10);
            readerIdText = new JTextField(10);
            readerNameText = new JTextField(10);


            panelRight = new JPanel[5];
            panelLeft = new JPanel[5];
            for(int i = 0; i < panelRight.length; i++){
                panelRight[i] = new JPanel();
                panelRight[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            }

            for(int i = 0; i < panelLeft.length; i++){
                panelLeft[i] = new JPanel();
                panelLeft[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            }

            for(int i = 0; i < panelLeft.length; i++) {
                for(int j = i; j < label.length; j++) {
                    panelLeft[i].add(label[j]);
                }
            }

            panelRight[0].add(borrowIdText);
            panelRight[1].add(bookIdText);
            panelRight[2].add(bookNameText);
            panelRight[3].add(readerIdText);
            panelRight[4].add(readerNameText);

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
            this.add(panelSouth,BorderLayout.SOUTH);
            this.add(panel);
            MyEvent();
//			this.setVisible(true);
//			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }
        public void refresh() {
            Object[][] data = borrowDaoImpl.getBorrowInfo();
            tableModel.setDataVector(data, header);
        }
        public void MyEvent(){
            button.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int borrowId = parseInt(borrowIdText.getText());
                    int bookId = parseInt(bookIdText.getText());
                    String bookName = bookNameText.getText();
                    int readerId = parseInt(readerIdText.getText());
                    String readerName = readerNameText.getText();
                    Borrow borrow = new Borrow(borrowId,bookId,bookName,readerId,readerName);
                    int i = borrowDao.delBorrow(borrowId);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        refresh();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败！");
                    }
                }
            });
        }


    }

    class ChangeFrame extends JFrame{


        public ChangeFrame(){
            this.setTitle("修改借阅信息");
            this.setLocationRelativeTo(null);
            this.setBounds(300,200,500,350);
            this.setLocationRelativeTo(null);
            panel = new JPanel();
            panel.setLayout(new GridLayout(8,2));
            panelSouth = new JPanel();
            panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
            button = new JButton("OK");
            panelSouth.add(button);
            label = new JLabel[5];
            label[0] = new JLabel("借阅编号：");
            label[1] = new JLabel("图书编号：");
            label[2] = new JLabel("图书名称：");
            label[3] = new JLabel("读者编号：");
            label[4] = new JLabel("读者姓名：");

            borrowIdText = new JTextField(10);
            bookIdText = new JTextField(10);
            bookNameText = new JTextField(10);
            readerIdText = new JTextField(10);
            readerNameText = new JTextField(10);


            panelRight = new JPanel[5];
            panelLeft = new JPanel[5];
            for(int i = 0; i < panelRight.length; i++){
                panelRight[i] = new JPanel();
                panelRight[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            }

            for(int i = 0; i < panelLeft.length; i++){
                panelLeft[i] = new JPanel();
                panelLeft[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            }

            for(int i = 0; i < panelLeft.length; i++) {
                for(int j = i; j < label.length; j++) {
                    panelLeft[i].add(label[j]);
                }
            }

            panelRight[0].add(borrowIdText);
            panelRight[1].add(bookIdText);
            panelRight[2].add(bookNameText);
            panelRight[3].add(readerIdText);
            panelRight[4].add(readerNameText);

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
            this.add(panelSouth,BorderLayout.SOUTH);
            this.add(panel);
            MyEvent();
//			this.setVisible(true);
//			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();//子窗口销毁
            setVisible(true);//父窗口变可见
        }
        public void refresh() {
            Object[][] data = borrowDaoImpl.getBorrowInfo();
            tableModel.setDataVector(data, header);
        }
        public void MyEvent(){
            button.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int borrowId = parseInt(borrowIdText.getText());
                    int bookId = parseInt(bookIdText.getText());
                    String bookName = bookNameText.getText();
                    int readerId = parseInt(readerIdText.getText());
                    String readerName = readerNameText.getText();
                    Borrow borrow = new Borrow(borrowId,bookId,bookName,readerId,readerName);
                    int i = borrowDao.changeBorrow(borrow);
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


    public void MyEvent(){

        // 增加
        add.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new AddFrame();

                int rowNum = table.getSelectedRow();

                if(rowNum != -1){
                    String aa = table.getValueAt(rowNum, 0).toString();
                    String aa1 = aa.substring(0, 1);
                    String aa2 = aa.substring(1, aa.length());

                    long bb = Long.parseLong(aa2) + 1;

                    String cc = aa1 + String.valueOf(bb);
                    borrowIdText.setText(cc);
                }
            }

        });

        // 删除
        del.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new DelFrame();

                int rowNum = table.getSelectedRow();

                if(rowNum != -1){
                    String aa = table.getValueAt(rowNum, 0).toString();
                    String aa1 = aa.substring(0, 1);
                    String aa2 = aa.substring(1, aa.length());

                    long bb = Long.parseLong(aa2) + 1;

                    String cc = aa1 + String.valueOf(bb);
                    borrowIdText.setText(cc);
                }
            }

        });

        // 修改
        change.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new ChangeFrame();

                int rowNum = table.getSelectedRow();

                if(rowNum != -1){
                    String aa = table.getValueAt(rowNum, 0).toString();
                    String aa1 = aa.substring(0, 1);
                    String aa2 = aa.substring(1, aa.length());

                    long bb = Long.parseLong(aa2) + 1;

                    String cc = aa1 + String.valueOf(bb);
                    borrowIdText.setText(cc);
                }
            }

        });

        // 查找
        find.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
            }

        });

        // 退出
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }

        });
    }


    public static void main(String[] args){
        new AdminBorrowFrame("借阅信息管理");
    }
}

