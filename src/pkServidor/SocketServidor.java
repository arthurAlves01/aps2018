package pkServidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
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
        String ip = "127.0.0.1";
        try(ServerSocket servidor = new ServerSocket(this.porta)){
            Enumeration en = NetworkInterface.getNetworkInterfaces();
            while(en.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) en.nextElement();
                Enumeration ee = ni.getInetAddresses();
                while(ee.hasMoreElements()) {
                    InetAddress ia = (InetAddress) ee.nextElement();
                    if(ia.getHostAddress().toString().substring(0,2).equals("19"))
                        ip = ia.getHostAddress();
                }
            }
            System.out.println("Servidor rodando em: " + ip + ":" + this.porta);

            while (true) {
                Socket cliente = servidor.accept();
                ConnCliente tc = new ConnCliente(cliente, this);
                new Thread(tc).start();
            }
        }
    }
    public void enviarMensagemParaCliente(Mensagem msg) {
        if(msg.getDestino().equals("\\all")||msg.getDestino()==null) {
            Iterator lista = this.clientes.entrySet().iterator();
            ConnCliente c;
            while(lista.hasNext()) {
                Map.Entry item = (Map.Entry)lista.next();
                c = (ConnCliente)item.getValue();
                c.enviarMensagem(msg);
            }
        } else {
            ConnCliente dest = this.clientes.get(msg.getDestino());
            dest.enviarMensagem(msg);
        }
    }
    public boolean inserirUsuario(ConnCliente socket, String nomeUsuario) {
        if(this.clientes.get(nomeUsuario)==null) {
            this.clientes.put(nomeUsuario, socket);
            this.atualizaListaCliente(null);
            return true;
        }
        return false;
    }

    public void atualizaListaCliente(String clienteRemovido) {
        ArrayList<String> connAtivas = new ArrayList<>();
        Iterator lista = this.clientes.entrySet().iterator();
        ConnCliente clienteAtual;
        Mensagem msgLista;
        while(lista.hasNext()) {
            Map.Entry item = (Map.Entry)lista.next();
            connAtivas.add(item.getKey().toString());
        }
        msgLista = new Mensagem(clienteRemovido, connAtivas);
        lista = this.clientes.entrySet().iterator();
        while(lista.hasNext()) {
            Map.Entry item = (Map.Entry)lista.next();
            clienteAtual = (ConnCliente) item.getValue();
            clienteAtual.enviarMensagem(msgLista);
        }
    }
    public ConnCliente excluiCliente(ConnCliente cliente) {
        this.clientes.remove(cliente.getNomeUsuario());
        this.atualizaListaCliente(cliente.getNomeUsuario());
        return cliente;
    }
}
