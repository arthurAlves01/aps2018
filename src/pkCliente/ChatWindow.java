package pkCliente;

import pkAux.Mensagem;
import pkAux.TipoMensagem;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

public class ChatWindow extends JPanel {
    private String destino; //Destino das mensagens enviadas nessa tela
    private JPanel wrapConversa; //Panel onde ficam o scroll pane e o panel da conversa
    private JScrollPane scrollConversa, scrollInput; //Scroll pane do input e da conversa
    private JPanel conversa; //Panel onde aparecem as mensagens
    private JButton btnEnviarMensagem; //Botão de enviar mensagem
    private JButton anexar; //TODO: Cria função de anexar imagem
    private MainGUICliente janelaPrincipal; //Janela pai (MainGUICliente)
    private SpringLayout spl1; //Spring layout da classe
    private JTextArea inputMensagem; //Campo de input da mesagem
    private boolean statusCliente; //Controla a permissão para enviar ou não mensagens nessa tela

    public ChatWindow(MainGUICliente janelaPrincipal, String destino) {
        //Configurações inicias da classe
        this.spl1 = new SpringLayout();
        this.setLayout(spl1);
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.destino = destino;
        this.janelaPrincipal = janelaPrincipal;
        this.setBorder(null);
        this.statusCliente = true;

        //Instanciação dos componentes da janela da conversa
        this.wrapConversa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.conversa = new JPanel();
        this.conversa.setLayout(new BoxLayout(conversa, BoxLayout.PAGE_AXIS));
        this.scrollConversa = new JScrollPane(wrapConversa, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollConversa.setBorder(null);
        this.scrollConversa.setPreferredSize(new Dimension(470,155));
        //Instanciação dos componentes do campo de input
        this.scrollInput = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollInput.setBorder(new LineBorder(new Color(126,126,126)));
        this.inputMensagem = new JTextArea(1, 25);
        this.inputMensagem.setLineWrap(true);
        //Instanciação do botão de enviar
        this.btnEnviarMensagem = new JButton("Enviar");

        //Constraints da FlowLayout
        //Janela das mensagens
        this.spl1.putConstraint(SpringLayout.WEST, wrapConversa, 5, SpringLayout.WEST, this);
        this.spl1.putConstraint(SpringLayout.EAST, wrapConversa, -5, SpringLayout.EAST, this);
        //Campo de input
        this.spl1.putConstraint(SpringLayout.WEST, scrollInput, 5, SpringLayout.WEST, this);
        this.spl1.putConstraint(SpringLayout.NORTH, scrollInput, 5, SpringLayout.SOUTH, wrapConversa);
        this.spl1.putConstraint(SpringLayout.SOUTH, scrollInput, -5, SpringLayout.SOUTH, this);
        this.spl1.putConstraint(SpringLayout.EAST, scrollInput, -5, SpringLayout.WEST, btnEnviarMensagem);
        //Botão de enviar
        this.spl1.putConstraint(SpringLayout.EAST, btnEnviarMensagem, -5, SpringLayout.EAST, this);
        this.spl1.putConstraint(SpringLayout.NORTH, btnEnviarMensagem, 5, SpringLayout.SOUTH, wrapConversa);
        this.spl1.putConstraint(SpringLayout.SOUTH, btnEnviarMensagem, -5, SpringLayout.SOUTH, this);

        //Adiciona os componentes
        this.scrollConversa.getViewport().add(conversa);
        this.scrollInput.getViewport().add(inputMensagem);
        this.wrapConversa.add(scrollConversa);
        this.add(wrapConversa);
        this.add(scrollInput);
        this.add(btnEnviarMensagem);

        //Evento do botão
        btnEnviarMensagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensagem();
            }
        });
        //Eventos do input
        inputMensagem.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10) {
                    enviarMensagem();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10) {
                    inputMensagem.setText("");
                }
            }
        });
    }
    public void addMensagem(Mensagem msg) {
        //Metódo que recebe a mensagem e decide como será adicionada na tela - IMG ou TEXTO
        if(msg.getTipoMsg()==TipoMensagem.TEXTO)
            addMensagemTexto(msg);
        //Atualiza a tela
        conversa.repaint();
        conversa.revalidate();
    }
    private void addMensagemTexto(Mensagem msg) {
        //Adiciona mensagem de texto
        StringBuffer textoDeExib;
        JLabel labelTexto = new JLabel();
        textoDeExib = new StringBuffer();
        String timeStampFormatado = new SimpleDateFormat("HH:mm").format(msg.getTimeStamp());
        textoDeExib.append("(");
        textoDeExib.append(timeStampFormatado);
        textoDeExib.append(") ");
        if(msg.getOrigem().equals(RodaCliente.getUsuarioSocket())) {
            //Se for o proprio usuário
            textoDeExib.append("Você disse: ");
            labelTexto.setFont(new Font(labelTexto.getFont().getName(), Font.BOLD, labelTexto.getFont().getSize()));
        } else {
            textoDeExib.append(msg.getOrigem() + " disse: ");
        }
        textoDeExib.append(msg.getMensagem());
        labelTexto.setText(textoDeExib.toString());
        conversa.add(labelTexto);
    }
    private void addAlerta(String alerta, Color cor) {
        //Adiciona um alerta na tela
        JLabel labelAlerta = new JLabel(alerta);
        Color corUtilizada;
        corUtilizada = cor==null?Color.RED:cor;
        labelAlerta.setForeground(corUtilizada);
        conversa.add(labelAlerta);
        conversa.revalidate();
    }
    public void sinalizaDcCliente(String nome) {
        //Sinaliza que o cliente(destino) desconectou e adiciona um alerta na tela
        addAlerta(nome + " desconectou!", null);
        this.statusCliente = false;
    }
    public void sinalizaConnCliente(String nome) {
        //Adiciona alerta informando que o cliente (destino) conectou
        addAlerta(nome + " conectou!", Color.GREEN);
        this.statusCliente = true;
    }
    private void enviarMensagem() {
        //Valida as informações quando o botão enviar é clicado
        if(inputMensagem.getText().equals("")) return; //Se o campo estiver vazio não faz nada
        if(inputMensagem.getText().equals("\\exit")) {
            //Utilize o comando \exit para fechar está janela
            inputMensagem.setText("");
            janelaPrincipal.deletaChat(destino);
            return;
        }
        if(!statusCliente) {
            //Se o statusCliente estiver false não enviar a mensagem e avisa o usuário
            JOptionPane.showMessageDialog(null, "O usuário não está logado!");
            return;
        }
        //Instancia a classe mensagem e envia para a janelaPrincipal
        Mensagem mensagemDeSaida = new Mensagem(RodaCliente.getUsuarioSocket(), destino, inputMensagem.getText());
        janelaPrincipal.enviarMensagem(mensagemDeSaida);
        addMensagem(mensagemDeSaida);
        inputMensagem.setText("");
    }
}
