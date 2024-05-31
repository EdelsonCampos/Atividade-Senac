package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    // TO DO : CRUD!

    public static boolean cadastrar(CadastroL p) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "insert into cadastro(nome, dataa, situacao)values(?,?,?);";

            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            query.setString(1, p.getNome());
            query.setString(2, p.getDataa());
            query.setString(3, p.getSituacao());

            //Executar a instrução SQL
            query.execute();

            conexao.desconectar();
            return true;
        } catch (SQLException se) {
            System.out.println("Erro ao cadastrar registro no banco de dados");
            return false;

        }

    }

    public static List<CadastroL> listarTodos() {
//Declaração da variável lista que será retornada
        List<CadastroL> lista = new ArrayList<CadastroL>();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT * FROM produtos";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                CadastroL p = new CadastroL();
                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setDataa(resposta.getString("dataa"));
                p.setSituacao(resposta.getString("situacao"));

                lista.add(p);
            }

            conexao.desconectar();
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro ao listar os registros do banco de dados!");
            e.printStackTrace();

        }
        return lista;
    }

    public static boolean atualizar(CadastroL p) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            //Instrução SQL
            String sql = "UPDATE produtos SET nome=?, dataa=?, situacao=? WHERE id=?;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            //Passar as informações do objeto para a consulta
            consulta.setString(1, p.getNome());
            consulta.setString(3, p.getDataa());
            consulta.setString(2, p.getSituacao());
            consulta.setInt(4, p.getId());

            //Executar a instrução
            consulta.execute();

            //Desconectar do Banco
            conexao.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o registro do banco de dados");
            return false;
        }
    }

    public static boolean excluir(int id) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "DELETE FROM cadastro WHERE id=?;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            consulta.setInt(1, id);

            //Executa a instrução SQL
            consulta.execute();

            //Desconectar do banco
            conexao.desconectar();
            return true;
        } catch (SQLException s) {
            System.out.println("Erro ao deletar registro no banco de dados!");
            return false;
        }
    }
}
