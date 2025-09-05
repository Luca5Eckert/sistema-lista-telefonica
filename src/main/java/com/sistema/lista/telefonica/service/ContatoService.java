package com.sistema.lista.telefonica.service;

import com.sistema.lista.telefonica.dto.ContatoRequest;
import com.sistema.lista.telefonica.dto.ContatoResponse;

import java.util.List;

public interface ContatoService {

    void adicionarContato(ContatoRequest contatoRequest);

    List<ContatoResponse> pegarContatos();

    List<ContatoResponse> buscarContatoPorNome(String nome);


    ContatoResponse pegarUsuarioPorId(long id);
}
