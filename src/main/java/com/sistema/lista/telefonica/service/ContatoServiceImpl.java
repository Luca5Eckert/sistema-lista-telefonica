package com.sistema.lista.telefonica.service;

import com.sistema.lista.telefonica.dto.ContatoRequest;
import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.infraestrutura.persistense.contato.mapper.ContatoMapper;
import com.sistema.lista.telefonica.service.port.ContatoRepository;
import com.sistema.lista.telefonica.model.Contato;

import java.util.List;
import java.util.stream.Collectors;

public class ContatoServiceImpl implements ContatoService{

    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public ContatoServiceImpl(ContatoRepository contatoRepository, ContatoMapper contatoMapper) {
        this.contatoRepository = contatoRepository;
        this.contatoMapper = contatoMapper;
    }

    @Override
    public void adicionarContato(ContatoRequest contatoRequest) {
        Contato contato = contatoMapper.toEntity(contatoRequest);
        contatoRepository.inserir(contato);
    }

    @Override
    public List<ContatoResponse> pegarContatos() {
        List<Contato> contatoList = contatoRepository.pegarContatos();
        return contatoList.stream().map(contatoMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ContatoResponse> buscarContatoPorNome(String nome) {
        List<Contato> contatoList = contatoRepository.buscarContatoPorNome(nome);
        return contatoList.stream().map(contatoMapper::toResponse).collect(Collectors.toList());
    }

}
