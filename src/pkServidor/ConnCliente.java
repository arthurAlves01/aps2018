package pkServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pkAux.*;

public class ConnCliente implements Runnable {

    private Socket cliente;
    private SocketServidor servidor;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String nomeUsuario;
    private boolean statusConn;

    public ConnCliente(Socket cliente, SocketServidor servidor) throws IOException {
        this.cliente = cliente;
        this.servidor = servidor;
        out = new ObjectOutputStream(this.cliente.getOutputStream());
        in = new ObjectInputStream(this.cliente.getInputStream());
    }
    public void enviarMensagem(Mensagem msg) {
        try {
            this.out.writeObject(msg);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void run() {
        Mensagem curMsg;
        while(true) {
            try {
                Mensagem msgUser = (Mensagem) in.readObject();
                if (msgUser.getTipoMsg() == TipoMensagem.REQ_CONN && servidor.inserirUsuario(this, msgUser.getOrigem())) {
                    this.nomeUsuario = msgUser.getOrigem();
                    Mensagem resposta = new Mensagem(TipoMensagem.CONN_OK);
                    this.enviarMensagem(resposta);
                    statusConn = true;
                    System.out.println("Nova conexão com o cliente \"" + this.nomeUsuario + "\": " + cliente.getInetAddress().getHostAddress() + " na porta " + cliente.getPort());
                    break;
                } else {
                    this.enviarMensagem(new Mensagem(TipoMensagem.DC));
                    this.cliente.close();
                    System.out.println("Conexão do cliente " + cliente.getInetAddress().getHostAddress() + ":" + cliente.getPort());
                    System.out.println(" recusada, usuário em uso: " + msgUser.getOrigem());
                    break;
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        servidor.atualizaListaCliente(null);
        while(statusConn) {
            try {
                Object inMsg = in.readObject();
                curMsg = (Mensagem) inMsg;
                if(curMsg.getTipoMsg()==TipoMensagem.DC) {
                    servidor.excluiCliente(this);
                    statusConn = false;
                    System.out.println("O cliente \"" + this.nomeUsuario + "\" desconectou: " + cliente.getInetAddress().getHostAddress() + ":" + this.cliente.getPort());
                } else {
                    servidor.enviarMensagemParaCliente(curMsg);
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Erro de IO, o cliente \"" + this.nomeUsuario + "\" desconectou: " + cliente.getInetAddress().getHostAddress() + ":" + this.cliente.getPort());
                this.servidor.excluiCliente(this);
                break;
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                System.out.println("Erro de 'Classe Não Encontratada', o cliente \"" + this.nomeUsuario + "\" desconectou: " + cliente.getInetAddress().getHostAddress() + ":" + this.cliente.getPort());
                this.servidor.excluiCliente(this);
                break;
            }
        }
        try {
            this.cliente.close();
            System.out.println("Porta fechada!");
        } catch (IOException ioe) {
            //ioe.printStackTrace();
            System.out.println("Erro em fechar socket do cliente: " + this.getNomeUsuario());
        }
    }
}