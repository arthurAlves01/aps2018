package pkCliente;;

import java.io.*;
import java.net.*;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import pkAux.*;
import java.util.ArrayList;

class TratadorMsgServidor implements Runnable {

    private SocketCliente skCliente;
    private ObjectInputStream inServidor;

    public TratadorMsgServidor(SocketCliente serv) {
        this.skCliente = serv;
        this.inServidor = serv.getIn();
    }

    public void run() {
        Mensagem msg;
        skCliente.enviaDadosConn();
        System.out.println("Enviando informações para o servidor.");
        while(true) {
            try {
                msg = (Mensagem) inServidor.readObject();
                if(msg.getTipoMsg()==TipoMensagem.CONN_OK) {
                    System.out.println("Conectado ao servidor!");
                    RodaCliente.setEstadoConn(true);
                    RodaCliente.habilitaCamposInterface();
                    break;
                } else if(msg.getTipoMsg()==TipoMensagem.DC) {
                    System.out.println("Nome de usuário em uso!");
                    break;
                } else {

                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
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