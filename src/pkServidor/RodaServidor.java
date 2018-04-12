package pkServidor;

import java.io.IOException;

public class RodaServidor {

    public static void main(String[] args)
            throws IOException {
        new SocketServidor(12345).executa();
    }
}