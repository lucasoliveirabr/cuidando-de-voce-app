package com.example.cuidandodevoce.api;

import com.google.gson.annotations.SerializedName;

public class SuportesRequest {

  @SerializedName("tipoSuporte")
  private String tipoSuporte;

  @SerializedName("timestampCriacao")
  private String timestampCriacao;

  @SerializedName("mensagemUsuario")
  private String mensagemUsuario;

  public void setTipoSuporte(String tipoSuporte) {
    this.tipoSuporte = tipoSuporte;
  }

  public void setTimestamp(String timestampCriacao) {
    this.timestampCriacao = timestampCriacao;
  }

  public void setMensagemUsuario(String mensagemUsuario) {
    this.mensagemUsuario = mensagemUsuario;
  }
}