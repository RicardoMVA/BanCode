package com.br.recode.bancode.util;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.NovaConta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ContaService {
    @GET("account/")
    Call<Conta> buscarConta(@Header("cpf") String cpf);

    @Headers("Contenty-Type: application/json")
    @POST("accounts/")
    Call<Conta> criarConta(@Header("cpf") String cpf, @Header("pws") String pws, @Body NovaConta novaConta);
}
