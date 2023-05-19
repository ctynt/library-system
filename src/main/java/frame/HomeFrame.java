package frame;

import javax.swing.*;
import java.awt.*;

/**
 * @version 1.0.0
 * @Author zhy
 * @Date 2023/5/19 8:48
 * @Description
 */
public class HomeFrame extends JFrame{
    public void init(){
        JPanel menuJpanel = new JPanel();
        menuJpanel.setBackground(Color.green);
        JPanel leftJpanel = new JPanel();
        leftJpanel.setBackground(Color.yellow);
        JPanel contentJPanel = new JPanel();
        contentJPanel.setBackground(Color.gray);
        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setBackground(Color.cyan);
        JPanel statusJPanel = new JPanel();
        statusJPanel.setBackground(Color.BLUE);
        //定义布局并设置布局
        GridBagLayout layout=new GridBagLayout();
        setLayout(layout);
        // 创建GridBagConstraints对象
        GridBagConstraints s=new GridBagConstraints();
        s.fill=GridBagConstraints.BOTH;

        s.gridwidth=0;
        s.weightx=10;
        s.weighty=1;
        layout.setConstraints(menuJpanel,s);

        s.gridwidth=1;
        s.weightx=2;
        s.weighty=10;
        layout.setConstraints(leftJpanel,s);

        s.gridwidth=0;
        s.weightx=10;
        s.weighty=30;
        layout.setConstraints(contentJPanel,s);

        s.gridwidth=0;
        s.weightx=10;
        s.weighty=1;
        layout.setConstraints(bottomJPanel,s);

        s.gridwidth=0;
        s.weightx=10;
        s.weighty=1;
        layout.setConstraints(statusJPanel,s);

        add(menuJpanel);
        add(leftJpanel);
        add(contentJPanel);
        add(bottomJPanel);
        add(statusJPanel);

    }
    public HomeFrame() throws HeadlessException {
        init();
        setTitle("draw窗体面板展示");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HomeFrame();
    }

}
