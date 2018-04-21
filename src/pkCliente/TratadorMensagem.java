package pkCliente;

import java.io.*;
import java.net.*;

class TratadorMensagem implements Runnable {

    private ObjectOutputStream outServidor;
    private ObjectInputStream inServidor;

    public TratadorMensagem(SocketCliente cliente) throws IOException {
        this.outServidor = cliente.getOut();
        this.inServidor = cliente.getIn();
    }

    public void run() {
        String msg;

        while(true) {
            try {
                msg = (String) inServidor.readObject();
                //if(!msg.equals(""))
                RodaCliente.enviaParaInterface(msg);
                //inServidor.reset();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}