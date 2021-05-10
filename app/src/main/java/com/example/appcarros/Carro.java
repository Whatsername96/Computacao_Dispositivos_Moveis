package com.example.appcarros;

public class Carro {
    public int id;
    public String modelo, montadora, ano;

    public Carro() {

    }

    public Carro(String modelo, String montadora, String ano) {
        this.modelo = modelo;
        this.montadora = montadora;
        this.ano = ano;
    }

    public Carro(int id, String modelo, String montadora, String ano) {
        this.id = id;
        this.modelo = modelo;
        this.montadora = montadora;
        this.ano = ano;
    }
}
