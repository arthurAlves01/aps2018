package pkCliente;

import java.io.IOException;
import java.net.UnknownHostException;


public class RodaCliente {
    static private InterfaceCliente cl1;
    static private SocketCliente sk1;
    public static void main(String[] args) throws UnknownHostException,	IOException {
        Thread threadSocket, threadInterface;
        cl1 = new InterfaceCliente();
        sk1 = new SocketCliente("127.0.0.1", 12345);
        threadSocket = new Thread(sk1, "conexao");
        threadSocket.start();

        threadInterface = new Thread(cl1, "interface");
        threadInterface.start();
    }
    public static void enviaParaInterface(String msg) {
        cl1.mostraMensagem(msg);
    }
    public static void enviaMensagemParaSocket(String msg) {
        sk1.enviarMensagem(msg);
    }
}
