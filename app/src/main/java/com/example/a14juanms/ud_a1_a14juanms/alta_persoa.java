package com.example.a14juanms.ud_a1_a14juanms;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class alta_persoa extends Activity {

    EditText etNome;
    EditText etDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_persoa);
        etDesc=(EditText)findViewById(R.id.etDesc);
        etNome=(EditText)findViewById(R.id.etNome);
    }

    public void darDeAlta(View v){
        Persoa p = new Persoa(etNome.getText().toString(), etDesc.getText().toString());
        gestionBD base = new gestionBD(this);
        base.sqlLiteDB = base.getWritableDatabase();
        if (!(etNome.getText().toString().equals("") || etDesc.getText().toString().equals(""))) {
            base.engadirPersoa(p);
            etNome.setText("");
            etDesc.setText("");
            Log.i("Engadeuse Persoa", "" + p.getNome());
        }
        else{
            Toast.makeText(getApplicationContext(),"Engade datos por favor",Toast.LENGTH_SHORT).show();
        }

    }
    public void salir(View v){
        finish();
    }
}
