package com.br.recode.bancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"mensagem"})
public class Transacao {
    private String codigo_de_barras;
    private double amount;
    private int origem;
    private int destino;

    public String getCodigo_de_barras() {
        return codigo_de_barras;
    }

    public void setCodigo_de_barras(String codigo_de_barras) {
        this.codigo_de_barras = codigo_de_barras;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "boleto='" + codigo_de_barras + '\'' +
                ", amount=" + amount +
                ", origem=" + origem +
                ", destino=" + destino +
                '}';
    }
}
