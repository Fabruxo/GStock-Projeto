package dao;

import model.*;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoEstoqueDAO {

// FUNÇÃO PARA APRESENTAR UM DETERMINADO PRODUTO NUM DETERMINADO ESTOQUE
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

// FUNÇÃO PARA APRESENTAR OS PRODUTOS EM DETERMINADOS ESTOQUES
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
//FUNÇÃO PARA EDITAR UM PRODUTO EM ESTOQUE
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

// FUNÇÃO PARA DELETAR O PRODUTO DE ALGUM ESTOQUE PARA CASOS DE ADIÇÃO ERRONEA
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

// FUNÇÃO PARA RETIRAR UM PRODUTO DE UM ESTOQUE PARA SER UTILIZADO
    public void retirarProdutoDoEstoque(long id, double quantidadeRetirada) {
        String selectSql = "SELECT quantidade FROM produtos_estoque WHERE id = ?";
        String updateSql = "UPDATE produtos_estoque SET quantidade = ? WHERE id = ?";
        HistoricoRetirada historicoParaRegistrar = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            selectStmt.setLong(1, id);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                double quantidadeAtual = rs.getDouble("quantidade");

                if (quantidadeRetirada > quantidadeAtual) {
                    System.out.println("❌ Quantidade insuficiente no estoque.");
                    return;
                }

                double novaQuantidade = quantidadeAtual - quantidadeRetirada;

                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setDouble(1, novaQuantidade);
                    updateStmt.setLong(2, id);
                    updateStmt.executeUpdate();
                    updateStmt.executeUpdate();
                    System.out.println("✅ Produto retirado com sucesso! Nova quantidade: " + novaQuantidade);
                }

                historicoParaRegistrar = new HistoricoRetirada(
                        id,
                        quantidadeRetirada,
                        java.time.LocalDateTime.now().toString(),
                        System.getProperty("user.name")
                );

            } else {
                System.out.println("❌ ProdutoEstoque não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao retirar produto: " + e.getMessage());
        }
        if (historicoParaRegistrar != null) {
            HistoricoRetiradaDAO historicoDAO = new HistoricoRetiradaDAO();
            historicoDAO.registrar(historicoParaRegistrar);
        }
    }

}



