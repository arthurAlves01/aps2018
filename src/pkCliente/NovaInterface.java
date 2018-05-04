package pkCliente;


import pkAux.Mensagem;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.regex.*;
import java.awt.event.*;

public class NovaInterface extends javax.swing.JFrame implements Runnable, WindowListener {

    public void windowClosed(WindowEvent e) {

    }
    public void windowActivated(WindowEvent e) {

    }
    public void windowClosing(WindowEvent e) {
        RodaCliente.encerrarConn();
    }
    public void windowOpened(WindowEvent e) {

    }
    public void windowDeactivated(WindowEvent e) {

    }
    public void windowDeiconified(WindowEvent e) {

    }
    public void windowIconified(WindowEvent e) {

    }

    private final Pattern PADRAO_USUARIO = Pattern.compile("^[A-Za-z][A-Za-z0-9]{3,}$");
    private Matcher matcherUsuario;

    public NovaInterface() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnConectar = new javax.swing.JButton();
        btnDesconectar = new javax.swing.JButton();
        nomeConectar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreareceber = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtEnviar = new javax.swing.JTextArea();
        btnAnexar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conectar"));

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnDesconectar.setText("Desconectar");
        btnDesconectar.setEnabled(false);
        btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nomeConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDesconectar, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConectar)
                                        .addComponent(btnDesconectar)
                                        .addComponent(nomeConectar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Onlines"), BorderFactory.createEmptyBorder(-3,-3,-3,-3)));

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2.setPreferredSize(new Dimension(110, 100));
        jPanel2.setMinimumSize(new Dimension(110,100));
        jPanel2.setMaximumSize(new Dimension(110,100));


        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setToolTipText("");

        txtAreareceber.setColumns(20);
        txtAreareceber.setRows(5);
        txtAreareceber.setEnabled(false);
        jScrollPane1.setViewportView(txtAreareceber);

        txtEnviar.setColumns(20);
        txtEnviar.setRows(5);
        txtEnviar.setEnabled(false);
        jScrollPane2.setViewportView(txtEnviar);

        btnAnexar.setText("Anexar");
        btnAnexar.setEnabled(false);

        btnLimpar.setText("Limpar");
        btnLimpar.setEnabled(false);

        btnEnviar.setText("Enviar");
        btnEnviar.setEnabled(false);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jScrollPane2)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnAnexar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnLimpar)
                                                .addGap(16, 16, 16)
                                                .addComponent(btnEnviar)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAnexar)
                                        .addComponent(btnLimpar)
                                        .addComponent(btnEnviar))
                                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {
        if(this.nomeConectar.getText().equals("")) {
            alerta("Informe um nome de usuário!");
            return;
        }
        matcherUsuario = PADRAO_USUARIO.matcher(this.nomeConectar.getText());
        if(!matcherUsuario.find()) {
            alerta("O nome de usuário deve conter apenas número e letrar e iniciar com uma letra");
            return;
        }
        RodaCliente.estabelecerConn("169.254.167.178", 12345, this.nomeConectar.getText());
        jPanel2.add(new itemLista("Teste"));
        jPanel2.repaint();
        jPanel2.revalidate();
    }

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        RodaCliente.enviaMensagemParaSocket(new Mensagem(txtEnviar.getText()));
    }

    private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {
        RodaCliente.encerrarConn();
    }

    public void habilitaCampos() {

    }
    public void listarClientes(Mensagem msg) {
        jPanel2.removeAll();
        ArrayList<String> clientes = (ArrayList<String>) msg.getMensagem();
        for(String cliente: clientes) {
            if(!cliente.equals(RodaCliente.getUsuarioSocket())) {
                jPanel2.add(new itemLista(cliente));
                jPanel2.repaint();
                jPanel2.revalidate();
            }
        }
    }
    public void desabilitarCampos() {
        //TODO: Habilitar campo conectar e desabilitar demais
    }
    public void alerta(String mensagemDeAlerta) {
        JOptionPane.showMessageDialog(null, mensagemDeAlerta);
    }
    public void run() {
        new NovaInterface().setVisible(true);
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAnexar;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnDesconectar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nomeConectar;
    private javax.swing.JTextArea txtAreareceber;
    private javax.swing.JTextArea txtEnviar;
    // End of variables declaration                   

    void mostraMensagem(Mensagem msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
class itemLista extends javax.swing.JLabel {
    public itemLista(String nome) {
        this.setPreferredSize(new Dimension(100, 22));
        this.setMaximumSize(new Dimension(100, 22));
        this.setMinimumSize(new Dimension(100, 22));
        this.setText(nome);
    }
}