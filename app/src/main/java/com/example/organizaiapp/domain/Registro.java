package com.example.organizaiapp.domain;

public class Registro {

    private String categoria;
    private String descricao;
    private double valor;
    private int iconResource;

    public Registro(String categoria, String descricao, double valor, int iconResource) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.iconResource = iconResource;
    }

    public Registro() {

    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
