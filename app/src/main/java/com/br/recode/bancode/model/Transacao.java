package com.br.recode.bancode.model;

public class Transacao {
    private String boleto;
    private double amount;
    private int origem;
    private int destino;

    public String getBoleto() {
        return boleto;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
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
                "boleto='" + boleto + '\'' +
                ", amount=" + amount +
                ", origem=" + origem +
                ", destino=" + destino +
                '}';
    }
}
