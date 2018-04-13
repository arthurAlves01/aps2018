import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TesteLayout implements WindowListener {
    public static void main(String[] args) {
        TesteLayout t1 = new TesteLayout();

    }
    public TesteLayout(){
        JFrame j1 = new JFrame("Qoweiq");
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        j1.add(p1);
        p1.add(new JLabel("teste"));
        p1.add(new Button("Enviar"));
        p1.add(new JLabel("teste"));
        JTextArea t1 = new JTextArea();
        t1.setLineWrap(true);
        p1.add(t1);
        p1.add(new JLabel("teste"));


        j1.addWindowListener(this);
        j1.setSize(500,300);
        j1.setVisible(true);
    }
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }

    public void windowGainedFocus(WindowEvent e) {

    }

    public void windowLostFocus(WindowEvent e) {

    }

    public void windowStateChanged(WindowEvent e) {

    }
}
