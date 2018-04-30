package pkCliente;;

import java.io.*;
import java.net.*;
import pkAux.*;

class TratadorMsgServidor implements Runnable {

    private SocketCliente sk;
    private ObjectInputStream inServidor;

    public TratadorMsgServidor(SocketCliente serv) {
        this.sk = serv;
        this.inServidor = serv.getIn();
    }

    public void run() {
        Mensagem msg;
        sk.enviaDadosConn();
        System.out.println("Enviando informações para o servidor.");
        while(true) {
            try {
                msg = (Mensagem) inServidor.readObject();
                if(msg.getTipoMsg()==TipoMensagem.CONN_OK) {
                    System.out.println("Conectado ao servidor!");
                    break;
                } else if(msg.getTipoMsg()==TipoMensagem.DC) {
                    System.out.println("Nome de usuário em uso!");
                    RodaCliente.setEstadoConn();
                    break;
                } else {

                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        while(RodaCliente.getEstadoConn()) {
            try {
                msg = (Mensagem) inServidor.readObject();
                RodaCliente.enviaParaInterface(msg);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}