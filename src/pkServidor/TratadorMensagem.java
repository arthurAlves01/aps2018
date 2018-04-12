package pkServidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class TratadorMensagem implements Runnable {

    private Socket cliente;
    private SocketServidor servidor;

    public TratadorMensagem(Socket cliente, SocketServidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        try(Scanner s = new Scanner(this.cliente.getInputStream())) {
            while (s.hasNextLine()) {
                servidor.distribuiMensagem(this.cliente, s.nextLine(), this.cliente.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}