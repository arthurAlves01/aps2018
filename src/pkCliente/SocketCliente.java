package pkCliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketCliente implements Runnable{

    private String host;
    private int porta;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket cliente;

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public SocketCliente(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    public void enviarMensagem(String msg) {
        try {
            out.writeObject(msg);
            //out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            cliente = new Socket(this.host, this.porta);
            out = new ObjectOutputStream(cliente.getOutputStream());
            in = new ObjectInputStream(cliente.getInputStream());
            System.out.println("Conectado ao servidor utilizando a porta " + cliente.getLocalPort());

            TratadorMensagem r = new TratadorMensagem(this);
            new Thread(r).start();

        } catch (UnknownHostException eHost) {
            eHost.printStackTrace();
        } catch (IOException eIO) {
            eIO.printStackTrace();
        }
    }

}
