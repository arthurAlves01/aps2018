package pkCliente;

import java.io.InputStream;
import java.util.Scanner;

class TratadorMensagem implements Runnable {

    private InputStream servidor;

    public TratadorMensagem(InputStream servidor) {
        this.servidor = servidor;
    }

    public void run() {
        try(Scanner s = new Scanner(this.servidor)){
            while (s.hasNextLine()) {
                RodaCliente.enviaParaInterface(s.nextLine());
            }
        }
    }
}