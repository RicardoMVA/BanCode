package com.br.recode.bancode.model;

public class User {
    private int cpf;
    private String name;
    private String avatar;
    private int telefone;
    // senha do usuário
    private String pws;

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }

    @Override
    public String toString() {
        return "User {" +
                "cpf=" + cpf +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", telefone=" + telefone +
                ", pws=" + pws +
                '}';
    }
}
