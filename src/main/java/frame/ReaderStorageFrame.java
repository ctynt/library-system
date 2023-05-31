package frame;

import dao.impl.BookDaoImpl;

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
 * @Date 2023/5/29 8:08
 * @Description 读者--图书借阅系统--图书信息浏览
 */
public class ReaderStorageFrame extends JFrame {

    DefaultTableModel tableModel;
    JTable table;
    JPanel panelUP;
    Object[] header = {"图书编号", "书名", "作者", "类别", "状态"};
    Object[][] data;

    BookDaoImpl bookDaoImpl = new BookDaoImpl();

    public ReaderStorageFrame(String title) {
        super("图书借阅系统--图书信息浏览");
        this.setBounds(300, 200, 850, 510);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setLayout(new BorderLayout());

        panelUP = new JPanel();
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));
        Vector rowData = null;
        Vector columnNames = null;

        data = bookDaoImpl.getBookInfo();
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
        //垂直滚动条
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new ReaderMainFrame().setVisible(true);
            }
        });
        System.out.println(h);
        JScrollPane jsp = new JScrollPane(table, v, h);
        jsp.setBounds(0, 0, 850, 440);
        this.add(jsp);
        //新建表格
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
        new ReaderStorageFrame("图书借阅系统--图书信息浏览");
    }
}
