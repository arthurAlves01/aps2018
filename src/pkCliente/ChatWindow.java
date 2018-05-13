package pkCliente;

import pkAux.Mensagem;
import pkAux.TipoMensagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class ChatWindow extends JPanel {
    private String destino;
    private JPanel wrapConversa;
    private JScrollPane scrollConversa, scrollInput;
    private JPanel conversa;
    private JButton btnEnviarMensagem;
    private JButton anexar;
    private MainGUICliente janelaPrincipal;
    private SpringLayout spl1;
    private JTextArea inputMensagem;
    public ChatWindow(MainGUICliente janelaPrincipal, String destino) {
        this.spl1 = new SpringLayout();
        this.setLayout(spl1);
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.destino = destino;
        this.janelaPrincipal = janelaPrincipal;
        //System.out.println(this.janelaPrincipal.getName());
        GridBagLayout gbl = new GridBagLayout();
        wrapConversa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapConversa.setBackground(Color.blue);
        conversa = new JPanel();
        conversa.setLayout(new BoxLayout(conversa, BoxLayout.PAGE_AXIS));
        //conversa.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        //conversa.add(new JLabel("qweqwe"));

        this.scrollConversa = new JScrollPane(wrapConversa, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollConversa.setBorder(null);
        this.scrollConversa.setPreferredSize(new Dimension(465,170));

        this.scrollInput = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollInput.setBorder(null);
        this.scrollInput.setPreferredSize(new Dimension(465,170));

        this.inputMensagem = new JTextArea(1, 25);
        this.inputMensagem.setLineWrap(true);

        this.btnEnviarMensagem = new JButton("Enviar");

        this.spl1.putConstraint(SpringLayout.WEST, wrapConversa, 5, SpringLayout.WEST, this);
        this.spl1.putConstraint(SpringLayout.EAST, wrapConversa, -5, SpringLayout.EAST, this);

        this.spl1.putConstraint(SpringLayout.WEST, scrollInput, 5, SpringLayout.WEST, this);
        this.spl1.putConstraint(SpringLayout.NORTH, scrollInput, 5, SpringLayout.SOUTH, wrapConversa);
        this.spl1.putConstraint(SpringLayout.SOUTH, scrollInput, -5, SpringLayout.SOUTH, this);
        this.spl1.putConstraint(SpringLayout.EAST, scrollInput, -5, SpringLayout.WEST, btnEnviarMensagem);

        this.spl1.putConstraint(SpringLayout.EAST, btnEnviarMensagem, -5, SpringLayout.EAST, this);
        this.spl1.putConstraint(SpringLayout.NORTH, btnEnviarMensagem, 5, SpringLayout.SOUTH, wrapConversa);
        this.spl1.putConstraint(SpringLayout.SOUTH, btnEnviarMensagem, -5, SpringLayout.SOUTH, this);

        this.scrollConversa.getViewport().add(conversa);
        this.scrollInput.getViewport().add(inputMensagem);
        this.wrapConversa.add(scrollConversa);
        this.add(wrapConversa);
        this.add(scrollInput);
        this.add(btnEnviarMensagem);

        btnEnviarMensagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mensagem mensagemDeSaida = new Mensagem(RodaCliente.getUsuarioSocket(), destino, inputMensagem.getText());
                janelaPrincipal.enviarMensagem(mensagemDeSaida);
                addMensagem(mensagemDeSaida);
                inputMensagem.setText("");
            }
        });
    }
    public void addMensagem(Mensagem msg) {
        if(msg.getTipoMsg()==TipoMensagem.TEXTO)
            addMensagemTexto(msg);
        conversa.revalidate();
    }
    private void addMensagemTexto(Mensagem msg) {
        StringBuffer textoDeExib;
        textoDeExib = new StringBuffer();
        String timeStampFormatado = new SimpleDateFormat("HH:mm").format(msg.getTimeStamp());
        textoDeExib.append("(");
        textoDeExib.append(timeStampFormatado);
        textoDeExib.append(") ");
        if(msg.getOrigem().equals(RodaCliente.getUsuarioSocket())) {
            textoDeExib.append("Você disse: ");
        } else {
            textoDeExib.append(msg.getOrigem() + " disse: ");
        }

        textoDeExib.append(msg.getMensagem());
        conversa.add(new JLabel(textoDeExib.toString()));
    }
}
