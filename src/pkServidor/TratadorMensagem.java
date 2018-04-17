package pkServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

class TratadorMensagem implements Runnable {

    private Socket cliente;
    private SocketServidor servidor;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public TratadorMensagem(Socket cliente, SocketServidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        try {
            //Scanner s = new Scanner(this.cliente.getInputStream());
            out = new ObjectOutputStream(this.cliente.getOutputStream());
            in = new ObjectInputStream(this.cliente.getInputStream());
            String m = (String) in.readObject();
            while (true) {
                servidor.distribuiMensagem(this.cliente, /*s.nextLine()*/m, this.cliente.getPort());
                //in.flush();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}