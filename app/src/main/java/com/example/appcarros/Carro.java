package com.example.appcarros;

public class Carro {

    public static final int ANO_MINIMO = 2000;

    private int id;
    private String modelo, montadora;
    private int ano;

    public Carro() {

    }

    public Carro(String modelo, String montadora, int ano) {
        this.modelo = modelo;
        this.montadora = montadora;
        this.setAno(ano);
    }

    public Carro(int id, String modelo, String montadora, int ano) {
        this.id = id;
        this.modelo = modelo;
        this.montadora = montadora;
        this.setAno(ano);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMontadora() {
        return montadora;
    }

    public void setMontadora(String montadora) {
        this.montadora = montadora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        if(ano >= ANO_MINIMO) {
            this.ano = ano;
        }
    }

    @Override
    public String toString() {
        return id + " | " + modelo + " - " + montadora + " - " + ano;
    }
}
