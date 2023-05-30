package frame;

import dao.impl.BorrowDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/30 14:19
 * @Description
 */
public class BookStorageFrame extends JFrame {
    DefaultTableModel tableModel;
    //    Vector vector;
    JTable table;
    /*增加信息的面板*/
    JPanel panelUP;

    Object[] header = {"借阅编号","图书编号","图书名称","读者编号","读者姓名"};

    Object[][] data;

    BorrowDaoImpl borrowDaoImpl = new BorrowDaoImpl();
    public BookStorageFrame(String title) {
        super("图书借阅服务--借阅信息");
        this.setBounds(300, 200, 850, 450);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setLayout(new BorderLayout());

        panelUP = new JPanel();
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));
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
                new ReaderBorrowFrame("");
                setVisible(true);
            }
        });
        System.out.println(h);
        JScrollPane jsp = new JScrollPane(table, v, h);
        jsp.setBounds(0, 0, 850, 400);
        this.add(jsp);
//         新建表格
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);
        table.setRowHeight(22);

        JScrollPane s = new JScrollPane(table);

        this.add(panelUP, BorderLayout.NORTH);
        this.add(s);
        //子窗口销毁
        this.dispose();
        //父窗口变可见
        setVisible(true);

        //窗口大小不可变
        setResizable(false);
    }

    public static void main(String[] args) {
        new BookStorageFrame("图书借阅服务--借阅信息");
    }

}
