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
    private static final String SERVER_ADDRESS = "servidorandroiddo.ddns.net";
    private static final int PUERTO = 47001;

    private Date fecha;
    private String usuario;
    private String ipName;
    private String comando;
    private InetAddress serveradd;
    private Socket cliente;

    public DataBaseConnectionTask (String comando) {
        cliente = null;
        this.fecha = new Date();
        this.usuario = "Sergio";
        this.comando = comando;
        this.ipName = "192.168.1.1";
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            this.serveradd = InetAddress.getByName(SERVER_ADDRESS);
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
