package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author ctynt
 * @Date 2023/5/22
 * @Description
 */

public class AdminMainFrame extends JFrame {
    JPanel panel;
    JButton storage,lendInfo;
    public AdminMainFrame(){
        this.setTitle("管理员");
        this.setBounds(400,300,200,200);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        storage = new JButton("图书入库管理");
        lendInfo = new JButton("借阅信息管理");

        this.add(storage);
        this.add(lendInfo);

        MyEvent();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void MyEvent(){
        // 图书入库管理
        storage.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
//                new TableFrame(storage.getText()).show();
            }

        });

        // 用户借阅信息管理
        lendInfo.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
//                new TableFrame(lendInfo.getText()).show();
            }

        });
    }

    public static void main(String[] args){
        new AdminMainFrame();
    }
}

