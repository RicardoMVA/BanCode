package com.br.recode.bancode.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"createdAt", "updatedAt", "__v"})
public class Conta implements Serializable {
    private String _id;
    private int bank_branch;
    private int code;
    private String user;
    private double account_balance;
    private int status;
    private String createdAt;
    private String updatedAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(int bank_branch) {
        this.bank_branch = bank_branch;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Conta {" +
                "_id='" + _id + '\'' +
                ", bank_branch=" + bank_branch +
                ", code=" + code +
                ", user=" + user +
                ", account_balance=" + account_balance +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
