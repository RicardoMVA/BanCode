package com.br.recode.bancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"createdAt", "updatedAt", "__v"})
public class Movimentacao implements Serializable {
    private String _id;
    private List<String> bank_account;
    private int source_transaction;
    private double amount;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getBank_account() {
        return bank_account;
    }

    public void setBank_account(List<String> bank_account) {
        this.bank_account = bank_account;
    }

    public int getSource_transaction() {
        return source_transaction;
    }

    public void setSource_transaction(int source_transaction) {
        this.source_transaction = source_transaction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "_id='" + _id + '\'' +
                ", bank_account=" + bank_account +
                ", source_transaction=" + source_transaction +
                ", amount=" + amount +
                '}';
    }
}
