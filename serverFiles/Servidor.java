import java.net.*;
import java.io.*;

public class Servidor {

    public static final int PUERTO = 47001;

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(PUERTO);
        /* creación del socket */

        System.out.println("-------------------- SERVIDOR ACEPTANDO CONEXIONES --------------------");
        while (true) {
            Socket sock = servidor.accept();
            SirvienteAndroid sirv = new SirvienteAndroid(sock);
            Thread hiloCliente = new Thread(sirv, "thread");
            hiloCliente.start();
        }
    }
}
