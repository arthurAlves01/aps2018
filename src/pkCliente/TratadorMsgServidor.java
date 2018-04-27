package pkCliente;;

import java.io.*;
import java.net.*;
import pkAux.*;

class TratadorMsgServidor implements Runnable {

    private ObjectInputStream inServidor;

    public TratadorMsgServidor(SocketCliente cliente) {
        this.inServidor = cliente.getIn();
    }

    public void run() {
        Mensagem msg;

        while(true) {
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