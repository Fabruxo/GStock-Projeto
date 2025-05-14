package dao;

import model.Estoque;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    public void inserirEstoque(Estoque estoque) {
        String sql = "INSERT INTO estoques (nome, posicao) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estoque.getNome());
            stmt.setString(2, estoque.getPosicao());
            stmt.executeUpdate();

            System.out.println("Estoque inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir estoque: " + e.getMessage());
        }
    }

    public List<Estoque> listarEstoques() {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "SELECT * FROM estoques";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estoque estoque = new Estoque(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("posicao")
                );
                estoques.add(estoque);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar estoques: " + e.getMessage());
        }

        return estoques;
    }

    public void editarEstoque(Estoque estoque) {
        String sql = "UPDATE estoques SET nome = ?, posicao = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estoque.getNome());
            stmt.setString(2, estoque.getPosicao());
            stmt.setLong(3, estoque.getId());

            stmt.executeUpdate();
            System.out.println("Estoque editado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao editar estoque: " + e.getMessage());
        }
    }

    public void deletarEstoque(long id) {
        String sql = "DELETE FROM estoques WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Estoque deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar estoque: " + e.getMessage());
        }
    }

}
