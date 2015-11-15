package com.example.a14juanms.ud_a1_a14juanms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ud_a1_a14juanms extends Activity {
    private gestionBD basedatos;

    Button btnCL;
    Button btnAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ud_a1_a14juanms);
        btnAP=(Button)findViewById(R.id.btnAP);
        btnCL=(Button)findViewById(R.id.btnCL);
        basedatos = new gestionBD(getApplicationContext());
        basedatos.getWritableDatabase();

        Toast.makeText(getApplicationContext(), "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();


    }

    public void llamarAP(View v){

        Intent i=new Intent(getApplication(),alta_persoa.class);
        startActivity(i);
    }

    public void llamarLP(View v){

        Intent i=new Intent(getApplication(),cargar_lista.class);
        startActivity(i);
    }
}
