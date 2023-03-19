package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddData extends JFrame {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,40));
    JLabel name=new JLabel("物品编号:");
    JLabel weight=new JLabel("物品重量:");
    JLabel value=new JLabel("物品价值:");
    static JTextField nameF=new JTextField();
    static JTextField weightF=new JTextField();
    static JTextField valueF=new JTextField();
    JButton add=new JButton("添加");

    public AddData(){
        add(jPanel);
        nameF.setPreferredSize(new Dimension(100,30));
        weightF.setPreferredSize(new Dimension(100,30));
        valueF.setPreferredSize(new Dimension(100,30));
        jPanel.add(name);
        jPanel.add(nameF);

        jPanel.add(weight);
        jPanel.add(weightF);

        jPanel.add(value);
        jPanel.add(valueF);

        jPanel.add(add);
        jPanel.setVisible(true);
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//只销毁当前窗体
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddData();
        ActionListener addbt=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameF.getText();
                weightF.getText();
                valueF.getText();

            }
        };
    }

}
