package com.br.recode.bancode.util;

import com.br.recode.bancode.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    @GET("users/")
    Call<User> buscarUsuario(@Header("cpf") String cpf, @Header("pws") String pws);

    @Headers("Contenty-Type: application/json")
    @POST("users/")
    Call<User> criarUsuario(@Body User user);

    @Headers("Contenty-Type: application/json")
    @PUT("user/update/")
    Call<User> editarUsuario(@Body User user);

    @GET("getAllUsers/")
    Call<ArrayList<User>> buscarTodosUsuarios(@Header("cpf") String userAdmin, @Header("pws") String pws);
}
