package pkCliente;

import pkAux.Mensagem;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketCliente implements Runnable{

    private String hostServidor;
    private int portaServidor;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket cliente;
    private String nomeCliente;

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public SocketCliente(String host, int porta, String nome) {
        this.hostServidor = host;
        this.portaServidor = porta;
        this.nomeCliente = nome;
    }
    public void enviaDadosConn() {
        try {
            out.writeObject(this.nomeCliente);
            System.out.println(this.nomeCliente);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void enviarMensagem(Mensagem msg) {
        try {
            System.out.println((String)msg.getMensagem());
            out.writeObject(msg);
            //out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            cliente = new Socket(this.hostServidor, this.portaServidor);
            out = new ObjectOutputStream(cliente.getOutputStream());
            in = new ObjectInputStream(cliente.getInputStream());
            System.out.println("Conectado ao servidor utilizando a porta " + cliente.getLocalPort());
            this.enviaDadosConn();
            TratadorMsgServidor r = new TratadorMsgServidor(this);
            new Thread(r).start();
        } catch (UnknownHostException eHost) {
            eHost.printStackTrace();
        } catch (IOException eio) {
            eio.printStackTrace();
        }
    }

}
