package com.sistema.lista.telefonica.model;

import com.sistema.lista.telefonica.exception.contato.EmailBlankException;
import com.sistema.lista.telefonica.exception.contato.NomeBlankException;
import com.sistema.lista.telefonica.exception.contato.TelefoneBlankException;
import com.sistema.lista.telefonica.model.valueobjects.Email;
import com.sistema.lista.telefonica.model.valueobjects.Telefone;
import org.apache.commons.lang3.math.NumberUtils;

public class Contato {

    private final long id;

    private String nome;

    private Telefone telefone;

    private Email email;

    private String observacao;


    public Contato() {
        this.id = -1;
    }

    public Contato(String nome, Telefone telefone, Email email, String observacao) {
        this.id = -1;
        setNome(nome);
        this.telefone = telefone;
        this.email = email;
        setObservacao(observacao);
    }

    public Contato(long id, String nome, Telefone telefone, Email email, String observacao) {
        this.id = id;
        setNome(nome);
        this.telefone = telefone;
        this.email = email;
        setObservacao(observacao);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isBlank()){
            throw new NomeBlankException("Nome não pode ser branco");
        }
        this.nome = nome;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {

        this.telefone = telefone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
