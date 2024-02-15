package com.example.cuidandodevoce.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  public static Retrofit retrofit;

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl("http://192.168.1.168:8800/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}