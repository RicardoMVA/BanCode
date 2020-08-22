package com.br.recode.bancode.model;

public class Boleto {

    private String codigoBoleto;

    public String getCodigoBoleto() {
        return codigoBoleto;
    }

    public String gerarCodigoBoleto(Conta conta ,double valor) {
        String numeroDaConta = String.format ("%07d", conta.getCode());

        String valorDoBoleto = String.format ("%09d", valor * 100);

        return codigoBoleto = numeroDaConta + valorDoBoleto;
    }

    public int getContaBoleto() {
        codigoBoleto = getCodigoBoleto();

        int contaBoleto = Integer.valueOf(codigoBoleto.substring(0, 8));

        return contaBoleto;
    }

    public double getValorBoleto() {
        codigoBoleto = getCodigoBoleto();

        int valorBoleto = (Integer.valueOf(codigoBoleto.substring(8))) / 100;

        return valorBoleto;
    }
}
