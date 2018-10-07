package com.example.sergio.prototipo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickBotonEncender(View v) {
        try {
            DataBaseConnectionTask c = new DataBaseConnectionTask("encender");
            c.execute((Void) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickBotonApagar(View v) {
        try {
            DataBaseConnectionTask c = new DataBaseConnectionTask("apagar");
            c.execute((Void) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
