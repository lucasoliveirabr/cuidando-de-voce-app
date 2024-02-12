package com.example.cuidandodevoce.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SuportesApi {
  @Headers("Accept: application/json")
  @POST("suportes")
  Call<SuportesRequest> addSupportMessage(@Body SuportesRequest suportesRequest);
}