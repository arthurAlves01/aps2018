package pkServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import pkAux.*;

public class SocketServidor {

    private int porta;
    private HashMap<String, ConnCliente> clientes;

    public SocketServidor(int porta) {
        this.porta = porta;
        this.clientes = new HashMap<>();
    }

    public void executa() throws IOException  {
        try(ServerSocket servidor = new ServerSocket(this.porta)){
            System.out.println("Porta 12345 aberta!");

            while (true) {
                Socket cliente = servidor.accept();
                ConnCliente tc = new ConnCliente(cliente, this);
                new Thread(tc).start();
            }
        }
    }
    public void enviarMensagemParaCliente(Mensagem msg) {
        ConnCliente dest = this.clientes.get(msg.getDestino());
        if(dest!=null) {
            dest.enviarMensagem(msg);
        }
    }
    public boolean inserirUsuario(ConnCliente socket, String nomeUsuario) {
        if(this.clientes.get(nomeUsuario)==null) {
            this.clientes.put(nomeUsuario, socket);
            this.atualizaListaCliente();
            return true;
        }
        return false;
    }

    public void atualizaListaCliente() {
        ArrayList<String> connAtivas = new ArrayList<>();
        Iterator lista = this.clientes.entrySet().iterator();
        ConnCliente cliente;
        Mensagem msgLista;
        while(lista.hasNext()) {
            Map.Entry item = (Map.Entry)lista.next();
            connAtivas.add(item.getKey().toString());
        }
        msgLista = new Mensagem(connAtivas);
        lista = this.clientes.entrySet().iterator();
        while(lista.hasNext()) {
            Map.Entry item = (Map.Entry)lista.next();
            cliente = (ConnCliente) item.getValue();
            cliente.enviarMensagem(msgLista);
        }
    }
    public ConnCliente excluiCliente(ConnCliente cliente) {
        this.clientes.remove(cliente.getNomeUsuario());
        this.atualizaListaCliente();
        return cliente;
    }
}
