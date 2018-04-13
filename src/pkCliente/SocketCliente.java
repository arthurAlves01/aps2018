package pkCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketCliente implements Runnable{

    private String host;
    private int porta;

    public SocketCliente(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    private Socket cliente;
    private Scanner teclado;
    private PrintStream saida;

    public void enviarMensagem(String msg) {
        saida.println(msg);
    }
    public void run() {
        try {
            cliente = new Socket(this.host, this.porta);
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
