package com.sistema.lista.telefonica.infraestrutura.beans;

import com.sistema.lista.telefonica.infraestrutura.persistense.contato.dao.ContatoDao;
import com.sistema.lista.telefonica.infraestrutura.persistense.contato.mapper.ContatoMapper;
import com.sistema.lista.telefonica.infraestrutura.persistense.contato.repository.ContatoRepository;
import com.sistema.lista.telefonica.infraestrutura.persistense.contato.repository.ContatoRepositoryAdapter;
import com.sistema.lista.telefonica.model.Contato;
import com.sistema.lista.telefonica.service.ContatoService;
import com.sistema.lista.telefonica.service.ContatoServiceImpl;

public class ContatoBeans {

    private static final ContatoDao CONTATO_DAO = new ContatoDao();
    private static final ContatoRepository CONTATO_REPOSITORY = new ContatoRepositoryAdapter(CONTATO_DAO);
    private static final ContatoMapper CONTATO_MAPPER = new ContatoMapper();
    private static final ContatoService CONTATO_SERVICE = new ContatoServiceImpl(CONTATO_REPOSITORY, CONTATO_MAPPER);

    public static ContatoService instanceService(){
        return CONTATO_SERVICE;
    }

}
