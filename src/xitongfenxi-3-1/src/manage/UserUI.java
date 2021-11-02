package manage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserUI extends JFrame implements ActionListener
{

    //定义组件
    JButton jb1=new JButton();
    JButton jb2=new JButton();
    JButton jb3=new JButton();
    JPanel jp1,jp2,jp3=null;

    public  UserUI()
    {
        String[] permission = new String[3];
        int i = 0;
        String current_role = null;
        try {
            Class.forName(MainUI.driverName);
            Connection con= DriverManager.getConnection(MainUI.dbURL, MainUI.userName, MainUI.userPwd);
            System.out.println("连接数据库成功");
            Statement state=con.createStatement();

            System.out.println(MainUI.current_user);
            String findRole ="select role.role_id from user,user_role,role where user.u_id="+MainUI.current_user+" and user_role.u_id = user.u_id and user_role.role_id = role.role_id";
            ResultSet rs1 = state.executeQuery(findRole);
            while (rs1.next()) {
                String role_id = rs1.getString("role_id");
                System.out.println("当前用户的角色为"+role_id);
                current_role = role_id;
            }

            String checkPermission="select p_name from permission,role,role_permission where role.role_id = "+current_role+" and role.role_id = role_permission.role_id and role_permission.p_id = permission.p_id ";
            ResultSet rs = state.executeQuery(checkPermission);

            while (rs.next()) {
                String p_name = rs.getString("p_name");
                System.out.println(p_name);
                permission[i] = p_name;
                i++;
            }

            con.close();
            state.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.print("连接失败");

        }


        //创建组件
        jb1=new JButton(permission[0]);
        jb1.setForeground(Color.BLUE);
        jb2=new JButton(permission[1]);
        jb2.setForeground(Color.BLUE);
        jb3=new JButton(permission[2]);
        jb3.setForeground(Color.BLUE);

        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();


        jp1.add(jb1);
        jp2.add(jb2);
        jp3.add(jb3);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        //设置布局管理器
        this.setLayout(new GridLayout(4,2,50,50));
        this.setTitle("学生管理系统");
        this.setSize(400,300);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        jb1.addActionListener(this);
        jb2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            dispose();
        }else if(e.getSource() == jb2){
            dispose();
        }

    }
}
