package pkCliente;

import javax.swing.*;
import java.awt.event.*;

public class InterfaceCliente implements Runnable, ActionListener {
    private JFrame janela;
    private JPanel conteudo;
    private JTextArea inputBox;
    private JButton enviar;

    public InterfaceCliente() {
        System.out.println("Inerface rodando.");
    }
    public void run() {
        janela = new JFrame("Char - APS 2018");
        janela.setSize(200,500);

        conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.PAGE_AXIS));

        inputBox = new JTextArea();
        inputBox.setRows(3);

        enviar = new JButton("Enviar");
        enviar.addActionListener(this);

        conteudo.add(enviar);
        conteudo.add(inputBox);

        janela.add(conteudo);
        janela.setVisible(true);
    }
    public void mostraMensagem(String msg) {
        conteudo.add(new JLabel(msg));
        janela.repaint();
        janela.revalidate();
    }
    public void actionPerformed(ActionEvent e) {
        RodaCliente.enviaMensagemParaSocket(inputBox.getText());
        inputBox.setText("");
    }
}

