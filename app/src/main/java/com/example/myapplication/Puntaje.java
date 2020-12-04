package com.example.myapplication;

public class Puntaje {
    private String id;
    private String prom;


    public Puntaje(String id, String prom) {
        this.prom = prom;
    }

    public Puntaje() {

    }

    public String getProm() {
        return prom;
    }

    public void setProm(String prom) {
        this.prom = prom;
    }
}
