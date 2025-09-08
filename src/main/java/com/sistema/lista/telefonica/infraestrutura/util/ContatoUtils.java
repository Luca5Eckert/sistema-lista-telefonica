package com.sistema.lista.telefonica.infraestrutura.util;

import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.exception.contato.ContatoBancoDadosException;

import java.util.List;

public class ContatoUtils {

    public static void listarContatosResponse(List<ContatoResponse> contatoResponses){
        if(contatoResponses.isEmpty()) {
            System.out.println(" Nenhum contato correspondente ");
        } else {
            contatoResponses.forEach(ContatoUtils::imprimirContato);
        }
    }

    private static void imprimirContato(ContatoResponse contatoResponse) {
        System.out.println("---");
        System.out.println(contatoResponse);
        System.out.println("---");
    }


}
