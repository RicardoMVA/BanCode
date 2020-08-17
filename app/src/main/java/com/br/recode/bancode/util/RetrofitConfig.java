package com.br.recode.bancode.util;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://newbank-backend.herokuapp.com/" )
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }

    public ContaService getContaService() {
        return this.retrofit.create(ContaService.class);
    }
}
