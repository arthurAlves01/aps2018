package pkCliente;

import pkAux.*;

public class RodaCliente {
    static private InterfaceCliente cl1;
    static private SocketCliente sk1;
    static private boolean estadoConn;

    public static void main(String[] args) {
        Thread threadInterface;
        cl1 = new InterfaceCliente();
        threadInterface = new Thread(cl1, "interface");
        threadInterface.start();
    }

    public static void estabelecerConn(String host, int porta, String usuario) {
        if(!getEstadoConn()) {
            Thread threadSocket;
            sk1 = new SocketCliente(host, porta, usuario);
            estadoConn = true;
            threadSocket = new Thread(sk1, "conexao");
            threadSocket.start();
        }
    }

    public static boolean getEstadoConn() {
        return estadoConn;
    }

    public static void enviaParaInterface(Mensagem msg) {
        cl1.mostraMensagem(msg);
    }
    public static void enviaMensagemParaSocket(Mensagem msg) {
        sk1.enviarMensagem(msg);
    }
}
