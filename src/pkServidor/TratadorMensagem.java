package pkServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class TratadorMensagem implements Runnable {

    private Socket cliente;
    private SocketServidor servidor;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public TratadorMensagem(Socket cliente, SocketServidor servidor) throws IOException {
        this.cliente = cliente;
        this.servidor = servidor;
        out = new ObjectOutputStream(this.cliente.getOutputStream());
        in = new ObjectInputStream(this.cliente.getInputStream());
    }
    public void manda(String msg) {
        try {
            this.out.writeObject(msg);
        } catch (IOException ioe) {
            System.out.println("Erro ao enviar mensagem!");
        }
    }
    public void run() {
        while(true) {
            try {
                Object m = in.readObject();
                servidor.distribuiMensagem(this, /*s.nextLine()*/(String) m, this.cliente.getPort());
                //in.reset();
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("O cliente desconectou!");
                break;
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
            }
        }
    }
}