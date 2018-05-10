package pkCliente;

import pkAux.Mensagem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainGUICliente extends JFrame implements InterfaceGUICliente {
    //Métodos da interface
    public void conectar(){}
    public void desconectar(){}
    public void enviarMensagem(Mensagem msg){}
    public void exibirMensagem(Mensagem msg){}
    public void atualizarLista(ArrayList<String> clientes){}
    public void habilitarCmapos(){}
    public void desabilitarCampos(){}
    public void atualizaConversas(){}

    //Propriedads diversas da janela
    private final Pattern PADRAO_USUARIO = Pattern.compile("^[A-Za-z][A-Za-z0-9]{3,}$");
    private Matcher matcherUsuario;
    private HashMap<String, ChatWindow> listaChats;

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
    private JPanel wrapConversas;

    public MainGUICliente() {
        initGUI();
    }
    public void initGUI() {
        //Declaração dos layouts
        javax.swing.border.Border bordaComTitulo;
        SpringLayout springL1 = new SpringLayout(); //this
        FlowLayout flowL1 = new FlowLayout(FlowLayout.LEFT);
        GridBagLayout gridBagL1 = new GridBagLayout();
        //Inicialização principal do frame
        this.setMinimumSize(new Dimension(800,500));
        this.setMaximumSize(new Dimension(800,500));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(springL1);

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
        lista.add(new JLabel("Cliente 1"));
        lista.add(new JLabel("Cliente 2"));

        scrollListaUsuarios = new JScrollPane(wrapListaUsuarios, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollListaUsuarios.setBorder(null);
        scrollListaUsuarios.setPreferredSize(new Dimension(120, 290));

        scrollListaUsuarios.getViewport().add(lista);
        wrapListaUsuarios.add(scrollListaUsuarios);
        //--------------------------------------

        //Criação dos componentes da janela contendo as conversas
        wrapConversas = new JPanel(new CardLayout());
        bordaComTitulo = BorderFactory.createLineBorder(new Color(128,128,128));
        bordaComTitulo = BorderFactory.createTitledBorder(bordaComTitulo, "Conversa com :");
        wrapConversas.setBorder(bordaComTitulo);
        wrapConversas.setPreferredSize(new Dimension(100,100));
        wrapConversas.add(new ChatWindow("Geuso"));

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


        //Adiciona os panels no frame principal
        this.add(optsConn);
        this.add(wrapListaUsuarios);
        this.add(wrapConversas);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String... args) {
        MainGUICliente m1 = new MainGUICliente();
    }
    public void addConversa() {

    }
}
