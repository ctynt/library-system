//package frame;
//
//import domain.Admin;
//import domain.Reader;
//
//import java.awt.Frame;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
///**
// * 管理员功能选项
// * @author hwt1070359898
// *
// */
//public class AdminPage extends Frame implements ActionListener{
//    //存储图书编号的文件
//    private static String fileName="D:/练习/Java/gaoqishipin/Copy of LibraryManagement/BooksNum.txt";
//
//    //定义各个控件
//    private JButton btLookSelf=new JButton("查看个人资料");
//    private JButton btModify=new JButton("修改个人资料");
//    private JButton btShowBook=new JButton("查看书本信息");
//    private JButton btModifyBook=new JButton("修改书本信息");
//    private JButton btDeleteBook=new JButton("删除书本信息");
//    private JButton btAddBook=new JButton("增添书本信息");
//    private JButton btExit=new JButton("退出系统");
//
//    public AdminPage(){
//
//        super("管理员："+ Admin.adminName);//串口标题
//        this.setLayout(null);//设置为手工设置各个组件的位置和大小
//
//        btLookSelf.setBounds(new Rectangle(50,80,300,50));//修改个人资料
//        btModify.setBounds(new Rectangle(50,150,300,50));//修改个人资料
//        btShowBook.setBounds(new Rectangle(50,220,300,50));//查看书本信息
//        btModifyBook.setBounds(new Rectangle(50,290,300,50));//修改书本信息
//        btDeleteBook.setBounds(new Rectangle(50,360,300,50));//删除书本信息
//        btAddBook.setBounds(new Rectangle(50,430,300,50));//增添书本信息
//        btExit.setBounds(new Rectangle(50,500,300,50));//退出系统
//
//        btLookSelf.addActionListener(this);
//        btModify.addActionListener(this);
//        btShowBook.addActionListener(this);
//        btModifyBook.addActionListener(this);
//        btDeleteBook.addActionListener(this);
//        btAddBook.addActionListener(this);
//        btExit.addActionListener(this);
//
//        this.add(btLookSelf);
//        this.add(btModify);
//        this.add(btShowBook);
//        this.add(btModifyBook);
//        this.add(btDeleteBook);
//        this.add(btAddBook);
//        this.add(btExit);
//
//        this.setSize(400,570);
//
//        GUIUtil.toCenter(this);//使窗口居中
//        this.setVisible(true);//可视化
//        this.setResizable(false);//关闭放大窗口
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置错误关闭操作
//
//        //用于关闭窗体事件
//        this.addWindowListener(new WindowAdapter(){
//            @Override
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        });
//    }
//
//    private void setDefaultCloseOperation(int exitOnClose) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        // TODO Auto-generated method stub
//        if(e.getSource()==btLookSelf) {//查看个人资料
//            String message="您的详细资料为:\n";
//
//            message+="读者编号："+ Reader.readerId+"\n";
//            message+="姓名："+Reader.readerLimit+"\n";
//            message+="已借图书编号："+Reader.readerLend+"\n";
//
//
//            JOptionPane.showMessageDialog(this,message);
//        }else if(e.getSource()==btModify) {//修改个人资料
//            this.dispose();
//            new ModifyDialog("固定学号："+Reader.readerId);
//        }else if(e.getSource()==btShowBook) {//查看书本信息
//            this.dispose();
//            new ShowBook();
//        }else if(e.getSource()==btModifyBook) {//修改书本信息
//            this.dispose();
//            String ModifyBookNum = JOptionPane.showInputDialog("请输入您要修改的书本编号：");
//            if(BooksFileOpe.findBook(ModifyBookNum)){
//                new ModifyBook(ModifyBookNum);
//                return;
//            }
//            JOptionPane.showMessageDialog(this,"抱歉，系统中没有您要修改的书本信息！");
//            new AdminPage();
//        }
//        else if(e.getSource()==btDeleteBook) {//删除书本信息
//            this.dispose();
//            String deleteBook = JOptionPane.showInputDialog("请输入您要删除的书本编号：");
//            if(BooksFileOpe.findBook(deleteBook)){
////        		BooksFileOpe.getInfoByAccount(deleteBook);
//                BooksFileOpe.deleteBookNum(deleteBook);
//                JOptionPane.showMessageDialog(this, "删除成功！");
//                new AdminPage();
//                return;
//            }
//            JOptionPane.showMessageDialog(this,"抱歉，系统中没有您要删除的书本信息！");
//            new AdminPage();
//        }else if(e.getSource()==btAddBook) {//增添书本信息
//            this.dispose();
//            new AddBook();
//        }else if(e.getSource()==btExit){//退出系统
//            JOptionPane.showMessageDialog(this,"谢谢光临，欢迎下次继续使用本系统！");
//            System.exit(0);
//        }
//    }
//}