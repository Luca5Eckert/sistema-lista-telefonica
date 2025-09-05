package com.sistema.lista.telefonica.dto;

public record ContatoResponse(long id, String nome, String telefone, String email, String observacao) {

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Telefone: " + telefone + " | Email: " + email + " | Observação: " + observacao;
    }
}
