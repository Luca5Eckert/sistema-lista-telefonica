package com.sistema.lista.telefonica.service.port;

import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.model.Contato;
import com.sistema.lista.telefonica.model.valueobjects.Email;

import java.util.List;

public interface ContatoRepository {

    void inserir(Contato contato);

    List<Contato> pegarContatos();

    List<Contato> buscarContatoPorNome(String nome);

    Contato buscarContatoPorId(long id);

    void atualizarPorId(Contato contato);

    boolean existeComEmail(Email email);

    void deletarPorId(long id);
}
