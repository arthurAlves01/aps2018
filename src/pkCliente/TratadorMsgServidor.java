package pkCliente;;

import java.io.*;
import pkAux.*;

class TratadorMsgServidor implements Runnable {

    private SocketCliente skCliente;
    private ObjectInputStream inServidor;
    private boolean timeout;

    public TratadorMsgServidor(SocketCliente serv) {
        this.skCliente = serv;
        this.inServidor = serv.getIn();
    }
    private void sinalizarDC() {
        RodaCliente.desabilitaCamposInterface();
        RodaCliente.setEstadoConn(false);
        RodaCliente.enviarAlerta("Você foi desconectado!");
    }
    public void run() {
        timeout = true;
        Mensagem msg;
        skCliente.enviaDadosConn();
        System.out.println("Enviando informações para o servidor.");
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        timeout = false;
                    }
                },
                5000
        );
        while(timeout) {
            try {
                msg = (Mensagem) inServidor.readObject();
                if(msg.getTipoMsg()==TipoMensagem.CONN_OK) {
                    RodaCliente.setEstadoConn(true);
                    RodaCliente.habilitaCamposInterface();
                    RodaCliente.enviarAlerta("Conectado com sucesso!");
                    break;
                } else if(msg.getTipoMsg()==TipoMensagem.DC) {
                    RodaCliente.enviarAlerta("Nome de usuário em uso!");
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
                sinalizarDC();
                e.printStackTrace();
            } catch (IOException ioe) {
                sinalizarDC();
                //ioe.printStackTrace();
            }
        }
    }
}