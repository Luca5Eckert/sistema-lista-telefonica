package com.sistema.lista.telefonica.infraestrutura.persistense.contato.dao;

import com.sistema.lista.telefonica.exception.contato.ContatoBancoDadosException;
import com.sistema.lista.telefonica.infraestrutura.persistense.conexao.ConexaoFactory;
import com.sistema.lista.telefonica.model.Contato;
import com.sistema.lista.telefonica.model.valueobjects.Email;
import com.sistema.lista.telefonica.model.valueobjects.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    public void inserir(Contato contato){
        String consulta = "INSERT INTO contato (nome, telefone, email, observacao) VALUES (?, ?, ?, ?)";

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(consulta)){

            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getTelefone().getValue());
            preparedStatement.setString(3, contato.getEmail().getValue());
            preparedStatement.setString(4, contato.getObservacao());

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

    }

    public List<Contato> pegarContatos(){
        String consulta = "SELECT id, nome, telefone, email, observacao FROM contato";
        List<Contato> contatoList = new ArrayList<>();

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(consulta)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String observacao = resultSet.getString("observacao");

                var contato = new Contato(id, nome, new Telefone(telefone), new Email(email), observacao);
                contatoList.add(contato);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

        return contatoList;
    }

    public List<Contato> pegarContatoPorNome(String nomeBusca){
        String consulta = """
                SELECT id
                       , nome
                       , telefone
                       , email
                       , observacao
                FROM contato
                WHERE nome LIKE ?
                """;
        List<Contato> contatoList = new ArrayList<>();

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(consulta)){

            preparedStatement.setString(1, "%" + nomeBusca + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                    long id = resultSet.getLong("id");
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String email = resultSet.getString("email");
                    String observacao = resultSet.getString("observacao");

                    var contato = new Contato(id, nome, new Telefone(telefone), new Email(email), observacao);
                    contatoList.add(contato);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

        return contatoList;

    }

    public Contato pegarContatoPorId(long id) {
        String consulta = """
                SELECT id
                       , nome
                       , telefone
                       , email
                       , observacao
                FROM contato
                WHERE id = ?
                """;

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(consulta)){

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                long idBancoDados = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String observacao = resultSet.getString("observacao");

                return new Contato(idBancoDados, nome, new Telefone(telefone), new Email(email), observacao);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

        return null;

    }

    public void atualizarContatoPorId(Contato contato) {
        String consulta = """
                UPDATE contato
                SET email = ?, telefone = ?, observacao = ?
                WHERE id = ?
                """;

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement statement = connection.prepareStatement(consulta)){

            statement.setString(1, contato.getEmail().getValue());
            statement.setString(2, contato.getTelefone().getValue());
            statement.setString(3, contato.getObservacao());
            statement.setLong(4, contato.getId());

            statement.executeUpdate();

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

    }

    public boolean existeComEmail(Email email) {
        String consulta = """
                SELECT email
                FROM contato
                WHERE email = ?
                """;

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement statement = connection.prepareStatement(consulta)){

            statement.setString(1, email.getValue());

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

    }

    public void deletarPorId(long id) {
        String consulta = """
                DELETE FROM contato
                WHERE id = ?
                """;

        try(Connection connection = ConexaoFactory.conectar();
            PreparedStatement statement = connection.prepareStatement(consulta)){

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            throw new ContatoBancoDadosException("Erro ao se conectar no banco de dados");
        }

    }
}
