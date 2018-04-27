package pkCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import pkAux.*;

public class InterfaceCliente implements Runnable, ActionListener, WindowListener {
    private JFrame janela;
    private JPanel conteudo;
    private JTextField inputBox;
    private JButton enviar, conectar;
    private JScrollPane scrollMsg;
    private JPanel telaDeMensagens;

    public InterfaceCliente() {
        System.out.println("Interface rodando.");
    }
    public void run() {
        janela = new JFrame("Char - APS 2018");
        janela.setSize(500,300);
        janela.addWindowListener(this);

        conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.PAGE_AXIS));

        inputBox = new JTextField();
        inputBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));

        enviar = new JButton("Enviar");
        enviar.addActionListener(this);

        conectar = new JButton("Conectar");
        conectar.addActionListener(this);

        telaDeMensagens = new JPanel();
        telaDeMensagens.setLayout(new BoxLayout(telaDeMensagens, BoxLayout.PAGE_AXIS));
        scrollMsg = new JScrollPane(telaDeMensagens);
        scrollMsg.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        conteudo.add(scrollMsg);
        conteudo.add(inputBox);
        conteudo.add(enviar);
        conteudo.add(conectar);

        janela.add(conteudo);
        janela.setVisible(true);
    }
    public void mostraMensagem(Mensagem msg) {
        JLabel mensagem = new JLabel((String) msg.getMensagem());
        telaDeMensagens.add(mensagem);
        scrollMsg.getVerticalScrollBar().setValue(scrollMsg.getVerticalScrollBar().getMaximum());
        janela.repaint();
        janela.revalidate();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Enviar")) {
            if (!inputBox.getText().equals("")) {
                RodaCliente.enviaMensagemParaSocket(new Mensagem(inputBox.getText()));
                inputBox.setText("");
            } else {
                if (!RodaCliente.getEstadoConn())
                    System.out.println("Você não está conectado!");
            }
        } else if(e.getActionCommand().equals("Conectar")) {
            RodaCliente.estabelecerConn("127.0.0.1", 12345, "Eu");
        }
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

