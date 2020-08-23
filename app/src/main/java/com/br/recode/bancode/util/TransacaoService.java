package com.br.recode.bancode.util;

import com.br.recode.bancode.model.Boleto;
import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.Movimentacao;
import com.br.recode.bancode.model.Transacao;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TransacaoService {
    @Headers("Contenty-Type: application/json")
    @POST("transaction/gerar_boleto/")
    Call<Boleto> gerarBoleto(@Header("account") String numeroConta, @Header("cpf") String cpf, @Header("pws") String pws, @Body Transacao transacao);

    @Headers("Contenty-Type: application/json")
    @POST("transaction/pagamento/")
    Call<Conta> pagarBoleto(@Header("account") String numeroConta, @Header("cpf") String cpf, @Header("pws") String pws, @Body Transacao transacao);

    @Headers("Contenty-Type: application/json")
    @POST("transaction/transferencia/")
    Call<Void> transferencia(@Header("cpf") String cpf, @Header("pws") String pws, @Body Transacao transacao);

    @GET("transaction/getByUser/")
    Call<ArrayList<Movimentacao>> buscarExtrato(@Header("cpf") String cpf, @Header("pws") String pws);
}
