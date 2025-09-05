package com.sistema.lista.telefonica.infraestrutura.persistense.contato.mapper;

import com.sistema.lista.telefonica.dto.ContatoRequest;
import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.model.Contato;
import com.sistema.lista.telefonica.model.valueobjects.Email;
import com.sistema.lista.telefonica.model.valueobjects.Telefone;

public class ContatoMapper {

    public Contato toEntity(ContatoRequest contatoRequest){
        Telefone telefone = new Telefone(contatoRequest.telefone());
        Email email = new Email(contatoRequest.email());

        return new Contato(contatoRequest.nome(), telefone, email, contatoRequest.observacao());
    }

    public ContatoResponse toResponse(Contato contato){
        return new ContatoResponse(contato.getId(), contato.getNome(), contato.getTelefone().getValue(), contato.getEmail().getValue(), contato.getObservacao());
    }
}
