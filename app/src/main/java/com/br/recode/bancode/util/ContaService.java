package com.br.recode.bancode.util;

import com.br.recode.bancode.model.Conta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ContaService {
    @GET("account/")
    Call<Conta> buscarConta(@Header("cpf") String cpf);
}
