package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Cliente.NovaInterface;
import pkAux.*;

public class ConnCliente implements Runnable {

    private Socket cliente;
    private SocketServidor servidor;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ArrayList<ConnCliente> connAtivas;
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
                if(msgUser.getTipoMsg()==TipoMensagem.REQ_CONN&&servidor.inserirUsuario(this, msgUser.getOrigem())) {
                    System.out.println("Recebendo dados do cliente.");
                    this.nomeUsuario = (String) msgUser.getMensagem();
                    Mensagem resposta = new Mensagem(TipoMensagem.CONN_OK);
                    this.enviarMensagem(resposta);
                    System.out.println(resposta.getTipoMensagem());
                    statusConn = true;
                    NovaInterface.estado(statusConn);
                    break;
                } else {
                    this.enviarMensagem(new Mensagem(TipoMensagem.DC));
                    this.cliente.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(statusConn);
        while(statusConn) {
            try {
                Object inMsg = in.readObject();
                if(inMsg.getClass().getName().equals("pkAux.Mensagem")) {
                    curMsg = (Mensagem) inMsg;
                    System.out.println(curMsg.getMensagem());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro de IO com o cliente: " + this.cliente.getPort());
                this.servidor.excluiCliente(this);
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Erro de Classe Não Encontratada com o cliente: " + this.cliente.getPort());
                this.servidor.excluiCliente(this);
                break;
            }
        }
    }
}
