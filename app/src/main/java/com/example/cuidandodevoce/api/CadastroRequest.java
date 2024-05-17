package com.example.cuidandodevoce.api;

import com.google.gson.annotations.SerializedName;

public class CadastroRequest {

  @SerializedName("nomeCompleto")
  private String fullName;

  @SerializedName("dataNascimento")
  private String birthDate;

  @SerializedName("cpf")
  private String cpf;

  @SerializedName("numeroContato")
  private String contactNumber;

  @SerializedName("email")
  private String email;

  @SerializedName("senha")
  private String senha;

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}