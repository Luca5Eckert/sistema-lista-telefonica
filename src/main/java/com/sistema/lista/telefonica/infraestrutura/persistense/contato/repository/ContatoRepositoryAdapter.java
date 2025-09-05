package com.sistema.lista.telefonica.infraestrutura.persistense.contato.repository;

import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.infraestrutura.persistense.contato.dao.ContatoDao;
import com.sistema.lista.telefonica.model.Contato;
import com.sistema.lista.telefonica.service.port.ContatoRepository;

import java.util.List;

public class ContatoRepositoryAdapter implements ContatoRepository {

    private final ContatoDao contatoDao;

    public ContatoRepositoryAdapter(ContatoDao contatoDao) {
        this.contatoDao = contatoDao;
    }

    @Override
    public void inserir(Contato contato) {
        contatoDao.inserir(contato);
    }

    @Override
    public List<Contato> pegarContatos() {
        return contatoDao.pegarContatos();
    }

    @Override
    public List<Contato> buscarContatoPorNome(String nome) {
        return contatoDao.pegarContatoPorNome(nome);
    }

    @Override
    public Contato buscarContatoPorId(long id) {
        return contatoDao.pegarContatoPorId(id);
    }
}
