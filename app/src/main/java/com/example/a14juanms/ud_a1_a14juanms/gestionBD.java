package com.example.a14juanms.ud_a1_a14juanms;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class gestionBD extends SQLiteOpenHelper{

    public SQLiteDatabase sqlLiteDB;

    public final static String NOME_BD="GestionBD";
    public final static int VERSION_BD=1;

    private final String CONSULTAR_PERSOA ="SELECT nome,descricion FROM PERSOAS order by nome";
    private final String TABOA_PERSOAS="PERSOAS";
    private String CREAR_TABOA_PERSOAS ="CREATE TABLE PERSOAS ( " +
            "nome  VARCHAR ( 50 ) PRIMARY KEY," +
            "descricion VARCHAR( 250 )  NOT NULL)";



    public gestionBD(Context context) {
        super(context, NOME_BD, null, VERSION_BD);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREAR_TABOA_PERSOAS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS PERSOAS");
        onCreate(db);

    }

    public long engadirPersoa(Persoa p){
        ContentValues valores = new ContentValues();
        valores.put("nome", p.getNome());
        valores.put("descricion",p.getDescricion());
        long id = sqlLiteDB.insert("PERSOAS",null,valores);

        return id;

    }

    public int borrarPersoa(Persoa p){
        String condicionwhere = "nome=?";
        String[] parametros = new String[]{String.valueOf(p.getNome())};
        int rexistrosafectados = sqlLiteDB.delete(TABOA_PERSOAS,condicionwhere,parametros);

        return rexistrosafectados;

    }

    public int modificarPersoa(Persoa p){
        ContentValues datos = new ContentValues();
        datos.put("nome", p.getNome());
        datos.put("descricion",p.getDescricion());

        String where = "nome=?";
        String[] params = new String[]{String.valueOf(p.getNome())};

        int rexistrosModificados = sqlLiteDB.update(TABOA_PERSOAS, datos, where, params);

        return rexistrosModificados;
    }


    public ArrayList<Persoa> obterPersoa() {
        ArrayList<Persoa> listaPersonas = new ArrayList<Persoa>();

        Cursor datosConsulta = sqlLiteDB.rawQuery(CONSULTAR_PERSOA, null);
        if (datosConsulta.moveToFirst()) {
            Persoa p;
            while (!datosConsulta.isAfterLast()) {
                p = new Persoa(datosConsulta.getString(0),
                        datosConsulta.getString(1));
                listaPersonas.add(p);
                datosConsulta.moveToNext();
            }
        }

        return listaPersonas;
    }

}