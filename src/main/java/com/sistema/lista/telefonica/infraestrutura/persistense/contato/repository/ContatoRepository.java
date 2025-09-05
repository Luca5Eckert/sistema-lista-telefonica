package com.sistema.lista.telefonica.infraestrutura.persistense.contato.repository;

import com.sistema.lista.telefonica.model.Contato;

import java.util.List;

public interface ContatoRepository {

    void inserir(Contato contato);

    List<Contato> pegarContatos();

    List<Contato> buscarContatoPorNome(String nome);
}
