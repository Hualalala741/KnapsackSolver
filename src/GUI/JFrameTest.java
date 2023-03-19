package GUI;

import javax.swing.*;
import java.awt.*;

public class JFrameTest extends JFrame {
    JLabel name=new JLabel("01背包求解");
    SpringLayout springLayout=new SpringLayout();
    JPanel jPanel=new JPanel(springLayout);
    JLabel option=new JLabel("选择求解方法");

    public JFrameTest(){//构造函数

        Container contentPane =getContentPane();
        setSize(600,400);
        //居中
        setLocationRelativeTo(null);
        //关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //不改变大小
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JFrameTest();
    }


}
