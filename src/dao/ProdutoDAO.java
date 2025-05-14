package dao;

import model.Produto;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, anoSuportadoInicio, anoSuportadoFim, modeloCarro) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getAnoSuportadoInicio());
            stmt.setString(3, produto.getAnoSuportadoFim());
            stmt.setString(4, produto.getModeloCarro());
            stmt.executeUpdate();

            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("anoSuportadoInicio"),
                        rs.getString("anoSuportadoFim"),
                        rs.getString("modeloCarro")
                );
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    public void editarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, anoSuportadoInicio = ?, anoSuportadoFim = ?, modeloCarro = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getAnoSuportadoInicio());
            stmt.setString(3, produto.getAnoSuportadoFim());
            stmt.setString(4, produto.getModeloCarro());
            stmt.setLong(5, produto.getId());

            stmt.executeUpdate();
            System.out.println("Produto editado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao editar produto: " + e.getMessage());
        }
    }

    public void deletarProduto(long id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Produto deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
        }
    }

}
