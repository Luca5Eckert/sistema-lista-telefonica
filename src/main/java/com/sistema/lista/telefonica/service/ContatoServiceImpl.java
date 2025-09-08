package com.sistema.lista.telefonica.service;

import com.sistema.lista.telefonica.dto.ContatoAtualizaRequest;
import com.sistema.lista.telefonica.dto.ContatoRequest;
import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.exception.contato.ContatoBancoDadosException;
import com.sistema.lista.telefonica.exception.contato.EmailUsadoException;
import com.sistema.lista.telefonica.infraestrutura.persistense.contato.mapper.ContatoMapper;
import com.sistema.lista.telefonica.service.port.ContatoRepository;
import com.sistema.lista.telefonica.model.Contato;
import com.sistema.lista.telefonica.view.port.ContatoService;

import java.util.List;
import java.util.stream.Collectors;

public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public ContatoServiceImpl(ContatoRepository contatoRepository, ContatoMapper contatoMapper) {
        this.contatoRepository = contatoRepository;
        this.contatoMapper = contatoMapper;
    }

    @Override
    public void adicionarContato(ContatoRequest contatoRequest) {
        Contato contato = contatoMapper.toEntity(contatoRequest);

        if(contatoRepository.existeComEmail(contato.getEmail())){
            throw new EmailUsadoException("O email já está em uso");
        }

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

    @Override
    public ContatoResponse pegarUsuarioPorId(long id) {
        var contato = contatoRepository.buscarContatoPorId(id);

        if(contato == null) throw new ContatoBancoDadosException("Contato com id " + id + " não encontrado");

        return contatoMapper.toResponse(contato);
    }

    @Override
    public void atualizarContato(ContatoAtualizaRequest contatoAtualizaRequest, long id) {
        Contato contato = contatoMapper.toEntity(contatoAtualizaRequest, id);

        contatoRepository.atualizarPorId(contato);
    }

    @Override
    public void deletarContato(long id) {
        contatoRepository.deletarPorId(id);
    }

}
