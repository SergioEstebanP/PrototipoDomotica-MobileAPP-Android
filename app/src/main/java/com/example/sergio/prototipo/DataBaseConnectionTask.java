package com.example.sergio.prototipo;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Date;
import android.os.Build;
import android.util.Log;

/**
 * Created by sergio on 23/03/18.
 */

public class DataBaseConnectionTask extends AsyncTask<Void, Void, Boolean>{
    private Date fecha;
    private String usuario;
    private String ipName;
    private String comando;
    private static int PUERTO = 47001;
    private String ip="88.4.21.217";
    private InetAddress serveradd;
    private Socket cliente;

    public DataBaseConnectionTask (String comando) throws IOException {
        fecha = new Date();
        this.usuario = "Sergio";
        this.comando = comando;
        this.ipName = "192.168.1.1";
        serveradd = InetAddress.getByName(ip);
        cliente = null;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            cliente = new Socket(serveradd, PUERTO);
            PrintStream outred = new PrintStream(cliente.getOutputStream());
            outred.println(fecha.toString());
            outred.println(usuario);
            outred.println(ipName);
            outred.println(comando);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
