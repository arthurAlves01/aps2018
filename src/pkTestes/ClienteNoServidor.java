package pkTestes;

import java.io.*;
import java.net.*;

public class ClienteNoServidor implements Runnable {
    private Socket connCliente;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public void run() {
        String msg;
        try {
            this.out = new ObjectOutputStream(this.connCliente.getOutputStream());
            this.in = new ObjectInputStream(this.connCliente.getInputStream());
            while(true) {
                msg = (String) this.in.readObject();
                this.in.reset();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ClienteNoServidor(Socket cliente) {
        this.connCliente = cliente;
    }
}
