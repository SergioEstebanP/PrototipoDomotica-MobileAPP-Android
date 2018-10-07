import java.io.*;
import java.sql.*;

import java.net.*;
import java.util.*;

public class SirvienteAndroid implements Runnable {

    private final Socket s;
    private static final String BASE_DATOS = "debugLog.db";
    private Connection connection = null;
    private String fecha;
    private String usuario;
    private String ip;
    private String comando;

    public SirvienteAndroid(Socket s) {
        this.s = s;
    }

    public void run() {
        PrintStream outred = null;
        /* intentamos crear un lector que será el que lea el fliujo de datos del socket*/
        try (BufferedReader inred = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            // leemos lo que nos ha enviado el cliente, que deberia de ser un úico mensaje
            fecha = inred.readLine(); 
            usuario = inred.readLine(); 
            ip = inred.readLine(); 
            comando = inred.readLine(); 

            // antes de guardar la informacion en la bbdd ejecutamos el comando
            if (comando.equals("encender")) {
                try {
                    String cmd = "python /home/pi/Universidad/SMOV-practica/gpioScripts/onLights.py";
                    Process pb = Runtime.getRuntime().exec(cmd);

                    String line;
                    BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);

                    }
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String cmd = "python /home/pi/Universidad/SMOV-practica/gpioScripts/offLights.py";
                    Process pb = Runtime.getRuntime().exec(cmd);

                    String line;
                    BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                    input.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // creamos la conexión a la bbdd
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:android.db");
            connection.setAutoCommit(false);
            System.out.println(">> Se ha accedido a las BBDD correctamente.");

            Statement stmt = null;
            stmt = connection.createStatement();
            String sql = "insert into comandosEjecutados (fecha, usuario, ip, comando) values ('" 
                            + fecha + "', '" 
                            + usuario + "', '" 
                            + ip + "', '" 
                            + comando + "');";
            WriteDb(sql, stmt, connection);
            connection.close();
            System.out.println(">> Datos introducidos: " + fecha + "|" + usuario + "|" + ip + "|" + comando + ".");
            System.out.println(">> BBDD actualizada exitosamente.");
            System.out.println(">>");
            outred.close();
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public synchronized void WriteDb (String sql, Statement stmt, Connection connection) {
        try {
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (Exception e) {
        }
    }
}
