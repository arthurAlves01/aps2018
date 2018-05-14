package pkCliente;

import pkAux.Mensagem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainGUICliente extends JFrame implements InterfaceGUICliente, Runnable, MouseListener, WindowListener {
    //Métodos da interface
    public void conectar(){
        String usuario = this.inputNomeUsuario.getText();
        matcherUsuario = PADRAO_USUARIO.matcher(usuario);
        if(!matcherUsuario.find()) {
            alerta("O nome de usuário de iniciar com letra, conter pelo menos 4 caracteres e ser composto apenas de letras e números! ");
        } else {
            RodaCliente.estabelecerConn("192.168.0.6", 12345, usuario);
        }
    }
    public void desconectar(){
        RodaCliente.encerrarConn();
        desabilitarCampos();
        for(Component labelNome: lista.getComponents()) {
            lista.remove(labelNome);
        }
        lista.repaint();
        lista.revalidate();
        lista.validate();
    }
    public void enviarMensagem(Mensagem msg){
        RodaCliente.enviaMensagemParaSocket(msg);
    }
    public void exibirMensagem(Mensagem msg){
        String origem;
        origem = msg.getDestino().equals("\\all")?"\\all":msg.getOrigem();
        if(listaChatsAtivos.get(origem)==null) {
            if(listaChatsAtivos.isEmpty()) {
                ((TitledBorder)wrapConversas.getBorder()).setTitle("Conversa com " + origem + ":");
            }
            listaChatsAtivos.put(origem, addConversa(origem));
        }
        if(!msg.getOrigem().equals(RodaCliente.getUsuarioSocket()))
            listaChatsAtivos.get(origem).addMensagem(msg);
    }
    public void atualizarLista(Mensagem msg){
        ArrayList<String> listaAtualizada = (ArrayList<String>)msg.getMensagem();
        if(msg.getOrigem()!=null) {
            delClienteLista(msg.getOrigem());
            System.out.println(msg.getOrigem());
        }
        for(String nome: listaAtualizada) {
            if(nome.equals(RodaCliente.getUsuarioSocket())) continue;
            if(listaChats.get(nome)==null) {
                addClienteLista(nome);
            }
        }
    }
    private void addClienteLista(String nome) {
        JLabel labelCliente;
        labelCliente = new JLabel(nome);
        labelCliente.addMouseListener(this);
        listaChats.put(nome, new ChatWindow(this, nome));
        lista.add(labelCliente);
        lista.repaint();
        lista.revalidate();
        lista.validate();
    }
    private void delClienteLista(String nome) {
        for(Component labelNome: lista.getComponents()) {
            if(((JLabel)labelNome).getText().equals(nome)) {
                lista.remove((labelNome));
                lista.repaint();
                lista.revalidate();
                lista.validate();
                break;
            }
        }
        if(listaChatsAtivos.get(nome)!=null) {
            listaChatsAtivos.get(nome).addAlerta(nome + " desconectou!");
        }
    }
    public void habilitarCampos(){
        btnConectar.setEnabled(false);
        btnDesconectar.setEnabled(true);
        inputNomeUsuario.setEnabled(false);
        addClienteLista("\\all");
    }
    public void desabilitarCampos(){
        listaChatsAtivos.clear();
        listaChats.clear();
        for(Component c: lista.getComponents()) {
            lista.remove(c);
        }
        for(Component c: wrapConversas.getComponents()) {
            //wrapConversas.remove(c);
        }
        lista.revalidate();
        lista.repaint();
        btnConectar.setEnabled(true);
        btnDesconectar.setEnabled(false);
        inputNomeUsuario.setEnabled(true);
    }
    public void atualizaConversas(){}
    public void alerta(String texto){
        JOptionPane.showMessageDialog(null, texto);
    };
    public ChatWindow addConversa(String contato) {
        ChatWindow chatWin = listaChats.get(contato);
        wrapConversas.add(contato, chatWin);
        //((CardLayout)wrapConversas.getLayout()).addLayoutComponent(chatWin, contato);
        //wrapConversas.repaint();
        //wrapConversas.revalidate();
        //wrapConversas.validate();
        return chatWin;
    }

    //Propriedads diversas da janela
    private final Pattern PADRAO_USUARIO = Pattern.compile("^[A-Za-z][A-Za-z0-9]{3,}$");
    private Matcher matcherUsuario;
    private HashMap<String, ChatWindow> listaChats;
    private HashMap<String, ChatWindow> listaChatsAtivos;

    //Campos da GUI
    //Declarações dos componentes do campo de conexão
    private JPanel optsConn;
    private JLabel nomeUsuario;
    private JTextField inputNomeUsuario;
    private JButton btnConectar;
    private JButton btnDesconectar;

    //Declaroções dos componentes do campo com a lista dos usuários logados
    private JPanel wrapListaUsuarios;
    private JScrollPane scrollListaUsuarios;
    private JPanel lista;

    //Declaroções dos componentes do campo com as janelas de conversas
    private JTabbedPane wrapConversas;

    public void run() { initGUI(); }
    public void initGUI() {
        //Declaração dos layouts
        javax.swing.border.Border bordaComTitulo;
        SpringLayout springL1 = new SpringLayout(); //this
        FlowLayout flowL1 = new FlowLayout(FlowLayout.LEFT);
        GridBagLayout gridBagL1 = new GridBagLayout();
        //Inicialização principal do frame
        this.setTitle("QuiChat");
        this.setMinimumSize(new Dimension(645,350));
        this.setMaximumSize(new Dimension(645,350));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(springL1);
        this.addWindowListener(this);

        //Inicialização dos componentes principais
        optsConn = new JPanel();
        optsConn.setLayout(flowL1);
        optsConn.setBorder(BorderFactory.createLineBorder(new Color(120,120,120)));

        //Criação dos componentes referente as opções de conexão
        optsConn = new JPanel();
        optsConn.setLayout(flowL1);
        bordaComTitulo = BorderFactory.createLineBorder(new Color(128,128,128));
        bordaComTitulo = BorderFactory.createTitledBorder(bordaComTitulo, "Menu");
        optsConn.setBorder(bordaComTitulo);
        //Inicializa componentes do panel acima
        nomeUsuario = new JLabel("Nome de usuário:");
        inputNomeUsuario = new JTextField(15);
        btnConectar = new JButton("Conectar");
        btnDesconectar = new JButton("Desconectar");
        btnDesconectar.setEnabled(false);
        //Adiciona os componentes no panel
        optsConn.add(nomeUsuario);
        optsConn.add(inputNomeUsuario);
        optsConn.add(btnConectar);
        optsConn.add(btnDesconectar);
        //--------------------------------------

        //Criação dos componentes da lista de usuários logados
        wrapListaUsuarios = new JPanel();
        wrapListaUsuarios.setLayout(gridBagL1);
        bordaComTitulo = BorderFactory.createLineBorder(new Color(128,128,128));
        bordaComTitulo = BorderFactory.createTitledBorder(bordaComTitulo, "Clientes");
        wrapListaUsuarios.setBorder(bordaComTitulo);

        lista = new JPanel();
        lista.setLayout(new BoxLayout(lista, BoxLayout.PAGE_AXIS));
        lista.setBorder(BorderFactory.createEmptyBorder(0,3,0,0));

        scrollListaUsuarios = new JScrollPane(wrapListaUsuarios, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollListaUsuarios.setBorder(null);
        scrollListaUsuarios.setPreferredSize(new Dimension(120, 290));

        scrollListaUsuarios.getViewport().add(lista);
        wrapListaUsuarios.add(scrollListaUsuarios);
        //--------------------------------------

        //Criação dos componentes da janela contendo as conversas
        wrapConversas = new JTabbedPane();
        bordaComTitulo = BorderFactory.createLineBorder(new Color(255,0,0));
        wrapConversas.setBorder(null);
        //wrapConversas.setPreferredSize(new Dimension(100,100));

        //--------------------------------------


        //Constraints do frame principal
        //Campo com opções da connexão
        springL1.putConstraint(SpringLayout.WEST, optsConn, 5, SpringLayout.WEST, this);
        springL1.putConstraint(SpringLayout.NORTH, optsConn, 5, SpringLayout.NORTH, this);
        //Lista de clientes
        springL1.putConstraint(SpringLayout.WEST, wrapListaUsuarios, 5, SpringLayout.EAST, optsConn);
        springL1.putConstraint(SpringLayout.NORTH, wrapListaUsuarios, 5, SpringLayout.NORTH, this);
        //Campo das conversas
        springL1.putConstraint(SpringLayout.NORTH, wrapConversas, 5, SpringLayout.SOUTH, optsConn);
        springL1.putConstraint(SpringLayout.WEST, wrapConversas, 5, SpringLayout.WEST, this);
        springL1.putConstraint(SpringLayout.EAST, wrapConversas, -5, SpringLayout.WEST, wrapListaUsuarios);
        springL1.putConstraint(SpringLayout.SOUTH, wrapConversas, 0, SpringLayout.SOUTH, wrapListaUsuarios);


        //Eventos dos componentes
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
            }
        });
        btnDesconectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "Deseja desconectar?","Desconectar", JOptionPane.YES_NO_OPTION)==0)
                    desconectar();
            }
        });
        inputNomeUsuario.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10) {
                    btnConectar.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        listaChats = new HashMap<>();
        listaChatsAtivos = new HashMap<>();
        //Adiciona os panels no frame principal
        this.add(optsConn);
        this.add(wrapListaUsuarios);
        this.add(wrapConversas);
        this.pack();
        this.setFontePadrao(new java.awt.Font("Arial Unicode MS", java.awt.Font.PLAIN, 11));
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            String contatoSelecionado = ((JLabel)e.getComponent()).getText();
            e.consume();
            if(listaChatsAtivos.get(contatoSelecionado)==null) {
                listaChatsAtivos.put(contatoSelecionado, addConversa(contatoSelecionado));
            }
            //((CardLayout)wrapConversas.getLayout()).show(wrapConversas, contatoSelecionado);
            //((TitledBorder)wrapConversas.getBorder()).setTitle("Conversa com " + contatoSelecionado + ":");
            wrapConversas.revalidate();
            wrapConversas.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private void setFontePadrao (java.awt.Font f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value != null && value instanceof java.awt.Font)
                UIManager.put (key, f);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        desconectar();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
