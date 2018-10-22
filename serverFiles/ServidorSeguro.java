import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import java.io.*;

public class ServidorSeguro {

    public static final int PUERTO = 47001;

    public static void main(String[] args) throws IOException {
        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(PUERTO);
        /* creación del socket */

        System.out.println("-------------------- SERVIDOR ACEPTANDO CONEXIONES --------------------");
        while (true) {
            SSLSocket sslSocket = (SSLSocket)serverSocket.accept();
            SirvienteAndroid sirv = new SirvienteAndroid(sslSocket);
            Thread hiloCliente = new Thread(sirv, "thread");
            hiloCliente.start();
        }
    }
}
