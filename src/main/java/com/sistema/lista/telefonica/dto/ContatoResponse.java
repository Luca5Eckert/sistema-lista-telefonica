package com.sistema.lista.telefonica.dto;

public record ContatoResponse(long id, String nome, String telefone, String email, String observacao) {

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Telefone: " + telefone + " | Email: " + email + " | Observação: " + observacao;
    }

    public void imprimirVerticalmente() {
        System.out.println(" ID: " + id);
        System.out.println(" Nome: " + nome);
        System.out.println(" Telefone: " + telefone);
        System.out.println(" Email: " + email);
        System.out.println(" Observação: " + observacao);
    }
}
