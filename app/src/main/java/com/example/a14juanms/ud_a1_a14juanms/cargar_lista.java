package com.example.a14juanms.ud_a1_a14juanms;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class cargar_lista extends Activity {

    Spinner spPersoas;
    TextView txtPersoa;
    ArrayList<Persoa> listaPersonas = new ArrayList<>();
    Persoa PSelec;
    String ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_lista);
        spPersoas = (Spinner) findViewById(R.id.sp_personas);
        txtPersoa = (TextView) findViewById(R.id.txtPersoa);

        gestionBD base = new gestionBD(this);
        base.sqlLiteDB = base.getReadableDatabase();
        listaPersonas = base.obterPersoa();
        String[] Nomes = new String[listaPersonas.size()];
        for (int i = 0; i < listaPersonas.size(); i++) Nomes[i] = listaPersonas.get(i).getNome();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Nomes);

        spPersoas.setAdapter(adaptador);

        //Escoitador
        spPersoas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                txtPersoa.setText("");
                txtPersoa.setText(listaPersonas.get(pos).getDescricion());
                PSelec = listaPersonas.get(pos);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button btnPref = (Button) findViewById(R.id.btnPref);
        btnPref.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getBaseContext(), preferencias.class);
                startActivity(intent);

            }

        });


        Button btnGardar = (Button) findViewById(R.id.btnGardar);
        btnGardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                ruta = preferencias.getString("Preferencias", "/DATOS/");
                Log.i("Ruta=", ruta);

                gardarDatos(PSelec);
            }

        });


    }

    public void gardarDatos(Persoa PSelec) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.e("ARQUIVO", "TARXETA NON MONTADA");
        }
        File nova_ruta = new File(Environment.getExternalStorageDirectory(), ruta);
        if (nova_ruta.exists() == false) nova_ruta.mkdirs();

        Log.i("Ruta Completa", nova_ruta.toString());


        File arquivo = new File(nova_ruta, PSelec.getNome() + ".txt");
        Log.i("Ruta archivo", arquivo.toString());
        try {
            FileOutputStream fos_sd = new FileOutputStream(arquivo);
            OutputStreamWriter iswriter_sd = new OutputStreamWriter(fos_sd);
            iswriter_sd.write(PSelec.toString() + "\n");
            iswriter_sd.flush();
            iswriter_sd.close();
            fos_sd.close();

            Toast.makeText(getApplicationContext(), "Texto gardado en " + arquivo.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void salir(View v) {
        finish();
    }
}



