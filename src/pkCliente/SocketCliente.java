package pkCliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente implements Runnable{

    private String host;
    private int porta;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketCliente(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    private Socket cliente;
    private Scanner teclado;
    private PrintStream saida;

    public void enviarMensagem(String msg) {
        try {
            //saida.println(msg);
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            cliente = new Socket(this.host, this.porta);
            out = new ObjectOutputStream(cliente.getOutputStream());
            in = new ObjectInputStream(cliente.getInputStream());
            teclado = new Scanner(System.in);
            saida = new PrintStream(cliente.getOutputStream());
            System.out.println("Conectado ao servidor utilizando a porta " + cliente.getLocalPort());

            TratadorMensagem r = new TratadorMensagem(cliente.getInputStream());
            new Thread(r).start();

            while (teclado.hasNextLine()) {
                saida.println(teclado.nextLine());
            }

        } catch (UnknownHostException eHost) {
            eHost.printStackTrace();
        } catch (IOException eIO) {
            eIO.printStackTrace();
        }
    }

}
