package pkServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import pkAux.*;

public class ConnCliente implements Runnable {

    private Socket cliente;
    private SocketServidor servidor;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ArrayList<ConnCliente> connAtivas;
    private String nomeUsuario;

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
                Object inMsg = in.readObject();
                if(inMsg.getClass().getName().equals("pkAux.Mensagem")) {
                    curMsg = (Mensagem) inMsg;
                    //servidor.enviarMensagemParaCliente(curMsg);
                    System.out.println(curMsg.getMensagem());
                } else if(inMsg.getClass().getName().equals("java.lang.String")) {
                    if(servidor.inserirUsuario(this, (String)inMsg)) {
                        this.nomeUsuario = (String) inMsg;
                    }
                }

                //in.reset();
            } catch (IOException e) {
                //e.printStackTrace();
                servidor.excluiCliente(this);
                System.out.println("O cliente desconectou " + this.getNomeUsuario() + "!");
                break;
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
            }
        }
    }
}