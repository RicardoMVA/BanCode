package com.br.recode.bancode.model;

public class NovaConta {
    private String cpf;
    private double account_balance;
    private int status;

    public NovaConta(String cpf) {
        this.cpf = cpf;
        this.account_balance = 100d;
        this.status = 1;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
