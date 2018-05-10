package pkCliente;

import pkAux.*;
import java.io.IOException;

public class RodaCliente {
    static private MainGUICliente cl1;
    static private SocketCliente sk1;
    static private boolean estadoConn;
    static private Thread threadSocket;

    public static void main(String[] args) {
        Thread threadInterface;
        cl1 = new MainGUICliente();
        threadInterface = new Thread(cl1, "interface");
        threadInterface.start();
        cl1.exibirMensagem(new Mensagem("","","Hello World!"));
    }

    public static void estabelecerConn(String host, int porta, String usuario) {
        if(!getEstadoConn()) {
            sk1 = new SocketCliente(host, porta, usuario);
            threadSocket = new Thread(sk1, "conexao");
            threadSocket.start();
        }
    }
    public static void setEstadoConn(boolean estado) {
        estadoConn = estado;
    }
    public static boolean getEstadoConn() {
        return estadoConn;
    }

    public static void enviaParaInterface(Mensagem msg) {
        if(msg.getTipoMsg()==TipoMensagem.LISTA_CLIENTES) {
            cl1.atualizarLista(msg);
        } else {
            cl1.atualizarLista(msg);
        }
    }
    public static void enviaMensagemParaSocket(Mensagem msg) {
        sk1.enviarMensagem(msg);
    }
    public static void habilitaCamposInterface() {
        cl1.habilitarCampos();
    }
    public static void desabilitaCamposInterface() {
        cl1.desabilitarCampos();
    }
    public static void encerrarConn() {
        if(getEstadoConn()) {
            try {
                Mensagem dc = new Mensagem(TipoMensagem.DC);
                enviaMensagemParaSocket(dc);
                sk1.getSocket().close();
                setEstadoConn(false);
                desabilitaCamposInterface();
                //threadSocket.interrupt();
                System.out.println("Desconectado com sucesso!");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    public static String getUsuarioSocket() {
        return sk1.getNomeUsuario();
    }
    public static void enviarAlerta(String mensagem) {
        cl1.alerta(mensagem);
    }
}
