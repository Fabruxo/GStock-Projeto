package dao;

import model.*;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoEstoqueDAO {

    public void inserirProdutoEmEstoque(ProdutoEstoque pe) {
        String sql = "INSERT INTO produtos_estoque (id_produto, id_estoque, corredor, bin, quantidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, pe.getProduto().getId());
            stmt.setLong(2, pe.getEstoque().getId());
            stmt.setString(3, pe.getCorredor());
            stmt.setString(4, pe.getBin());
            stmt.setDouble(5, pe.getQuantidade());

            stmt.executeUpdate();
            System.out.println("Produto inserido no estoque com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto no estoque: " + e.getMessage());
        }
    }

    public List<ProdutoEstoque> listarProdutosEmEstoque() {
        List<ProdutoEstoque> lista = new ArrayList<>();
        String sql = """
            SELECT pe.id, p.id AS id_produto, p.nome, p.anoSuportadoInicio, p.anoSuportadoFim, p.modeloCarro,
                   e.id AS id_estoque, e.nome AS nome_estoque, e.posicao,
                   pe.corredor, pe.bin, pe.quantidade
            FROM produtos_estoque pe
            JOIN produtos p ON pe.id_produto = p.id
            JOIN estoques e ON pe.id_estoque = e.id
        """;

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getLong("id_produto"),
                        rs.getString("nome"),
                        rs.getString("anoSuportadoInicio"),
                        rs.getString("anoSuportadoFim"),
                        rs.getString("modeloCarro")
                );

                Estoque estoque = new Estoque(
                        rs.getLong("id_estoque"),
                        rs.getString("nome_estoque"),
                        rs.getString("posicao")
                );

                ProdutoEstoque pe = new ProdutoEstoque(
                        rs.getLong("id"),
                        produto,
                        estoque,
                        rs.getString("corredor"),
                        rs.getString("bin"),
                        rs.getDouble("quantidade")
                );

                lista.add(pe);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos em estoque: " + e.getMessage());
        }

        return lista;
    }

    public void editarProdutoEmEstoque(ProdutoEstoque pe) {
        String sql = "UPDATE produtos_estoque SET corredor = ?, bin = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pe.getCorredor());
            stmt.setString(2, pe.getBin());
            stmt.setDouble(3, pe.getQuantidade());
            stmt.setLong(4, pe.getId());

            stmt.executeUpdate();
            System.out.println("Produto em estoque atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao editar produto em estoque: " + e.getMessage());
        }
    }

    public void deletarProdutoEmEstoque(long id) {
        String sql = "DELETE FROM produtos_estoque WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Produto removido do estoque com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto em estoque: " + e.getMessage());
        }
    }

}
