package pkTestes;

import javax.swing.*;
import java.awt.*;

public class TesteScrollPane extends JFrame {
    private JPanel p1, p2;
    private JScrollPane s1;
    public TesteScrollPane() {
        this.setLayout(new SpringLayout());
        this.setMinimumSize(new Dimension(200,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1 = new JPanel(new GridBagLayout());
        p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
        s1 = new JScrollPane(p1);
        s1.setPreferredSize(new Dimension(150,200));
        s1.getViewport().add(p2);
        p2.add(new JLabel("eq68we"));
        p2.add(new JLabel("eq9we"));
        p2.add(new JLabel("eq4we"));
        p2.add(new JLabel("eq5we"));
        p2.add(new JLabel("5eqw2e"));
        p2.add(new JLabel("eqwe"));
        p2.add(new JLabel("eqwe"));
        p2.add(new JLabel("eqwe"));
        p2.add(new JLabel("e7qw3e"));
        p2.add(new JLabel("eqwe"));
        p2.add(new JLabel("eq5we"));
        p2.add(new JLabel("eq6we"));
        p2.add(new JLabel("eqw4e"));
        p2.add(new JLabel("eqwe"));
        this.add(s1);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        TesteScrollPane t1 = new TesteScrollPane();
    }
}
