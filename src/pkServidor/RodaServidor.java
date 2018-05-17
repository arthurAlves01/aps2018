package pkServidor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RodaServidor {

    public static void main(String[] args) throws IOException {
        boolean portaValida = false;
        int porta = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Informe a porta que deseja utilizar (1024 até 49151):");
        do {
            try {
                porta = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Informe um valor númerico entre 1024 e 49151!");
            }
            if(porta>=1024&&porta<=49151) {
                portaValida = true;
            } else {
                System.out.println("Informe um valor númerico entre 1024 e 49151!");
            }
        } while (!portaValida);
        new SocketServidor(porta).executa();
    }
}