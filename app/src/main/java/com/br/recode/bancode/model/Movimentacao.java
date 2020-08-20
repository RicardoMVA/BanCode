package com.br.recode.bancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties({"createdAt", "updatedAt", "__v"})
public class Movimentacao {
    private String _id;
    private List<String> contas;
    private String transacao;
    private double valor;

    public Movimentacao(String _id, List<String> contas, String transacao, double valor) {
        this._id = _id;
        this.contas = contas;
        this.transacao = transacao;
        this.valor = valor;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getContas() {
        return contas;
    }

    public void setContas(List<String> contas) {
        this.contas = contas;
    }

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Movimentacoes{" +
                "_id='" + _id + '\'' +
                ", contas=" + contas +
                ", transacao='" + transacao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
