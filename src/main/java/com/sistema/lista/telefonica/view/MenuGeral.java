package com.sistema.lista.telefonica.view;

import com.sistema.lista.telefonica.dto.ContatoAtualizaRequest;
import com.sistema.lista.telefonica.dto.ContatoRequest;
import com.sistema.lista.telefonica.dto.ContatoResponse;
import com.sistema.lista.telefonica.exception.contato.ContatoBancoDadosException;
import com.sistema.lista.telefonica.infraestrutura.beans.ContatoBeans;
import com.sistema.lista.telefonica.service.ContatoService;

import java.util.List;
import java.util.Scanner;

import static com.sistema.lista.telefonica.infraestrutura.util.ContatoUtils.listarContatosResponse;

public class MenuGeral {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ContatoService contatoService = ContatoBeans.instanceService();


    public static void iniciar() {
        inicio();
    }

    private static void inicio() {
        boolean sair = false;

        while (!sair) {

            try {
                System.out.println("-------------------------------------------------------");
                System.out.println("                   LISTA TELEFONICA                    ");
                System.out.println("-------------------------------------------------------");

                System.out.println(" 1- Cadastrar contato ");
                System.out.println(" 2- Listar contatos ");
                System.out.println(" 3- Buscar Contato por nome ");
                System.out.println(" 4- Atualizar Contato");
                System.out.println(" 5- Remover Contato");
                System.out.println("\n 6- Sair do sistema");
                System.out.println("-------------------------------------------------------");


                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listarContatos();
                    case 3 -> buscarContatoPorNome();
                    case 4 -> atualizarContato();
                    case 5 -> deletarContato();
                    case 6 -> sair = true;
                    default -> System.out.println("Opção invalida");
                }

            } catch (RuntimeException runtimeException) {

                System.out.println(runtimeException.getMessage());

            }

        }

        System.out.println("-------------------------------------------------------");
        System.out.println("                  SISTEMA ENCERRADO                    ");
        System.out.println("-------------------------------------------------------");

    }

    private static void deletarContato() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                   DELETAR CONTATO                     ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    private static void atualizarContato() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                ATUALIZAR CONTATO                      ");
        System.out.println("-------------------------------------------------------");
        System.out.println(" Digite o id do contato que deseja alterar: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        var contatoResponse = contatoService.pegarUsuarioPorId(id);

        if(contatoResponse == null) throw new ContatoBancoDadosException("Contato não existe");

        System.out.println("------------------------");
        System.out.println(" Dados antigos: ");
        contatoResponse.imprimirVerticalmente();

        System.out.println("------------------------");

        System.out.println("Insira o novo telefone: ");
        String novoTelefone = scanner.nextLine();

        System.out.println("Insira o novo email: ");
        String novoEmail = scanner.nextLine();

        System.out.println("Insira a nova observação: ");
        String novaObservacao = scanner.nextLine();

        System.out.println("-------------------------------------------------------");

        ContatoAtualizaRequest contatoAtualizaRequest = new ContatoAtualizaRequest(novoTelefone, novoEmail, novaObservacao);

        contatoService.atualizarContato(contatoAtualizaRequest, id);

        System.out.println("Atualizado com sucesso");


    }

    private static void buscarContatoPorNome() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                       BUSCA                           ");
        System.out.println("-------------------------------------------------------");
        System.out.println(" Digite o nome do contato que deseja buscar: ");
        String nome = scanner.nextLine();

        System.out.println("-------------------------------------------------------");

        List<ContatoResponse> contatoResponses = contatoService.buscarContatoPorNome(nome);

        listarContatosResponse(contatoResponses);

        System.out.println("-------------------------------------------------------");


    }

    private static void listarContatos() {
        List<ContatoResponse> contatoResponses = contatoService.pegarContatos();

        System.out.println("-------------------------------------------------------");
        System.out.println("                      CONTATOS                         ");
        System.out.println("-------------------------------------------------------");

        listarContatosResponse(contatoResponses);

        System.out.println("-------------------------------------------------------");

    }

    private static void cadastrar() {
        System.out.println("-------------------------------------------------------");
        System.out.println("                  ADICIONAR CONTATO                    ");
        System.out.println("-------------------------------------------------------");

        System.out.println("Nome do contato: ");
        String nome = scanner.nextLine();

        System.out.println("Telefone do contato: ");
        String telefone = scanner.nextLine();

        System.out.println("Email do contato: ");
        String email = scanner.nextLine();

        System.out.println("Digite a observação: ");
        String observacao = scanner.nextLine();

        System.out.println("-------------------------------------------------------");

        var contato = new ContatoRequest(nome, telefone, email, observacao);
        contatoService.adicionarContato(contato);

        System.out.println("Contato Adicionado com sucesso");

        System.out.println("-------------------------------------------------------");


    }

}
