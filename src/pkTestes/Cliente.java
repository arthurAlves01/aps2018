package pkTestes;

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;
import java.net.*;

public class Cliente implements Runnable {
    private String host;
    private int porta;
    private Socket conn;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Cliente(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    public void run() {
        try {
            this.conn = new Socket(this.host, this.porta);
            this.out = new ObjectOutputStream(this.conn.getOutputStream());
            this.in = new ObjectInputStream(this.conn.getInputStream());
            System.out.println("Conectado!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Cliente c1 = new Cliente("127.0.0.1", 12345);
        Thread t1 = new Thread(c1);
        t1.start();
    }
}
