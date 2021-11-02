package manage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainUI extends JFrame implements ActionListener {

    //定义组件
    JButton jb1,jb2,jb3=null;
    JPanel jp1,jp2, jp3 =null;
    JTextField jtf=null;
    JLabel jlb1,jlb2,jlb3=null;
    JPasswordField jpf=null;

    //数据库相关变量
    static String driverName = "com.mysql.cj.jdbc.Driver";
    static String dbURL="jdbc:mysql://localhost:3306/xtfx?&useSSL=false&serverTimezone=Asia/Shanghai";
    static String userName = "root";
    static String userPwd = "root";
    static String current_user = null;

    public static void main(String[] args) {

        MainUI mUI=new MainUI();

        //测试数据库连接是否正常
        try {
            Class.forName(driverName);
            Connection con= DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("数据库连接正常");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.print("连接失败");

        }
    }

    //登录主界面
    public MainUI()
    {
        //创建组件
        jb1=new JButton("登录");
        jb2=new JButton("重置");
        jb3=new JButton("退出");

        //设置监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);


        jp1=new JPanel();
        jp2=new JPanel();
        jp3 =new JPanel();

        jlb1=new JLabel("用户名：");
        jlb2=new JLabel("密   码：");

        jtf=new JTextField(10);
        jpf=new JPasswordField(10);

        //加入到JPanel中
        jp1.add(jlb1);
        jp1.add(jtf);

        jp2.add(jlb2);
        jp2.add(jpf);

        //添加按钮
        jp3.add(jb1);
        jp3.add(jb2);
        jp3.add(jb3);

        //加入JFrame中
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setLayout(new GridLayout(4,1));
        this.setTitle("学生管理系统");
        this.setSize(300,200);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

    }

    //事件判断
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "登录":login();break;
            case "重置":clear();break;
            case "退出":exit();break;
        }

    }


    //登录判断方法
    public void login()
    {

        String rightPwd = null;
        try {
            Class.forName(driverName);
            Connection con= DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接数据库成功");
            Statement state=con.createStatement();

            String checkPwd="select u_pwd from user where u_id = "+jtf.getText()+"";
            ResultSet rs = state.executeQuery(checkPwd);
            while (rs.next()) {
                rightPwd = rs.getString("u_pwd");
                System.out.println("用户的正确密码为："+rightPwd);
            }
            con.close();
            state.close();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.print("连接失败");

        }

        System.out.println("用户输入的密码是"+rightPwd.equals(jpf.getText())+"的");
        if(rightPwd.equals(jpf.getText()))
        {
            JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);
            current_user = jtf.getText();
            System.out.println("当前用户为"+MainUI.current_user);
            dispose();
            clear();
            UserUI ui = new UserUI();


        }else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);
        }else if(jtf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);
        }else if(jpf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);
        }else
        {
            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);
            //清空输入框
            clear();
        }
    }
    
    //清空文本框和密码框
    public  void clear()
    {
        jtf.setText("");
        jpf.setText("");
    }

    //退出关闭窗口
    public void exit()
    {
            dispose();
    }

}
