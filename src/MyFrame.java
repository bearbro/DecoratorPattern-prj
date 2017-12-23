import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;


public class MyFrame extends JFrame implements ActionListener {
    JTextArea textArea1;
    JTextArea textArea2;
    JButton sumbit;
    String cacheFile = "cacheFile.txt";//缓存文件
    JRadioButton[] r;//输入类型选择

    public MyFrame(String name) {
        super(name);
        JPanel jp = new JPanel();

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        textArea1 = new JTextArea("orgin", 4, 20);
        textArea2 = new JTextArea("", 4, 20);
        sumbit = new JButton("转换");
        JLabel input = new JLabel("输入：");
        JLabel out = new JLabel("输出：");
        Font hFont = new Font("宋体", Font.BOLD, 16);
        input.setFont(hFont);
        out.setFont(hFont);

        //单选框
        r = new JRadioButton[]{new JRadioButton("文本"), new JRadioButton("文件")};
        ButtonGroup rg = new ButtonGroup();//组合
        rg.add(r[0]);
        rg.add(r[1]);

        jp.add(input);
        jp.add(textArea1);

        JPanel subjp=new JPanel();
        subjp.add(r[0]);
        subjp.add(r[1]);
        subjp.add(sumbit);
        jp.add(subjp);

        jp.add(out);
        jp.add(textArea2);
        //textArea1的右侧与JButton的左侧对齐
        textArea1.setAlignmentX(Component.LEFT_ALIGNMENT);
        subjp.setAlignmentX(Component.LEFT_ALIGNMENT);
        textArea2.setAlignmentX(Component.LEFT_ALIGNMENT);
        textArea2.setEditable(false);

        //添加监视器
        sumbit.addActionListener(this);
        r[0].addActionListener(this);
        r[1].addActionListener(this);
        //创建快捷键
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //点击sumbit按钮
                    actionPerformed(new ActionEvent(sumbit,0,null));// ActionEvent(Object source, int id, String command);
                }
            }
        });
        setContentPane(jp);
        setBounds(500, 200, 300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sumbit) {
            if (r[0].isSelected()) {
                doPb();//过滤词汇
            } else if (r[1].isSelected()) {
                if(textArea1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "请输入源文件", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else if(textArea2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "请输入目标文件", "提示", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    doPb(textArea1.getText(), textArea2.getText());
                }

            } else {
                JOptionPane.showMessageDialog(null, "请选择输入类型", "提示", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == r[0]) {
            textArea2.setText("");
            textArea2.setEditable(false);
        } else if (e.getSource() == r[1]) {
            textArea2.setText("");
            textArea2.setEditable(true);
        }
    }

    private void doPb() {
        textArea2.setText(MyWriter.pb(new StringBuffer(textArea1.getText())));

//            try {
//                PrintWriter out = new PrintWriter(
//                        new BufferedWriter(
//                                new MyWriter(
//                                        new OutputStreamWriter(
//                                                new FileOutputStream(cacheFile)
//                                        ))
//                        )
//                );
//                out.write(textArea1.getText());
//                out.flush();
//                out.close();
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(
//                                new FileInputStream(cacheFile)
//                        )
//                );
//                StringBuffer all = new StringBuffer();
//                String a = in.readLine();
//                while (a != null) {
//                    all.append(a);
//                    a = in.readLine();
//                }
//                in.close();
//                textArea2.setText(all.toString());
//            } catch (FileNotFoundException e1) {
//                System.out.println(e1.toString());
//            } catch (IOException e1) {
//                System.out.println(e1.toString());
//            }
    }

    private void doPb(String orgin, String aim) {
//        orgin=orgin.replaceAll("/","\\\\");
//        aim=aim.replaceAll("/","\\\\");
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(orgin)
                    )
            );
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new MyWriter(
                                    new OutputStreamWriter(
                                            new FileOutputStream(aim)
                                    ))
                    )
            );

            String a = in.readLine();
            while (a != null) {
                out.println(a);
                a = in.readLine();
            }
            out.flush();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "未找到文件" + orgin, "出错", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "出错", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e.toString());
        }
    }
}