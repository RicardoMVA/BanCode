package com.br.recode.bancode.util;

import com.br.recode.bancode.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {
    @GET("users/")
    Call<User> buscarUsuario(@Header("cpf") String cpf, @Header("pws") String pws);
}
