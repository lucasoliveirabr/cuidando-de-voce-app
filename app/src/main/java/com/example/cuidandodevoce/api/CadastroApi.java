package com.example.cuidandodevoce.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CadastroApi {
  @Headers("Accept: application/json")
  @POST("pacientes")
  Call<CadastroRequest> addPaciente(@Body CadastroRequest cadastroRequest);
}