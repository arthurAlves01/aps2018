package pkCliente;

import pkAux.Mensagem;
import pkAux.TipoMensagem;

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
    private String nomeUsuario;

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public Socket getSocket() {
        return cliente;
    }

    public SocketCliente(String host, int porta, String nome) {
        this.hostServidor = host;
        this.portaServidor = porta;
        this.nomeUsuario = nome;
    }
    public void enviaDadosConn() {
        try {
            out.writeObject(new Mensagem(this.nomeUsuario, TipoMensagem.REQ_CONN));
            System.out.println(this.nomeUsuario);
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
            System.out.println("Tentando estabeler conexão com o servidor: " + cliente.getLocalPort());
            TratadorMsgServidor r = new TratadorMsgServidor(this);
            new Thread(r).start();
        } catch (UnknownHostException eHost) {
            eHost.printStackTrace();
        } catch (IOException eio) {
            eio.printStackTrace();
        }
    }
    public String getNomeCliente() {
        return this.nomeUsuario;
    }
}
