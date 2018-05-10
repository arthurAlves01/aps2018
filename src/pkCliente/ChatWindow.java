package pkCliente;

import pkAux.Mensagem;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JPanel {
    private String destino;
    private JPanel wrapConversa;
    private JScrollPane scrollConversa;
    private JPanel conversa;
    private JButton enviarMensagem;
    private JButton anexar;
    private MainGUICliente janelaPrincipal;
    private SpringLayout spl1;
    public ChatWindow(String destino) {
        this.spl1 = new SpringLayout();
        this.setLayout(spl1);
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.destino = destino;
        this.janelaPrincipal = (MainGUICliente) SwingUtilities.getWindowAncestor(this);
        //System.out.println(this.janelaPrincipal.getName());
        GridBagLayout gbl = new GridBagLayout();
        wrapConversa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapConversa.setBackground(Color.blue);
        conversa = new JPanel();
        conversa.setLayout(new BoxLayout(conversa, BoxLayout.PAGE_AXIS));
        //conversa.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        conversa.add(new JLabel("qweqwe"));

        this.scrollConversa = new JScrollPane(wrapConversa, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollConversa.setBorder(null);
        this.scrollConversa.setPreferredSize(new Dimension(300,170));


        spl1.putConstraint(SpringLayout.WEST, wrapConversa, 5, SpringLayout.WEST, this);
        spl1.putConstraint(SpringLayout.EAST, wrapConversa, -5, SpringLayout.EAST, this);


        this.scrollConversa.getViewport().add(conversa);
        this.wrapConversa.add(scrollConversa);
        this.add(wrapConversa);
    }
    public void addMensagem(Mensagem msg) {

    }
}
