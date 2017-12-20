import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * BorderLayout示例
 *
 * @author jianggujin
 *
 */
public class MyFrame extends JFrame
{
    public MyFrame(String name)
    {
        super("BorderLayoutDemo");
        setBounds(500, 200, 300, 300);
        setLayout(new BorderLayout(10, 10));
        add(new JButton("北"), BorderLayout.NORTH);
        add(new JButton("东"), BorderLayout.EAST);
        add(new JButton("南"), BorderLayout.SOUTH);
        add(new JButton("西"), BorderLayout.WEST);
        add(new JButton("中"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}