package com.br.recode.bancode.util;

import com.br.recode.bancode.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @Headers("Contenty-Type: application/json")
    @POST("users/")
    Call<User> criarUsuario(@Body User user);

    @GET("users/")
    Call<User> buscarUsuario(@Header("cpf") String cpf, @Header("pws") String pws);

    @GET("getAllUsers/")
    Call<List<User>> buscarTodosUsuarios(@Header("cpf") String userAdmin, @Header("pws") String pws);
}
