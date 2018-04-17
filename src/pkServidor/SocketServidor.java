package pkServidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServidor {

    private int porta;
    private List<Socket> clientes;

    public SocketServidor(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<>();
    }

    public void executa() throws IOException  {
        try(ServerSocket servidor = new ServerSocket(this.porta)){
            System.out.println("Porta 12345 aberta!");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente " +
                        cliente.getInetAddress().getHostAddress() + " na porta " + cliente.getPort());

                this.clientes.add(cliente);

                TratadorMensagem tc = new TratadorMensagem(cliente, this);
                new Thread(tc).start();
            }
        }
    }

    public void distribuiMensagem(Socket clienteQueEnviou, String msg, int porta) {
        for (Socket cliente : this.clientes) {
            if(!cliente.equals(clienteQueEnviou)){
                try {
                    PrintStream ps = new PrintStream(cliente.getOutputStream());
                    ps.println(porta + " disse: " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
