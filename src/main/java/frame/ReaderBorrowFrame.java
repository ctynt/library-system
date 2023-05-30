package frame;
import dao.impl.BookDaoImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/29 8:09
 * @Description
 */
public class ReaderBorrowFrame extends JFrame{
    JTable table;
    /*增加信息的面板*/
    JPanel panelUP;

    BookDaoImpl bookDaoImpl = new BookDaoImpl();
    public ReaderBorrowFrame(String title) {
        this.setBounds(300, 200, 850, 450);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        panelUP = new JPanel();
        panelUP.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new ReaderMainFrame().setVisible(true);
            }
        });

        this.add(panelUP, BorderLayout.NORTH);
        //子窗口销毁
        this.dispose();
        //父窗口变可见
        setVisible(true);
        //窗口大小不可变
        setResizable(false);
    }
    public static void main(String[] args){
        new ReaderBorrowFrame("图书借阅服务服务");
    }
}
