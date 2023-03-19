package GUI;

import bt.btBags;
import dp.Bags;
import tx.txBags;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class knapsack extends JFrame {

    JLabel nameLabel =new JLabel("0-1背包求解",JLabel.CENTER);
    FlowLayout flowLayout=new FlowLayout(FlowLayout.CENTER,30,40);
    JPanel jp =new JPanel(flowLayout);
    JPanel jp2=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel optionLabel =new JLabel("请选择求解方法：");
    static JButton getBt=new JButton("添加数据");
    JButton solveBt=new JButton(" 求解 ");
    JComboBox cmb=new JComboBox();    //创建JComboBox
    JTable table=new JTable();
    JScrollPane pane=new JScrollPane(table);


    //JPanel addPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,40));
    JLabel number=new JLabel("物品编号:");
    JLabel weight=new JLabel("物品重量:");
    JLabel value=new JLabel("物品价值:");
    JLabel Capacity=new JLabel("请输入背包容量:");
    JTextField nameF=new JTextField();
    JTextField weightF=new JTextField();
    JTextField valueF=new JTextField();
    JTextField CapacityF=new JTextField();
    JButton del=new JButton("删除数据");
    JLabel intro =new JLabel("说明：\n【添加数据】可在表格下一行继续添加数据。\n【删除数据】选中数据，即可删除。\n【求解】选择算法，求解0-1背包问题。");



    public knapsack(){//构造函数
        super("01背包问题求解");
        nameLabel.setFont(new Font("楷体",Font.PLAIN,40));
        //optionLabel.setFont(new Font("楷体",Font.PLAIN,20));
        Container contentPane =getContentPane();
        //下拉列表添加
        cmb.addItem("--请选择--");
        cmb.addItem("动态规划");
        cmb.addItem("改进后的动态规划");
        cmb.addItem("贪心算法");
        cmb.addItem("回溯算法");
        //表格
        pane.setPreferredSize(new Dimension(400, 150));
        DefaultTableModel tableModel=(DefaultTableModel) table.getModel();    //获得表格模型
        String[] name={"物品编号","重量","价值"};
        tableModel.setColumnIdentifiers(name);
        tableModel.addRow(new String[]{"1", "22", "46"});
        tableModel.addRow(new String[]{"2", "8", "26"});
        tableModel.addRow(new String[]{"3", "23", "25"});
        tableModel.addRow(new String[]{"4", "43", "57"});
        tableModel.addRow(new String[]{"5", "23", "24"});
        tableModel.addRow(new String[]{"6", "35", "45"});
        tableModel.addRow(new String[]{"7", "24", "44"});
        tableModel.addRow(new String[]{"8", "32", "65"});
        tableModel.addRow(new String[]{"9", "2", "13"});
        tableModel.addRow(new String[]{"10", "16", "16"});
        tableModel.addRow(new String[]{"11", "30", "39"});
        tableModel.addRow(new String[]{"12", "20", "43"});
        table.setModel(tableModel);    //应用表格模型

        //加入面板
        add(jp);
        jp.add(nameLabel,BorderLayout.NORTH);
        //jp.add(addPanel);
        jp.add(pane);
        //eastPanel.add(table);

        nameF.setPreferredSize(new Dimension(105,30));
        weightF.setPreferredSize(new Dimension(110,30));
        valueF.setPreferredSize(new Dimension(95,30));
        CapacityF.setPreferredSize(new Dimension(150,30));

        getBt.setPreferredSize(new Dimension(90,25));
        CapacityF.setPreferredSize(new Dimension(290,30));

        jp.add(number);
        jp.add(nameF);

        jp.add(weight);
        jp.add(weightF);

        jp.add(value);
        jp.add(valueF);

        jp.add(getBt);
        jp.add(del);

        jp.add(Capacity);
        jp.add(CapacityF);
        jp.add(optionLabel);
        jp.add(cmb);

        jp.add(solveBt);
        //jp.add(intro);
        //contentPane.add(eastPanel,BorderLayout.EAST);
        ActionListener add=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //AddData ad=new AddData();//为跳转的界面?构造
                String num= nameF.getText();//
                String wei=weightF.getText();
                String val=valueF.getText();
                String[] addd={num,wei,val};
                tableModel.addRow(addd);
                nameF.setText("");
                weightF.setText("");
                valueF.setText("");
            }
        };
        getBt.addActionListener(add);

        ActionListener doo=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String med= cmb.getSelectedItem().toString();
                int row = table.getRowCount();//获取行数 3 012
                int[] weight=new int[row+1]; //0123 4
                int[] value=new int[row+1];
                int[] number=new int[row+1];
                weight[0]=0;
                value[0]=0;
                number[0]=0;
                for(int i=0;i<row;i++){
                    weight[i+1]=Integer.valueOf(tableModel.getValueAt(i,1).toString());
                    value[i+1]=Integer.valueOf(tableModel.getValueAt(i,2).toString());
                    number[i+1]=Integer.valueOf(tableModel.getValueAt(i,0).toString());
                }

                int[] weight1=new int[row]; //0123 4
                int[] value1=new int[row];
                int[] number1=new int[row];
                for(int i=0;i<row;i++){
                    weight1[i]=Integer.valueOf(tableModel.getValueAt(i,1).toString());
                    value1[i]=Integer.valueOf(tableModel.getValueAt(i,2).toString());
                    number1[i]=Integer.valueOf(tableModel.getValueAt(i,0).toString());
                }
                if (CapacityF.getText().trim().equals("")||CapacityF.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "请输入背包容量！");
                }
                int capacity= Integer.valueOf(CapacityF.getText());

                switch (med){
                    case "动态规划":
                        //JOptionPane.showMessageDialog(null,"这是一个对话框");
                        Bags bags=new Bags();
                        int[][] m=bags.knapsack(weight,value,capacity);
                        String word1="最优价值是："+bags.getbestvalue(weight,capacity,m)+"\n"+bags.traceback(m, weight, capacity,number);
                        //String word1="最优"+Arrays.toString(bags.traceback(m, weight, capacity));
                        JOptionPane.showMessageDialog(null,word1);
                        break;
                    case"改进后的动态规划":
                        udp.Bags pack =new udp.Bags();
                        int[][] p=pack.getp(weight,capacity);
                        String word2="求得的最优值为："+pack.knapsack(weight,value,capacity,p)+"\n"+pack.show_traceback(weight,value,capacity,number);
                        JOptionPane.showMessageDialog(null,word2);
                        break;
                    case"贪心算法":
                        txBags txbags=new txBags();
                        txbags.csh(weight1,value1,capacity,number1);
                        String word3="求得的最优值为："+txbags.getbestvalue()+"\n"+txbags.getx();
                        JOptionPane.showMessageDialog(null,word3);
                        break;
                    case"回溯算法":
                        btBags btBags=new btBags();
                        btBags.csh(weight,value,capacity);
                        btBags.BackTrack(1);
                        String bbv=btBags.getbestvalue();

                        String word4="最优价值是："+bbv+"\n"+btBags.getbestx(number);
                        JOptionPane.showMessageDialog(null,word4);
                        break;
                    case"--请选择--":
                        JOptionPane.showMessageDialog(null, "请选择算法！");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + med);
                }

                }

        };
        solveBt.addActionListener(doo);

        ActionListener delac=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.removeRow(table.getSelectedRow());
            }
        };
        del.addActionListener(delac);
        //定义大小
        setSize(500,700);
        //居中
        setLocationRelativeTo(null);
        //关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //不改变大小
        setResizable(false);
        setVisible(true);

    }



    public static void main(String[] args) {
        new knapsack();
    }

}