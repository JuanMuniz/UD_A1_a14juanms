package com.example.a14juanms.ud_a1_a14juanms;

/**
 * Created by juanv on 14/11/2015.
 */
public class Persoa {
    private String nome;
    private String descricion;

    public Persoa(String nome, String descricion){
        this.nome=nome;
        this.descricion=descricion;
    }

    public String getNome(){
        return this.nome;
    }
    public String getDescricion(){
        return this.descricion;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setDescricion(String descricion){
        this.descricion=descricion;
    }
    @Override
    public String toString(){
        return "Nome="+getNome()+" Descricion="+getDescricion();
    }
}
